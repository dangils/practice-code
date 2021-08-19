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

public class OrderDetailRepositoryTest extends Day02ApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create(){
        OrderDetail orderDetail = OrderDetail.builder()
                .status("결제완료")
                .quantity(1)
                .totalPrice(BigDecimal.valueOf(3000000))
                .regDate(LocalDateTime.now())
                .itemId(1L)
                .orderGroupId(2L)
                .build();
        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
    }

    @Test
    public void read(){
        Optional<OrderDetail> orderDetail = orderDetailRepository.findFirstByIdOrderByIdDesc(5L);
        orderDetail.ifPresent(selectOD ->{
            System.out.println(selectOD.getId());
            System.out.println(selectOD.getStatus());
            System.out.println(selectOD.getTotalPrice());
            System.out.println(selectOD.getRegDate());
        });
    }

    @Test
    public void delete() {
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(1L);
        orderDetail.ifPresent(selectUser -> { //dtUser객체를 화살표 함수로 selectUser로 정의 하여 아래 delete 실행
            orderDetailRepository.delete(selectUser);
        });
        Optional<OrderDetail> delitem = orderDetailRepository.findById(1L);
        if (delitem.isPresent()) { //데이터 존재여부 확인 (옵셔널로 받아서 널 체크가 안됨)
            System.out.println("삭제실패!");
        }else {
            System.out.println("삭제성공!");
        }
    }

    @Test
    public void update(){
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(10L);
        orderDetail.ifPresent(selectordetail->{ //selectUser 임의로 지음
            selectordetail.setStatus("배송중");
            selectordetail.setItemId(3L);
            selectordetail.setRegDate(LocalDateTime.now());
            orderDetailRepository.save(selectordetail); //유저 레포지토리에 저장
        });
    }
}
