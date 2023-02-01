package com.griffith.rj_spring_boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.griffith.rj_spring_boot.entity.AddressBook;
import com.griffith.rj_spring_boot.mapper.AddressBookMapper;
import com.griffith.rj_spring_boot.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
