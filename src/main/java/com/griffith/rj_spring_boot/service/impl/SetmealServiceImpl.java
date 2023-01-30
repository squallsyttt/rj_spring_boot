package com.griffith.rj_spring_boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.griffith.rj_spring_boot.common.CustomException;
import com.griffith.rj_spring_boot.dto.SetmealDto;
import com.griffith.rj_spring_boot.entity.Setmeal;
import com.griffith.rj_spring_boot.entity.SetmealDish;
import com.griffith.rj_spring_boot.mapper.SetmealDishMapper;
import com.griffith.rj_spring_boot.mapper.SetmealMapper;
import com.griffith.rj_spring_boot.service.SetmealDishService;
import com.griffith.rj_spring_boot.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Resource
    private SetmealDishService setmealDishService;
    /**
     * add new with dish
     *
     * @param setmealDto SetmealDto
     */
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        //保存 setmeal表的基本信息 执行insert操作
        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();

        //照理说这个赋值操作可以省略 但是idea报波浪了 顺着改下
        setmealDishes = setmealDishes.stream().map((item)-> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        setmealDishService.saveBatch(setmealDishes);
    }

    /**
     * remove with dish
     *
     * @param ids List<Long>
     */
    @Transactional
    public void removeWithDish(List<Long> ids) {
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId,ids);
        queryWrapper.eq(Setmeal::getStatus,1);

        int count = (int) this.count(queryWrapper);
        if(count >0){
            throw new CustomException("setmeal is on seal cant remove!");
        }

        //如果可以删除，先删除setmeal中的数据
        this.removeByIds(ids);
        //delete from setmeal_dish where setmeal_id in (1,2,3)
        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SetmealDish::getSetmealId,ids);
        setmealDishService.remove(lambdaQueryWrapper);
    }
}
