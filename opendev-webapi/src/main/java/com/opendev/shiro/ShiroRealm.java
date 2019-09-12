package com.opendev.shiro;

import com.opendev.model.Permission;
import com.opendev.model.Role;
import com.opendev.model.User;
import com.opendev.service.PermissionService;
import com.opendev.service.RoleService;
import com.opendev.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     *
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        log.info("---------------- 执行 Shiro 凭证认证 ----------------------");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username = token.getUsername();
        // 从数据库获取对应用户名密码的用户
        User user = userService.getUserByUsername(username);
        if (user != null) {
            // 用户为禁用状态
            if (!user.getStatus().equals(0)) {
                throw new DisabledAccountException("你的账户已被禁用,请联系管理员激活");
            }
            log.info("---------------- Shiro 凭证认证成功 ----------------------");
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    user, //用户
                    user.getPassword(), //密码
                    getName()  //realm name
            );
            return authenticationInfo;
        }
        throw new UnknownAccountException();
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("---------------- 执行 Shiro 权限获取 ---------------------");
        Object principal = principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (principal instanceof User) {
            User user = (User) principal;
            if(user != null){
                List<Role> roleList = roleService.getRoleByUserId(user.getUserId());
                if(CollectionUtils.isNotEmpty(roleList)){
                    for(Role role : roleList){
                        authorizationInfo.addRole(role.getRoleName());
                        List<Permission> permissionList = permissionService.getPermissionByRoleId(role.getRoleId());
                        if(CollectionUtils.isNotEmpty(permissionList)){
                            for (Permission permission : permissionList){
                                if(StringUtils.isNoneBlank(permission.getPerms())){
                                    authorizationInfo.addStringPermission(permission.getPerms());
                                }
                            }
                        }
                    }
                }
            }
        }
        log.info("---------------- 获取到以下权限 ----------------");
        log.info(authorizationInfo.getStringPermissions().toString());
        log.info("---------------- Shiro 权限获取成功 ----------------------");
        return authorizationInfo;
    }
}