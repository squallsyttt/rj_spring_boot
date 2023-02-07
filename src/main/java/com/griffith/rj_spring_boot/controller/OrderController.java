package com.griffith.rj_spring_boot.controller;

import com.griffith.rj_spring_boot.common.R;
import com.griffith.rj_spring_boot.entity.Orders;
import com.griffith.rj_spring_boot.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrdersService ordersService;

    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){
        log.info("order info:{}",orders);
        //TODO
        ordersService.submit(orders);
        return R.success("submit order success");
    }

}
