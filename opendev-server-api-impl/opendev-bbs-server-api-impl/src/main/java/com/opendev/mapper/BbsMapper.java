package com.opendev.mapper;

import com.opendev.bean.BbsBlog;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface BbsMapper extends BaseMapper<BbsBlog> {

    List<BbsBlog> selectLatestBbs(@Param("userId") Long userId,@Param("offset") int offset,@Param("limit") int limit);

    Long selectCounts();
}
