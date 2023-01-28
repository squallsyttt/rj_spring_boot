package com.griffith.rj_spring_boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.griffith.rj_spring_boot.entity.DishFlavor;
import com.griffith.rj_spring_boot.mapper.DishFlavorMapper;
import com.griffith.rj_spring_boot.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
