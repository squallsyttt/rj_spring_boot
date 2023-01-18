package com.griffith.rj_spring_boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.griffith.rj_spring_boot.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
