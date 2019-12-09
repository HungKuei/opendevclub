package com.opendev.mapper;

import com.opendev.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;


/**
 * Mapper 接口
 *
 * @author hungkuei
 * @since 2019-07-31
 */

public interface UserMapper extends BaseMapper<User> {

    User selectById(@Param("userId") Integer userId);

    User selectByUsername(@Param("username") String username);

    List<User> selectByPageLimit(@Param("page") Integer page, @Param("limit") Integer limit);

    Long count();
}
