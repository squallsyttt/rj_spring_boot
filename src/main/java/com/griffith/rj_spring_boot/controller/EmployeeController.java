package com.griffith.rj_spring_boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    //@Autowired 教程中用的autowired
    @Resource
    private EmployeeService employeeService;


    /**
     * 员工登陆
     * @param request 请求对象
     * @param employee 员工对象（前端ajax传来的json对象）
     * @return 返回员工信息
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

    /**
     * 退出登陆方法
     * @param request 请求对象
     * @return 范型R
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    /**
     *
     * @param request
     * @param employee
     * @return
     */
    @PostMapping//直接用 postMapping 后面不跟路径就说明 路径就是 employee/ 可能是rest风格的写法
    public R<String> save(HttpServletRequest request,@RequestBody Employee employee){
        log.info("新增员工，员工信息：{}",employee.toString());

        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        // 获取当前登陆用户id
        Long empId = (Long) request.getSession().getAttribute("employee");

        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);

        employeeService.save(employee);
        return R.success("add success");
    }

    public R<Page> page(int page,int pageSize,String name){
        log.info("page={},pageSize={},name={}",page,pageSize,name);

    }
}
