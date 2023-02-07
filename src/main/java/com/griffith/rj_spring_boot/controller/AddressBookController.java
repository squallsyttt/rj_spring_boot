package com.griffith.rj_spring_boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.griffith.rj_spring_boot.common.BaseContext;
import com.griffith.rj_spring_boot.common.R;
import com.griffith.rj_spring_boot.entity.AddressBook;
import com.griffith.rj_spring_boot.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/addressBook")
public class AddressBookController {
    @Resource
    private AddressBookService addressBookService;
    @PostMapping
    public R<AddressBook> save(@RequestBody AddressBook addressBook){
        addressBook.setUserId(BaseContext.getCurrentId());
        log.info("addressBook:{}",addressBook);

        addressBookService.save(addressBook);
        return R.success(addressBook);
    }

    /**
     * 这边的 default 前面没有/  可有可无 多几个也无妨
     * @param addressBook AddressBook
     * @return R<AddressBook>
     */
    @PutMapping("/default")
    public R<AddressBook> setDefault(@RequestBody AddressBook addressBook){
        log.info("addressBook:{}",addressBook);

        //区分 LambdaUpdateWrapper 和 LambdaQueryWrapper
        LambdaUpdateWrapper<AddressBook> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(AddressBook::getUserId,BaseContext.getCurrentId());
        updateWrapper.set(AddressBook::getIsDefault,0);
        //SQL update address_book set is_default = 0 where user_id = ?
        addressBookService.update(updateWrapper);

        addressBook.setIsDefault(1);
        //SQL:update address_book set is_default = 1 where id = ?
        addressBookService.updateById(addressBook);
        return R.success(addressBook);
    }

    /**
     * 这边的 R 无类型
     * @param id Long
     * @return R
     */
    @GetMapping("/{id}")
    public R get(@PathVariable Long id){
        AddressBook addressBook = addressBookService.getById(id);
        if(addressBook != null){
            return R.success(addressBook);
        }else {
            return R.error("empty addressBook");
        }
    }

    /**
     * 查询默认地址
     * 也无/
     * @return R<AddressBook>
     */
    @GetMapping("/default")
    public R<AddressBook> getDefault(){
        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBook::getUserId,BaseContext.getCurrentId());
        queryWrapper.eq(AddressBook::getIsDefault,1);//其实这边是硬编码 最好不要直接用1 从外部引入常量好一点

        AddressBook addressBook = addressBookService.getOne(queryWrapper);

        if(addressBook != null){
            return R.success(addressBook);
        }else {
            return R.error("没有找到对象");
        }
    }

    /**
     * return List<AddressBook>
     * @param addressBook AddressBook
     * @return List<AddressBook>
     */
    @GetMapping("/list")
    public R<List<AddressBook>> list(AddressBook addressBook){
        addressBook.setUserId(BaseContext.getCurrentId());
        log.info("addressBook:{}",addressBook);

        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null != addressBook.getUserId(),AddressBook::getUserId,addressBook.getUserId());
        queryWrapper.orderByDesc(AddressBook::getUpdateTime);

        return R.success(addressBookService.list(queryWrapper));
    }


}
