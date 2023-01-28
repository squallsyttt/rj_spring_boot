package com.griffith.rj_spring_boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.griffith.rj_spring_boot.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishFlavorMapper extends BaseMapper<DishFlavor> {
}
