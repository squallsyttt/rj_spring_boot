package com.griffith.rj_spring_boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.griffith.rj_spring_boot.dto.DishDto;
import com.griffith.rj_spring_boot.entity.Dish;
import com.griffith.rj_spring_boot.entity.DishFlavor;
import com.griffith.rj_spring_boot.mapper.DishMapper;
import com.griffith.rj_spring_boot.service.DishFlavorService;
import com.griffith.rj_spring_boot.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Resource
    private DishFlavorService dishFlavorService;

    /**
     * @param dishDto DishDto
     */
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {
        //先补充基础信息到dish表
        this.save(dishDto);
        Long dishId = dishDto.getId();
        List<DishFlavor> flavors = dishDto.getFlavors();

        //idea 居然要我把map 换成peek 妈的也算合理 map用来做stream流方法 看起来就是歧义得一比
        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());
        //批量存表dish_flavor
        dishFlavorService.saveBatch(flavors);

    }

    /**
     * @param id Long
     * @return DishDto
     */
    @Override
    public DishDto getByIdWithFlavor(Long id) {
        Dish dish = this.getById(id);

        DishDto dishDto = new DishDto();
        //这边不懂 需要过一遍视频
        BeanUtils.copyProperties(dish,dishDto);

        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dish.getId());
        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper);

        dishDto.setFlavors(flavors);
        return dishDto;
    }

    /**
     * @param dishDto DishDto
     */
    @Override
    public void updateWithFlavor(DishDto dishDto) {

    }
}
