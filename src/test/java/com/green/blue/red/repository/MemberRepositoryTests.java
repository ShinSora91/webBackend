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

import java.util.*;

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

//    @Test
//    public void testRead2() {
//        //반복문을 활용하여 user1, user9번까지 조회하고 MemberDTO를 만들어서 저장
//        List<MemberDTO> list = new ArrayList<>();
//
//        for(int i=0; i<10; i++){
//            Member member = memberRepository.getWithRoles("user"+i+"@aaa.com");
//            log.info("테스트=>{}", member);
//            list.add(modelMapper.map(member, MemberDTO.class));
//        }
//        log.info("리스트:{}",list);
//
//        Map<String, List<String>> map = new HashMap<>();
//        map.put("user", new ArrayList<>());
//        map.put("manager", new ArrayList<>());
//        map.put("admin", new ArrayList<>());
//
//        for(MemberDTO i : list){ //조건문 순서 가장 큰 것 부터
//            if(i.getMemberRoleList().contains(MemberRole.ADMIN)){
//                map.get("admin").add(i.getEmail());
//            }
//            else if(i.getMemberRoleList().contains(MemberRole.MANAGER)){
//                map.get("manager").add(i.getEmail());
//            }
//            else if(i.getMemberRoleList().contains(MemberRole.USER)){
//                map.get("user").add(i.getEmail());
//            }
//        }
//        log.info("map=>{}", map);
//        //{"user":[], "manager":[], "admin":[]}
//        //대응되는 email 저장
//    }
//
//    private MemberDTO toDTO(Member member){
//        return MemberDTO.builder()
//                .email(member.getEmail())
//                .memberRoleList(member.getMemberRoleList())
//                .nickname(member.getNickname())
//                .pw(member.getPw())
//                .social(member.isSocial())
//                .build();
//    }

//    @Test
//    public void testRead3() {
//        // 1) user1 ~ user9 (user0 제외)
//        List<MemberDTO> resultList = new ArrayList<>();
//        for (int i = 1; i <= 9; i++) {
//            var entity = memberRepository.getWithRoles("user" + i + "@aaa.com");
//            if (entity == null) continue; // 방어
//            MemberDTO member = toDTO(entity);
//            if (member == null) continue;
//            log.info("{}", member);
//            resultList.add(member);
//        }
//
//        // 2) 역할별 이메일 수집용 맵 초기화 (비어있어도 키 존재)
//        Map<String, List<String>> map = new HashMap<>();
//        map.put("admin",   new ArrayList<>());
//        map.put("manager", new ArrayList<>());
//        map.put("user",    new ArrayList<>());
//
//        // 역할 → 키 문자열 매핑
//        Map<MemberRole, String> roleKey = new EnumMap<>(MemberRole.class);
//        roleKey.put(MemberRole.ADMIN,   "admin");
//        roleKey.put(MemberRole.MANAGER, "manager");
//        roleKey.put(MemberRole.USER,    "user");
//
//        // 3) 단일 루프: 회원별 보유 역할을 돌며 바로 추가
//        for (MemberDTO m : resultList) {
//            if (m.getMemberRoleList() == null) continue;
//            for (MemberRole r : m.getMemberRoleList()) {
//                String k = roleKey.get(r);
//                if (k == null) continue; // 매핑 안 된 역할은 스킵
//                map.get(k).add(m.getEmail());
//            }
//        }
//
//        log.info("map: {}", map);
//    }
}
