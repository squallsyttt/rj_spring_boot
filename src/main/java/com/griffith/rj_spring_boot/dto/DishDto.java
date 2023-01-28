package com.griffith.rj_spring_boot.dto;

import com.griffith.rj_spring_boot.entity.Dish;
import com.griffith.rj_spring_boot.entity.DishFlavor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)//true 就是用直接用父类的 如果某个类不继承类（默认继承object）@Data默认是false 确实jb复杂
@Data
public class DishDto extends Dish {

    // 后续分表用
    private String categoryName;
    private Integer copies;

    //dto 封装前端和dish结构不符合的json格式数据
    private List<DishFlavor> flavors = new ArrayList<>();
}
