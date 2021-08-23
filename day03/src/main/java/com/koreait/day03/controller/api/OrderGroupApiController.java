package com.koreait.day03.controller.api;

import com.koreait.day03.ifs.CrudInterface;
import com.koreait.day03.model.network.Header;
import com.koreait.day03.model.network.request.OrderGroupApiRequest;
import com.koreait.day03.model.network.response.OrderGroupApiResponse;
import com.koreait.day03.service.OrderGroupApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController  //호출해서 rest로 사용하는 어노테이션
@RequestMapping("/api/ordergroup") //api/user로 호출하면 이쪽으로 오게됨
@RequiredArgsConstructor
public class OrderGroupApiController implements CrudInterface<OrderGroupApiRequest, OrderGroupApiResponse> {

    private final OrderGroupApiLogicService orderGroupApiLogicService;

    @Override
    @PostMapping("")
    public Header<OrderGroupApiResponse> create(@RequestBody Header<OrderGroupApiRequest> request) {
        System.out.println(request);
        return orderGroupApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<OrderGroupApiResponse> read(@PathVariable(name="id") Long id) {
        return orderGroupApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<OrderGroupApiResponse> update(@RequestBody Header<OrderGroupApiRequest> request) {
        System.out.println(request);
        return orderGroupApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header<OrderGroupApiResponse> delete(@PathVariable(name="id") Long id) {
        return orderGroupApiLogicService.delete(id);
    }
}
