package com.griffith.rj_spring_boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.griffith.rj_spring_boot.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
}
