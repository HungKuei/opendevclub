package com.opendev.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 权限表
 */
@Data
@NoArgsConstructor
public class Jurisdiction extends BaseEntity {

    private String authority;

    private String authorityName;

    private String moduleName;

    private Integer sort;

}
