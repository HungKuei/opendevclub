package com.opendev.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BbsComment implements Serializable {

    private static final long serialVersionUID = -8400377574165476494L;

    /**
     * 评论id
     */
    private Long commentId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 论坛id
     */
    private Long bbsId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论状态
     */
    private Integer status;

    /**
     * 评论时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

}
