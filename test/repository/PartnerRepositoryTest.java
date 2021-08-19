package com.koreait.day02.repository;

import com.koreait.day02.Day02ApplicationTests;
import com.koreait.day02.model.entity.Category;
import com.koreait.day02.model.entity.OrderGroup;
import com.koreait.day02.model.entity.Partner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class PartnerRepositoryTest extends Day02ApplicationTests {

    @Autowired
    private PartnerRepository partnerRepository;

    @Test
    public void create(){
        Partner partner = Partner.builder()
                .name("베스트샵2")
                .status("사용중")
                .address("서울시 금천구")
                .callCenter("070-4444-4444")
                .businessNumber("4444-44-4444")
                .ceoName("오지환2")
                .regDate(LocalDateTime.now())
                .categoryId(6L)
                .build();
        Partner newPartner = partnerRepository.save(partner);
    }

    @Test
    public void read(){
        Optional<Partner> partner= partnerRepository.findFirstByNameOrderByIdDesc("현대자동차");
        partner.ifPresent(selectpartner ->{
            System.out.println(selectpartner.getName());
            System.out.println(selectpartner.getStatus());
            System.out.println(selectpartner.getAddress());
        });
    }


    @Test
    public void delete() {
        Optional<Partner> partner = partnerRepository.findById(21L);
        partner.ifPresent(selectUser -> { //dtUser객체를 화살표 함수로 selectUser로 정의 하여 아래 delete 실행
            partnerRepository.delete(selectUser);
        });
        Optional<Partner> delpartner = partnerRepository.findById(21L);
        if (delpartner.isPresent()) { //데이터 존재여부 확인 (옵셔널로 받아서 널 체크가 안됨)
            System.out.println("삭제실패!");
        }else {
            System.out.println("삭제성공!");
        }
    }

    @Test
    public void update(){
        Optional<Partner> partner = partnerRepository.findById(1L);
        partner.ifPresent(selectcategory ->{ //selectUser 임의로 지음
            selectcategory.setName("삼성전자");
            selectcategory.setStatus("주가폭등");
            selectcategory.setCeoName("이메론");
            partnerRepository.save(selectcategory); //유저 레포지토리에 저장
        });
    }
}
