package com.griffith.rj_spring_boot.dto;

import com.griffith.rj_spring_boot.entity.Setmeal;
import com.griffith.rj_spring_boot.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    /**
     * 用来对应 categoryId
     */
    private String categoryName;
}
