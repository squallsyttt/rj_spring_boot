package com.griffith.rj_spring_boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.griffith.rj_spring_boot.entity.ShoppingCart;
import com.griffith.rj_spring_boot.mapper.ShoppingCartMapper;
import com.griffith.rj_spring_boot.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
