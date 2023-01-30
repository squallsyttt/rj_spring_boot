package com.griffith.rj_spring_boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.griffith.rj_spring_boot.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserService extends IService<User> {
}
