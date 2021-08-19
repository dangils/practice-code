package com.koreait.day02.repository;

import com.koreait.day02.Day02ApplicationTests;
import com.koreait.day02.model.entity.Item;
import com.koreait.day02.model.entity.OrderDetail;
import com.koreait.day02.model.entity.OrderGroup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class OrderGroupRepositoryTest extends Day02ApplicationTests {

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Test
    public void create(){
        OrderGroup orderGroup = OrderGroup.builder()
                .orderType("ALL")
                .status("미결제")
                .revAddress("서울시 서초구 양재동")
                .revName("배에리")
                .paymentType("현금")
                .totalPrice(BigDecimal.valueOf(4000000))
                .totalQuantity(4)
                .regDate(LocalDateTime.now())
                .orderAt(LocalDateTime.now())
                .userid(45L)
                .build();
        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);

    }

    @Test
    public void read(){
        Optional<OrderGroup> orderGroup= orderGroupRepository.findFirstByIdOrderByIdDesc(2L);
        orderGroup.ifPresent(selectgroup ->{
            System.out.println(selectgroup.getId());
            System.out.println(selectgroup.getStatus());
            System.out.println(selectgroup.getTotalPrice());
            System.out.println(selectgroup.getRegDate());
        });
    }

    @Test
    public void delete() {
        Optional<OrderGroup> orderGroup = orderGroupRepository.findById(3L);
        orderGroup.ifPresent(selectUser -> { //dtUser객체를 화살표 함수로 selectUser로 정의 하여 아래 delete 실행
            orderGroupRepository.delete(selectUser);
        });
        Optional<OrderGroup> delorderGroup = orderGroupRepository.findById(3L);
        if (delorderGroup.isPresent()) { //데이터 존재여부 확인 (옵셔널로 받아서 널 체크가 안됨)
            System.out.println("삭제실패!");
        }else {
            System.out.println("삭제성공!");
        }
    }

    @Test
    public void update(){
        Optional<OrderGroup> orderGroup = orderGroupRepository.findById(3L);
        orderGroup.ifPresent(selectcategory ->{ //selectUser 임의로 지음
            selectcategory.setRevName("이메론");
            selectcategory.setPaymentType("순금");
            selectcategory.setTotalQuantity(10);
            orderGroupRepository.save(selectcategory); //유저 레포지토리에 저장
        });
    }
}
