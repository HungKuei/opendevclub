package com.opendev.mapper;

import com.opendev.entity.User;


/**
 * Mapper 接口
 *
 * @author hungkuei
 * @since 2019-07-31
 */
public interface UserMapper extends BaseMapper<User> {

    User selectByUsername(String username);

}
