package com.griffith.rj_spring_boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.griffith.rj_spring_boot.dto.SetmealDto;
import com.griffith.rj_spring_boot.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    /**
     * add new with dish
     * @param setmealDto SetmealDto
     */
    void saveWithDish(SetmealDto setmealDto);

    /**
     * remove with dish
     * @param ids List<Long>
     */
    void removeWithDish(List<Long> ids);
}
