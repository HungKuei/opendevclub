package com.opendev.controller;

import com.opendev.base.APIResponse;
import com.opendev.base.PublicResultConstant;
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

    /**
     * 根据用户ID查询
     * @param userId
     * @return
     */
    @GetMapping("/get/{userId}")
    public APIResponse<User> get(@PathVariable("userId") Integer userId){
        User user = userService.getUserByUserId(userId);
        return JsonResult.success(ResultStatusCode.SUCCESS, user);
    }

    /**
     * 用户列表--分页查询
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("/get/{page}/{rows}")
    public PageResult<User> get(@PathVariable("page") Integer page, @PathVariable("rows") Integer rows){
        List<User> users = userService.getUsersByPages(page, rows);
        return new PageResult<>(ResultStatusCode.SUCCESS, users);
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @PostMapping("/add")
    public APIResponse add(@RequestBody User user){
        if (userService.addUser(user)){
            return JsonResult.success(ResultStatusCode.SUCCESS, user);
        }else{
            return JsonResult.error();
        }
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @PutMapping("/update")
    public APIResponse update(@RequestBody User user){
        if (userService.updateByUserId(user)){
            return JsonResult.success(ResultStatusCode.SUCCESS, user);
        }else{
            return JsonResult.error();
        }
    }

    /**
     * 实现逻辑删除
     * @param userId
     * @return
     */
    @DeleteMapping("/del/{userId}")
    public APIResponse delete(@PathVariable("userId") Integer userId){
        if (userId == null){
            return JsonResult.error(ResultStatusCode.Param_ID_Not_Exist);
        }else{
            User user = new User();
            user.setUserId(userId);
            user.setStatus(PublicResultConstant.STATUS_VALID);
            if (userService.updateByUserId(user)) {
                return JsonResult.success(ResultStatusCode.SUCCESS, user);
            }else{
                return JsonResult.error();
            }
        }
    }
}
