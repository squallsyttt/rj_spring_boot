package com.griffith.rj_spring_boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.griffith.rj_spring_boot.common.R;
import com.griffith.rj_spring_boot.entity.User;
import com.griffith.rj_spring_boot.service.UserService;
import com.griffith.rj_spring_boot.utils.SMSUtils;
import com.griffith.rj_spring_boot.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 用来存放信息验证码
     *
     * @param user    User
     * @param session HttpSession 用来本地暂存生成的验证码
     * @return R<String>
     */
    @PostMapping("/sendMessage")
    public R<String> sendMsg(@RequestBody User user, HttpSession session) {
        String phone = user.getPhone();
        // 这边其实不严谨 这是完全指望前端验证手机号了
        if (StringUtils.isNotEmpty(phone)) {
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code={}", code);

            // Ali SMS
            // SMSUtils.sendMessage("rj_spring_boot","templateCode",phone,code);
            session.setAttribute(phone,code);
            return R.success("send success");
        }
        return R.error("send fail");
    }

    @PostMapping("/login")
    public R<User> login(@RequestBody Map map,HttpSession session){
        log.info("here is map" + map.toString());
        String phone = map.get("phone").toString();
        String code = map.get("code").toString();

        Object codeInSession = session.getAttribute(phone);

        if(codeInSession !=null && codeInSession.equals(code)){
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone,phone);
            User user = userService.getOne(queryWrapper);
            if(user == null){
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user",user.getId());
            return R.success(user);
        }
        return R.error("login fail");

    }
}
