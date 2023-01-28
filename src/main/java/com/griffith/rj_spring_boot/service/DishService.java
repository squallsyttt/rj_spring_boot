package com.griffith.rj_spring_boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.griffith.rj_spring_boot.dto.DishDto;
import com.griffith.rj_spring_boot.entity.Dish;

public interface DishService extends IService<Dish> {
    void saveWithFlavor(DishDto dishDto);

    DishDto getByIdWithFlavor(Long id);

    void updateWithFlavor(DishDto dishDto);
}
