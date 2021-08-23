package com.koreait.day03.service;

import com.koreait.day03.ifs.CrudInterface;
import com.koreait.day03.model.entity.DtUser;
import com.koreait.day03.model.enumclass.DtUserStatus;
import com.koreait.day03.model.network.Header;
import com.koreait.day03.model.network.request.UserApiRequest;
import com.koreait.day03.model.network.response.UserApiResponse;
import com.koreait.day03.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service //서비스레이어, 내부에서 자바로직을 처리함 [로직: 서비스에서 쓰는 단위테스트]
@RequiredArgsConstructor


//단위테스트에서 시행했던 내용 여기서 수행
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {
    //DB JPA를 이용해 여기서 처리

    private final UserRepository userRepository; //확인

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        UserApiRequest userApiRequest = request.getData();
        DtUser user = DtUser.builder()
                .userid(userApiRequest.getUserid())
                .userpw(userApiRequest.getUserpw())
                .hp(userApiRequest.getHp())
                .email(userApiRequest.getEmail())
                .status(DtUserStatus.REGISTERED)
                .build();
        DtUser newUser = userRepository.save(user);
        return response(newUser);

    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        return userRepository.findById(id) //id에 의한 찾은 값을 넣음
            .map(user -> response(user))
                .orElseGet(
                        () -> Header.ERROR("데이터 없음")
                );
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        UserApiRequest userApiRequest = request.getData();
        Optional<DtUser> optional = userRepository.findById(userApiRequest.getId());

        return optional.map(user ->{
            user.setUserid(userApiRequest.getUserid());
            user.setUserpw(userApiRequest.getUserpw());
            user.setHp(userApiRequest.getHp());
            user.setEmail(userApiRequest.getEmail());
            user.setStatus(userApiRequest.getStatus());
            return user; // user에 입력된 데이터 반환
        }).map(user -> userRepository.save(user)) // 데이터 저장 유무에 따라 아래 출려
                .map(user -> response(user))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<DtUser> optional = userRepository.findById(id);
        return optional.map(dtUser -> {
            userRepository.delete(dtUser);
            return  Header.OK();
        }).orElseGet(() -> Header.ERROR("테이터 없음"));
    }


    private Header<UserApiResponse> response(DtUser user){
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .userid(user.getUserid())
                .userpw(user.getUserpw())
                .email(user.getEmail())
                .hp(user.getHp())
                .regDate(user.getRegDate())
                .status(user.getStatus())
                .build();
        return Header.OK(userApiResponse);

    }
}
