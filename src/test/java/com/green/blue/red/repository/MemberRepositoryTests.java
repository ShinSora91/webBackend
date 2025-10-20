package com.green.blue.red.repository;

import com.green.blue.red.domain.Member;
import com.green.blue.red.domain.MemberRole;
import com.green.blue.red.domain.Product;
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
    @Test
    public void testInsertMember2() {

        Member member = Member.builder()
                .email("memo0754@naver.com")
                .pw(passwordEncoder.encode("1111"))
                .nickname("신소라")
                .build();
        member.addRole(MemberRole.USER);
        member.addRole(MemberRole.MANAGER);
        member.addRole(MemberRole.ADMIN);
        memberRepository.save(member);
    }
}
