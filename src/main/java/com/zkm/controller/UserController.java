package com.zkm.controller;

import com.zkm.common.result.JsonResult;
import com.zkm.common.result.ResultTool;
import com.zkm.model.UserZkm;
import com.zkm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    JsonResult register(UserZkm userZkm) {
        return userService.registerUser(userZkm) == 1 ? ResultTool.success() : ResultTool.fail();
    }

    @GetMapping("/userInfo")
    JsonResult getUserInfo(@RequestParam String username) throws Exception {
        UserZkm userZkm = userService.findAllInfoByUsername(username);
        if(Objects.isNull(userZkm)){
            throw  new Exception("用户名非法");
        }
        return ResultTool.success(userZkm);
    }

    @PostMapping("/user/logout")
    JsonResult logout(@RequestParam String username) throws Exception {
        int i =  userService.logout(username);
        return ResultTool.success(i);
    }

    @GetMapping("/find/user")
    JsonResult findAllUserByUsername(@RequestParam String username) throws Exception {
        List<UserZkm> userZkmList =  userService.findAllUserByUsername(username);
        return ResultTool.success(userZkmList);
    }
}
