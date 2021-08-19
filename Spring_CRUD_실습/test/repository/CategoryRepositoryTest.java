package com.koreait.day02.repository;

import com.koreait.day02.Day02ApplicationTests;
import com.koreait.day02.model.entity.Category;
import com.koreait.day02.model.entity.DtUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class CategoryRepositoryTest extends Day02ApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void create() {
        Category category = Category.builder()
                .type("가전2")
                .title("삼성 TV")
                .regDate(LocalDateTime.now())
                .build();
        Category newCategory = categoryRepository.save(category);
    }

    @Test
    public void read(){
        Optional<Category> category= categoryRepository.findFirstByTitleOrderByIdDesc("파나메라");
        category.ifPresent(selectcategory ->{
            System.out.println("차 : " + selectcategory.getTitle());
        });
    }

    @Test
    public void delete() {
        Optional<Category> category = categoryRepository.findById(5L);
        category.ifPresent(selectUser -> { //dtUser객체를 화살표 함수로 selectUser로 정의 하여 아래 delete 실행
            categoryRepository.delete(selectUser);
        });
        Optional<Category> delcategory = categoryRepository.findById(5L);
        if (delcategory.isPresent()) { //데이터 존재여부 확인 (옵셔널로 받아서 널 체크가 안됨)
            System.out.println("삭제실패!");
        }else {
            System.out.println("삭제성공!");
        }
    }

    @Test
    public void update(){
        Optional<Category> category = categoryRepository.findById(1L);
        category.ifPresent(selectcategory ->{ //selectUser 임의로 지음
            selectcategory.setType("붕붕카");
            selectcategory.setTitle("파나메라");
            selectcategory.setUpdateDate(LocalDateTime.now());
            categoryRepository.save(selectcategory); //유저 레포지토리에 저장
        });
    }
}
