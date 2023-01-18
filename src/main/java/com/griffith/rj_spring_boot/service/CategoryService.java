package com.griffith.rj_spring_boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.griffith.rj_spring_boot.entity.Category;

public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
