package com.green.blue.red.repository;

import com.green.blue.red.domain.Member;
import com.green.blue.red.domain.MemberRole;
import com.green.blue.red.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
public class MemberRepositoryTests {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testInsertMember() {
        for(int i=0; i<10; i++){
            Member member = Member.builder()
                    .email("user"+i+"@aaa.com")
                    .pw(passwordEncoder.encode("1111"))
                    .nickname("USER"+i)
                    .build();
            member.addRole(MemberRole.USER);
            if(i >= 5) member.addRole(MemberRole.MANAGER);
            if(i >= 8) member.addRole(MemberRole.ADMIN);
            memberRepository.save(member);
        }
    }

    @Test
    public void testRead() {
        String email = "user9@aaa.com";
        Member member = memberRepository.getWithRoles(email);

        log.info("-----------------");
        log.info("{}",member);
    }

    @Test
    public void testRead2() {
        //반복문을 활용하여 user1, user9번까지 조회하고 MemberDTO를 만들어서 저장
        List<MemberDTO> list = new ArrayList<>();
        List<MemberDTO> listUser = new ArrayList<>();

        for(int i=0; i<10; i++){
            Member member = memberRepository.getWithRoles("user"+i+"@aaa.com");
            log.info("테스트=>{}", member);
            list.add(modelMapper.map(member, MemberDTO.class));
        }
        log.info("리스트:{}",list);

        Map<String, List<String>> map = new HashMap<>();
        map.put("user", new ArrayList<>());
        map.put("manager", new ArrayList<>());
        map.put("admin", new ArrayList<>());

        for(MemberDTO i : list){ //조건문 순서 가장 큰 것 부터
            if(i.getMemberRoleList().contains(MemberRole.ADMIN)){
                map.get("admin").add(i.getEmail());
            }
            else if(i.getMemberRoleList().contains(MemberRole.MANAGER)){
                map.get("manager").add(i.getEmail());
            }
            else if(i.getMemberRoleList().contains(MemberRole.USER)){
                map.get("user").add(i.getEmail());
            }
        }
        log.info("map=>{}", map);
        //{"user":[], "manager":[], "admin":[]}
        //대응되는 email 저장
    }
}
