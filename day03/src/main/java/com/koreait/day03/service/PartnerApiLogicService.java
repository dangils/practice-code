package com.koreait.day03.service;

import com.koreait.day03.ifs.CrudInterface;
import com.koreait.day03.model.entity.Partner;
import com.koreait.day03.model.network.Header;
import com.koreait.day03.model.network.request.PartnerApiRequest;
import com.koreait.day03.model.network.response.PartnerApiResponse;
import com.koreait.day03.repository.ItemRepository;
import com.koreait.day03.repository.PartnerRepository;
import com.koreait.day03.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service //서비스레이어, 내부에서 자바로직을 처리함 [로직: 서비스에서 쓰는 단위테스트]
@RequiredArgsConstructor

public class PartnerApiLogicService implements CrudInterface<PartnerApiRequest, PartnerApiResponse> {

    private  final PartnerRepository partnerRepository;

    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
        PartnerApiRequest partnerApiRequest = request.getData();
        Partner partner = Partner.builder()
                .id(partnerApiRequest.getId())
                .name(partnerApiRequest.getName())
                .status(partnerApiRequest.getStatus())
                .address(partnerApiRequest.getAddress())
                .callCenter(partnerApiRequest.getCallCenter())
                .businessNumber(partnerApiRequest.getBusinessNumber())
                .ceoName(partnerApiRequest.getCeoName())
                .build();
        Partner newPartner = partnerRepository.save(partner);
        return response(newPartner);
    }


//    {
//        "transaction_time":"2021-08-23",
//            "resultCode":"OK",
//            "description":"OK",
//            "data":{
//        "name":"샘숭",
//                "":"1234",
//                "email":"cherry@cherry.com",
//                "hp":"010-1234-1234"
//    }
//    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {
        return partnerRepository.findById(id)
                .map(partner -> response(partner))
                .orElseGet(
                        () -> Header.ERROR("데이터 없음")
                );
    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {

        PartnerApiRequest partnerApiRequest = request.getData();
        Optional<Partner> optional = partnerRepository.findById(partnerApiRequest.getId());

        return optional.map(partner -> {
            partner.setId(partnerApiRequest.getId());
            partner.setName(partnerApiRequest.getName());
            partner.setStatus(partnerApiRequest.getStatus());
            partner.setAddress(partnerApiRequest.getAddress());
            partner.setCallCenter(partnerApiRequest.getCallCenter());
            partner.setBusinessNumber(partnerApiRequest.getBusinessNumber());
            partner.setCeoName(partnerApiRequest.getCeoName());
            return partner;
        }).map(partner -> partnerRepository.save(partner))
                .map(partner -> response(partner))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Partner> optional = partnerRepository.findById(id);
        return optional.map(partner -> {
            partnerRepository.delete(partner);
            return Header.OK();
        }).orElseGet(()-> Header.ERROR("데이터 없음"));
    }

    private Header<PartnerApiResponse> response(Partner partner){
        PartnerApiResponse partnerApiResponse = PartnerApiResponse.builder()
                .id(partner.getId())
                .name(partner.getName())
                .status(partner.getStatus())
                .address(partner.getAddress())
                .callCenter(partner.getCallCenter())
                .businessNumber(partner.getBusinessNumber())
                .ceoName(partner.getCeoName())
                .build();
        return Header.OK(partnerApiResponse);
    }
}
