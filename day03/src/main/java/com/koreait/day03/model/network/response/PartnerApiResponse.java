package com.koreait.day03.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartnerApiResponse {
    private Long id;
    private String name;
    private String status;
    private String address;
    private String callCenter;
    private String businessNumber;
    private String ceoName;
}
