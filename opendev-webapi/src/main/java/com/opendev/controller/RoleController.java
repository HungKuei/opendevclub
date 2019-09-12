package com.opendev.controller;

import com.opendev.base.APIResponse;
import com.opendev.enums.ResultStatusCode;
import com.opendev.model.Role;
import com.opendev.service.RoleService;
import com.opendev.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value = "RoleController", description = "角色管理模块")
@RequestMapping("/api/roles")
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 角色列表查询
     * @return
     */
    @ApiOperation(value = "角色列表查询")
    @RequiresPermissions("roles:list")
    @GetMapping("/get/list")
    public APIResponse<Role> get(){
        List<Role> roles = roleService.getRoleAll();
        return JsonResult.success(ResultStatusCode.SUCCESS, roles);
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @ApiOperation(value = "添加角色")
    @RequiresPermissions("roles:add")
    @PostMapping("/add")
    public APIResponse add(@RequestBody Role role){
        if (roleService.addRole(role)){
            return JsonResult.success(ResultStatusCode.SUCCESS, role);
        }else{
            return JsonResult.error();
        }
    }

    /**
     * 更新角色信息
     * @param role
     * @return
     */
    @ApiOperation(value = "更新角色")
    @RequiresPermissions("roles:update")
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

    /**
     * 删除角色信息
     * @param roleId
     * @return
     */
    @ApiOperation(value = "删除角色")
    @RequiresPermissions("roles:delete")
    @DeleteMapping("/delete")
    public APIResponse delete(@RequestParam("roleId") Integer roleId){
        if (roleId == null){
            return JsonResult.error(ResultStatusCode.Param_ID_Not_Exist);
        }else{
            if (roleService.deleteById(roleId)){
                return JsonResult.success();
            }else{
                return JsonResult.error();
            }
        }
    }
}
