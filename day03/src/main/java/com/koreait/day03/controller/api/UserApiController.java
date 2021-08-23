package com.koreait.day03.controller.api;


import com.koreait.day03.service.UserApiLogicService;
import com.koreait.day03.ifs.CrudInterface;
import com.koreait.day03.model.network.Header;
import com.koreait.day03.model.network.request.UserApiRequest;
import com.koreait.day03.model.network.response.UserApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController  //호출해서 rest로 사용하는 어노테이션
@RequestMapping("/api/user") //api/user로 호출하면 이쪽으로 오게됨
@RequiredArgsConstructor
public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {
    // CrudInterface 인터페이스의 UserApiRequest 제네릭,UserApiRequest 제네릭 형태로 UserApiController 상속을 받아옴
    //인터페이스 UserApiController에 정의된 메소드 필수로 구현해야함 (generater -> overrideMethod)


    private final UserApiLogicService userApiLogicService; //전역으로 상수 설정

    /*
        {
            "transaction_time":"2021-08-23",
            "resultCode":"OK",
            "description":"OK",
            "data":{
                "userid":"cherry",
                "userpw":"1234",
                "email":"cherry@cherry.com",
                "hp":"010-1234-1234"
            }
        }
     */

    @Override
    @PostMapping("") // /api/user (post) 방식으로 호출하면 이쪽으로 옴 (http://127.0.0.1/api/User (post) )
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
        //@RequestBody: 리퀘스트(사용자로 부터 전달된 파라미터) 데이터를 받아서 전달하기 위한 어노테이션
        System.out.println(request);
        return userApiLogicService.create(request);
    }


//        @Override
//        @PostMapping("") // /api/user
//        public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
//            System.out.println(request);
//            return userApiLogicService.create(request);
//        }

    /*

    {
        "transaction_time":"2021-08-23",
        "resultCode":"OK",
        "description":"OK",
        "data":{
            "userid":"cherry",
        }
    }
 */

    @Override
    @GetMapping("{id}") // /api/user/{id} 로 호출하면 이쪽으로 옴 (get) 방식
    public Header<UserApiResponse> read(@PathVariable(name="id") Long id) {
        //@PathVariable(name="id") : 사용자 전달 이름 , Long id: 여기 메소드에서 쓸 이름
        return userApiLogicService.read(id); //로직서비스에 id 전달
    }


//# update
//    {
//        "transaction_time":"2021-08-23",
//            "resultCode" : "OK",
//            "description" : "OK",
//            "data" : {
//        "id" :"102",
//                "userid" : "cherry",
//                "userpw" : "1010",
//                "email" : "cherry@cherry.con",
//                "hp" : "010-3333-3333"
//    }
//    }

    @Override
    @PutMapping("") // /api/user (put)
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
        System.out.println(request);
        return userApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}") // /api/user/{id}  아이디를 전달 받아 삭제
    public Header<UserApiResponse> delete(@PathVariable(name="id") Long id) {
        return userApiLogicService.delete(id);
    }

    //확장성 있게 만들기 위해 규칙적인 틀을 만들어 상속을 받아옴
    //특정 규격을 만들어 상속받아옴

}
