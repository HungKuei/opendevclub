package com.opendev.controller;

import com.opendev.base.APIResponse;
import com.opendev.enums.ResultStatusCode;
import com.opendev.model.Role;
import com.opendev.service.RoleService;
import com.opendev.util.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/get/list")
    @RequiresPermissions("roles:list")
    public APIResponse<Role> get(){
        List<Role> roles = roleService.getRoleAll();
        return JsonResult.success(ResultStatusCode.SUCCESS, roles);
    }

    @PostMapping("/add")
    public APIResponse add(@RequestBody Role role){
        if (roleService.addRole(role)){
            return JsonResult.success(ResultStatusCode.SUCCESS, role);
        }else{
            return JsonResult.error();
        }
    }

    @PutMapping("/update")
    public APIResponse update(@RequestBody Role role){
        if (role.getRoleId() == null){
            return JsonResult.error(ResultStatusCode.Param_ID_Not_Exist);
        }else{
            if (roleService.updateById(role)) {
                return JsonResult.success(ResultStatusCode.SUCCESS, role);
            }else{
                return JsonResult.error();
            }
        }
    }
}
