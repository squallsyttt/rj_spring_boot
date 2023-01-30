package com.griffith.rj_spring_boot.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.griffith.rj_spring_boot.entity.SetmealDish;
import com.griffith.rj_spring_boot.mapper.SetmealDishMapper;
import com.griffith.rj_spring_boot.service.SetmealDishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SetmealDishServiceImpl extends ServiceImpl<SetmealDishMapper, SetmealDish> implements SetmealDishService{
}
