package com.griffith.rj_spring_boot.controller;

import com.griffith.rj_spring_boot.common.R;
import com.griffith.rj_spring_boot.dto.DishDto;
import com.griffith.rj_spring_boot.service.CategoryService;
import com.griffith.rj_spring_boot.service.DishFlavorService;
import com.griffith.rj_spring_boot.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {
    @Resource
    private DishService dishService;

    @Resource
    private DishFlavorService dishFlavorService;

    @Resource
    private CategoryService categoryService;

    public R<String> save(@RequestBody DishDto dishDto){
        log.info(dishDto.toString());
        return R.success("add dish success");
    }
}
