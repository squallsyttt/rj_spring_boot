package com.griffith.rj_spring_boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.griffith.rj_spring_boot.common.CustomException;
import com.griffith.rj_spring_boot.entity.Category;
import com.griffith.rj_spring_boot.entity.Dish;
import com.griffith.rj_spring_boot.entity.Setmeal;
import com.griffith.rj_spring_boot.mapper.CategoryMapper;
import com.griffith.rj_spring_boot.service.CategoryService;
import com.griffith.rj_spring_boot.service.DishService;
import com.griffith.rj_spring_boot.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 添加查询条件，根据分类id查询   这边::看的我恍惚 java里面的:: 是和php取静态变量/方法不同的一个点
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int countDish = (int) dishService.count(dishLambdaQueryWrapper);

        // 查询当前分类是否关联菜品，若关联，抛异常
        if(countDish > 0){
            throw new CustomException("当前分类下有关联dish，无法删除");
        }
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 添加查询条件，根据分类id查询
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int countSetmeal = (int) setmealService.count(setmealLambdaQueryWrapper);

        // 查询当前分类是否关联套餐，若关联，抛异常
        if(countSetmeal > 0){
            throw new CustomException("当前分类下有关联set meal，无法删除");
        }
        super.removeById(id);
    }
}
