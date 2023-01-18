package com.griffith.rj_spring_boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.griffith.rj_spring_boot.common.R;
import com.griffith.rj_spring_boot.entity.Category;
import com.griffith.rj_spring_boot.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * add category
     * @param category Category category
     * @return R<String>
     */
    @PostMapping
    public R<String> save(@RequestBody Category category){
        log.info("category:{}",category);
        categoryService.save(category);
        return R.success("add category success");
    }

    /**
     * 分页查询
     * @param page int
     * @param pageSize int
     * @return R
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize){
        //分页构造器
        Page<Category> pageInfo = new Page<>(page,pageSize);

        //查询条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        // 添加排序条件
        queryWrapper.orderByAsc(Category::getSort);

        categoryService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 删除分类
     * @param id cid
     * @return R
     */
    @DeleteMapping
    public R<String> delete(Long id){
        log.info("删除分类，id为{}",id);
        categoryService.remove(id);
        return R.success("分类信息删除成功");
    }

    /**
     * 更新
     * @param category CategoryObj
     * @return R
     */
    public R<String> update(@RequestBody Category category){
        log.info("修改分类信息:{}",category);
        categoryService.updateById(category);
        return R.success("修改分类成功");
    }
}
