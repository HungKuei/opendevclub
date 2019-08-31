package com.opendev.controller;

import com.opendev.base.APIResponse;
import com.opendev.enums.ResultStatusCode;
import com.opendev.model.User;
import com.opendev.service.UserService;
import com.opendev.util.JsonResult;
import com.opendev.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get/")
    public APIResponse<User> getById(@RequestParam("id") Integer id){
        User user = userService.getUserById(id);
        return JsonResult.success(ResultStatusCode.SUCCESS, user);
    }

    @GetMapping("/get/page/")
    public PageResult<User> getByPages(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows){
        List<User> users = userService.getUsersByPages(page, rows);
        return new PageResult<>(ResultStatusCode.SUCCESS, users);
    }

    @PostMapping("/add")
    public APIResponse add(@RequestBody User user){
        if (userService.addUser(user)){
            return JsonResult.success();
        }else{
            return JsonResult.error();
        }
    }

    @PutMapping("/edit")
    public APIResponse edit(@RequestBody User user){
        if (userService.updateUser(user)){
            return JsonResult.success();
        }else{
            return JsonResult.error();
        }
    }
}
