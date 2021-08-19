package com.koreait.day02.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(
        name="seq_group", //자바에서 사용하는 시퀀스 이름(오라클과 달라도 됨,내가 임의로 만듬)
        sequenceName = "seq_group", // 오라클의 시퀀스 이름
        initialValue = 1, //초기 벨류값
        allocationSize = 1 //증가값
)
@Builder

public class OrderGroup {
    @Id  //아이덴티한 값을 부여하기 위한 어노테이션 (identity 약자) -> 아래 어노테이션과 같이 사용 경우가 많음
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_group") //아래 id 필드가 identity 타입 (바로 아랫줄 적용되는 어노테이션)

    private Long id;
    private String orderType;
    private String status;
    private String revAddress;
    private String revName;
    private String paymentType;
    private BigDecimal totalPrice;
    private Integer totalQuantity;
    private LocalDateTime orderAt;
    private LocalDateTime arrivalDate;
    private LocalDateTime regDate;
    private Long userid;


}
