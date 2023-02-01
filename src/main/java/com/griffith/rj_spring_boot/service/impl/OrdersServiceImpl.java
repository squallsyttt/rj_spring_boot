package com.griffith.rj_spring_boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.griffith.rj_spring_boot.entity.Orders;
import com.griffith.rj_spring_boot.mapper.OrdersMapper;
import com.griffith.rj_spring_boot.service.OrdersService;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService{
}
