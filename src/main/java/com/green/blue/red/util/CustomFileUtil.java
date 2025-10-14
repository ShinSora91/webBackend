package com.green.blue.red.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.coobird.thumbnailator.Thumbnails;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomFileUtil {

    @Value("${com.green.blue.red.upload.path}")
    private String uploadPath;

    @PostConstruct
    public void init(){
        File tempFolder = new File(uploadPath);
        if(tempFolder.exists() == false) tempFolder.mkdirs();//폴더가 존재하지 않으면 폴더를 생성
        uploadPath = tempFolder.getAbsolutePath();
        log.info(("-----------------------------------폴더 생성"));
        log.info(uploadPath);
    }
    public List<String> saveFiles(List<MultipartFile> files) throws RuntimeException{
        if(files == null || files.size() == 0) return null; //controller로부터 파일을 추가하지않을 경우
        List<String> uploadNames = new ArrayList<>();
        for (MultipartFile i : files){
            //UUID는 고유한 번호를 생성해주는 자바 utility, 동일한 파일 관리를 위함
            String savedName = UUID.randomUUID().toString() + "_" + i.getOriginalFilename();
            Path savePath = Paths.get(uploadPath, savedName);
            try {
                Files.copy(i.getInputStream(), savePath);
                //thumbnail 관련 추가
                String contentType = i.getContentType();
                if(contentType != null && contentType.startsWith("image")){//이미지 여부확인
                    Path thumbnailPath = Paths.get(uploadPath, "s_" + savedName);

                    Thumbnails.of(savePath.toFile())
                            .size(200,200)
                            .toFile(thumbnailPath.toFile());
                }
                uploadNames.add(savedName);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return uploadNames;
    }
    //C://a/b/c/a.jpg => separator (//)
    //일반적으로 file server 가 있고 그 파일 서버의 경로를 db에서 가지고 있음
    //db는 고가라서 가능한 큰 파일을 저장하지 않음

    public ResponseEntity<Resource> getFile(String fileName){
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
        if(!resource.exists()) resource = new FileSystemResource(uploadPath + File.separator + fileName);
        HttpHeaders headers = new HttpHeaders();
        try{
            String check = Files.probeContentType(resource.getFile().toPath());
            log.info("CustomFileUtil => check:{}", check);
            headers.add("Content-Type", check);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource); //resource (실제 이미지)
        //브라우저가 encoding된 데이터를 header 정보를 기반으로 decoding(복호화)
    }
    public void deleteFiles(List<String> fileNames){
        if(fileNames == null || fileNames.size() == 0) return;
        fileNames.forEach(i -> {
            //썸네일이 있는지 확인하고 삭제
            String thumbnailfileName = "s_" + i;
            Path thumbnailPath = Paths.get(uploadPath, thumbnailfileName);
            Path filePath = Paths.get(uploadPath, i);
            try {
                Files.deleteIfExists(filePath);
                Files.deleteIfExists(thumbnailPath);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        });
    }
}
