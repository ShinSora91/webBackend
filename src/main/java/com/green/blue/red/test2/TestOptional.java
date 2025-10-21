package com.green.blue.red.test2;

import com.green.blue.red.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
public class TestOptional {

    public static void main(String[] args) {
        String name1 = Optional.ofNullable("loose").orElse("test");
        log.info("1 -> name1= {}", name1);

        String name2 = Optional.ofNullable("")
                .filter(i->!i.isBlank()).orElseGet(() -> "test");
        log.info("2 -> name2= {}", name2);

        List<String> strList = Arrays.asList(
                "kihyun","sora","byeongkuk","shinyung","yura",
                "star","minsuk","hyungwo","jongmin","jaesuk","gunho"
        );
        //우리반 이름을 영어로 적고, 반복문을 활용하여 의미를 파악
        String name3 = Optional.of("kwonkihyun")
                .filter(i->!(i.length()%5==0)).orElseGet(() -> "5의 배수가 아닙니다");
        log.info("3 -> name3= {}", name3);
        int rrr = "홍길동".indexOf("길");
        log.info("rrr:{}", rrr);

        String name4 = Optional.of(strList.get(0))
                .filter(i -> !(i.startsWith("k"))).orElseGet(() -> "첫글자가 k로 시작되지않는다");
        log.info("4 -> name4= {}", name4);

        strList.forEach(i -> log.info("첫글자=> {}", i.substring(0,1)));//첫글자 자르기


        for(int i=0; i<strList.size(); i++){
            String name5 = Optional.of(strList.get(i))
                    .filter(j -> !(j.startsWith("s"))).orElseGet(() -> "첫글자 s아님");
            //filter : startsWith("s") s면 true !이 붙으면 false
            //!이 없으면 true이기 때문에 첫글자가 s가 아닌 문자열들은 전부 첫글자 s 아님 출력
            //!을 붙이면 반대로 첫글자가 s인 문자열들이 첫글자 s아님 출력
            log.info("5 -> name5= {}", name5);
        }

        System.out.println("===========================================");

        String f = "";
        for(int i=0; i<strList.size(); i++){
            f = strList.get(i).substring(0,1);
            String name6 = Optional.of(f)
                    .filter(k -> k.contains("s")).orElse("s가 없습니다");
            log.info("6 -> name6= {}", name6);
        }

        Member m = Member.builder().nickname("a").build();
        Optional<Member> om = Optional.of(m);

        Member member1 = om.orElse(m);
        Member member2 = om.orElseGet(() -> {
            return Member.builder()
                    .email("aa@a.com")
                    .nickname("b")
                    .build();
        });
    }
}
