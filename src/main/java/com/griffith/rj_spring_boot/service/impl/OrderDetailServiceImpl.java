package com.griffith.rj_spring_boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.griffith.rj_spring_boot.entity.OrderDetail;
import com.griffith.rj_spring_boot.mapper.OrderDetailMapper;
import com.griffith.rj_spring_boot.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
