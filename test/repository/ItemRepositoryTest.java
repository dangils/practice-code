package com.koreait.day02.repository;

import com.koreait.day02.Day02ApplicationTests;
import com.koreait.day02.model.entity.Category;
import com.koreait.day02.model.entity.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class ItemRepositoryTest extends Day02ApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create(){
        Item item = Item.builder()
                .name("엘지 냉장고2")
                .status("판매중")
                .title("양문형 냉장고")
                .content("아주 시원해요")
                .price(BigDecimal.valueOf(2000000))
                .regDate(LocalDateTime.now())
                .partnerId(4L)
                .build();
        Item newItem = itemRepository.save(item);
    }

    @Test
    public void read(){
        Optional<Item> item = itemRepository.findFirstByNameOrderByIdDesc("삼성 노트북");
        item.ifPresent(selectitem ->{
            System.out.println("제품 : " + selectitem.getName());
        });
    }

    @Test
    public void delete() {
        Optional<Item> item = itemRepository.findById(5L);
        item.ifPresent(selectUser -> { //dtUser객체를 화살표 함수로 selectUser로 정의 하여 아래 delete 실행
            itemRepository.delete(selectUser);
        });
        Optional<Item> delitem = itemRepository.findById(5L);
        if (delitem.isPresent()) { //데이터 존재여부 확인 (옵셔널로 받아서 널 체크가 안됨)
            System.out.println("삭제실패!");
        }else {
            System.out.println("삭제성공!");
        }
    }
//
    @Test
    public void update(){
        Optional<Item> item = itemRepository.findById(3L);
        item.ifPresent(selectcategory ->{ //selectUser 임의로 지음
            selectcategory.setName("가나");
            selectcategory.setTitle("초콜릿");
            selectcategory.setUpdateDate(LocalDateTime.now());
            itemRepository.save(selectcategory); //유저 레포지토리에 저장
        });
    }
}
