package com.koreait.day02.repository;

import com.koreait.day02.Day02ApplicationTests;
import com.koreait.day02.model.entity.DtUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class DtUserRepositoryTest extends Day02ApplicationTests {

    @Autowired
    private UserRepository UserRepository;

    @Test //단위테스트로 사용, 아래 메소드만 실행되며 테스트 됨
    public void create(){
        //빌더 안에 데이터를 연결하여 메소드 체이닝 처럼 사용 / .userid("admin")
        DtUser dtUser = DtUser.builder()
                //dto(adminuser)에 빌드 어노테이션이 걸려잇으므로 메소드 체이닝 사용가능
                .userid("melon") //세터가 된 상태
                .userpw("1234")
                .hp("사용자")
                .email("melon@banana.com")
                .regDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        DtUser newDtUser = UserRepository.save(dtUser);
        //jpa로 만들어진 레포지토리에 저장하면 테이블에 데이터가 저장됨
        //save 하는 순간 hibernate로 inserte 문이 동작하며 테이블에 데이터가 저장됨

        // Assertions.assertNotNull(newAdminUser); //값이 들어갓는지 안들어 갔는지 체크하는 메소드

        /*
        //[빌드를 쓰지않는경우]
        DtUser dtUser1 = new DtUser();

        dtUser1.setUserid("banana");
        dtUser1.setUserpw("1212");
        dtUser1.setHp("010-1212-1212");
        dtUser1.setEmail("bana@bana.com");
        dtUser1.setRegDate(LocalDateTime.now());

        DtUser newdtUser1 = UserRepository.save(dtUser1);

         */
    }
    @Test
    public void read(){
        // select * from dtUser where userid=?

//        Optional<DtUser> dtUser = UserRepository.findByUserid("banana");
//        dtUser.ifPresent(selectUser ->{
//            System.out.println("user : " + selectUser);
//            System.out.println("userid : " + selectUser.getUserid());
//            System.out.println("userpw : " + selectUser.getUserpw());
//            System.out.println("hp : " + selectUser.getHp());
//            System.out.println("email : " + selectUser.getEmail());
//        });
        DtUser dtUser = UserRepository.findFirstByHpOrderByIdDesc("010-2222-2222");
        if(dtUser != null) {
            System.out.println("데이터가 존재합니다");
        }else{
            System.out.println("데이터가 존재하지않습니다");;
        }
    }

    @Test
    public void update(){
        Optional<DtUser> dtUser = UserRepository.findByUserid("banana");
        dtUser.ifPresent(selectUser ->{ //selectUser 임의로 지음
            selectUser.setEmail("banana@naver.com");
            selectUser.setHp("010-0000-0000");
            selectUser.setUpdateDate(LocalDateTime.now());
            UserRepository.save(selectUser); //유저 레포지토리에 저장
        });
    }

    @Test
    public void delete() {
        Optional<DtUser> dtUser = UserRepository.findByUserid("banana");
        dtUser.ifPresent(selectUser -> { //dtUser객체를 화살표 함수로 selectUser로 정의 하여 아래 delete 실행
            UserRepository.delete(selectUser);
        });
        Optional<DtUser> deleteUser = UserRepository.findByUserid("banana");
        if (deleteUser.isPresent()) { //데이터 존재여부 확인 (옵셔널로 받아서 널 체크가 안됨)
            System.out.println("삭제실패!");
        }else {
            System.out.println("삭제성공!");
        }
    }
}
