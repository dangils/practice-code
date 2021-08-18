package com.koreait.day02.repository;

import com.koreait.day02.Day02ApplicationTests;
import com.koreait.day02.model.entity.DtUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class DtUserRepositoryTest extends Day02ApplicationTests {

    @Autowired
    private UserRepository UserRepository;

    @Test //단위테스트로 사용, 아래 메소드만 실행되며 테스트 됨
    public void create(){
        //빌더 안에 데이터를 연결하여 메소드 체이닝 처럼 사용 / .userid("admin")
        DtUser dtUser = DtUser.builder()
                //dto(adminuser)에 빌드 어노테이션이 걸려잇으므로 메소드 체이닝 사용가능
                .userid("user") //세터가 된 상태
                .userpw("1234")
                .hp("사용자")
                .email("apple@apple.com")
                .regDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        DtUser newDtUser = UserRepository.save(dtUser);
        //jpa로 만들어진 레포지토리에 저장하면 테이블에 데이터가 저장됨
        //save 하는 순간 hibernate로 inserte 문이 동작하며 테이블에 데이터가 저장됨

        // Assertions.assertNotNull(newAdminUser); //값이 들어갓는지 안들어 갔는지 체크하는 메소드

    }
}
