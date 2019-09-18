package com.opendev.controller;

import com.opendev.base.APIResponse;
import com.opendev.enums.ResultStatusCode;
import com.opendev.model.Permission;
import com.opendev.service.PermissionService;
import com.opendev.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限模块
 */
@Api(value = "PermissionController", description = "权限管理模块")
@RequestMapping("/api/perms")
@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @ApiOperation(value = "权限列表查询")
    @RequiresPermissions("perms:list")
    @GetMapping("/list")
    public APIResponse get(){
        List<Permission> permissions = permissionService.getPermsList();
        return JsonResult.success(ResultStatusCode.SUCCESS, permissions);
    }


    @ApiOperation(value = "新增权限")
    @RequiresPermissions("perms:add")
    @PostMapping("/add")
    public APIResponse add(@RequestBody Permission permission){
        if (permissionService.addPermission(permission)){
            return JsonResult.success(ResultStatusCode.SUCCESS, permission);
        }else{
            return JsonResult.error();
        }
    }

}
