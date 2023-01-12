package com.griffith.rj_spring_boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.griffith.rj_spring_boot.common.R;
import com.griffith.rj_spring_boot.entity.Employee;
import com.griffith.rj_spring_boot.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    //@Autowired 教程中用的autowired
    @Resource
    private EmployeeService employeeService;


    /**
     * 员工登陆
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        // 页面提交的pwd
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        // 开始查库步骤
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        // 查不到要报登陆失败
        if(emp == null){
            return R.error("登陆失败");
        }

        // 密码不匹配要报登陆失败
        if(!emp.getPassword().equals(password)){
            return R.error("登陆失败");
        }

        // 查看下状态
        if(emp.getStatus() == 0){
            return R.error("账号已禁用");
        }

        // 成功信息存入session
        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);

    }
}
