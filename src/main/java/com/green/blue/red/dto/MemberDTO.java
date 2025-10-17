package com.green.blue.red.dto;

import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Getter
@Setter
@ToString
//인증(authentication), 비밀번호 인증, 권한(Authority), 관리자, admin 인가
public class MemberDTO extends User {
    private String email;
    private String pw;
    private String nickname;
    private boolean social;
    private List<String> roleNames = new ArrayList<>();

    public MemberDTO(String email, String pw, String nickname, boolean social,
                     List<String> roleNames){
        super(email,pw, roleNames.stream().map(str ->
                new SimpleGrantedAuthority("ROLE_"+str)).toList());
        this.email=email;
        this.pw=pw;
        this.nickname=nickname;
        this.social=social;
        this.roleNames=roleNames;
    }

    public Map<String, Object> getClaims(){
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("email", email);
        dataMap.put("pw", pw);
        dataMap.put("nickname", nickname);
        dataMap.put("social", social);
        dataMap.put("roleNames", roleNames);
        return dataMap;
    }
}
