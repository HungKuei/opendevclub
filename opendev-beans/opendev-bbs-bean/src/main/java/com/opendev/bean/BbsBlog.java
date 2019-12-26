package com.opendev.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BbsBlog implements Serializable {

    private static final long serialVersionUID = 6465089697923813776L;

    /**
     * 博文id
     */
    private Long bbsId;

    /**
     * 类型
     */
    private String bbsType;

    /**
     * 类型
     */
    private String content;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 评论数
     */
    private Long commentCount;

    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
