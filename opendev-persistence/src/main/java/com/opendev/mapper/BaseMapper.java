package com.opendev.mapper;

import io.lettuce.core.dynamic.annotation.Param;

import java.io.Serializable;
import java.util.Collection;

public interface BaseMapper<T> {

    Integer insert(T var1);

    Integer batchInsert(T var1);

    Integer deleteById(Serializable var1);

    Integer deleteBatchIds(@Param("ids") Collection<? extends Serializable> var1);
}
