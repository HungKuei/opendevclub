package com.opendev.service.impl;

import com.opendev.api.service.CommentService;
import com.opendev.base.BaseResponse;
import com.opendev.base.BaseService;
import com.opendev.bean.BbsComment;
import com.opendev.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentServiceImpl extends BaseService implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public BaseResponse addComment(@RequestParam("comment") BbsComment comment) {
        int i = commentMapper.insertSelective(comment);
        if (i > 0){
            return success();
        }
        return error();
    }

    @Override
    public BaseResponse getCommentsByBbsId(@RequestParam("bbsId") Long bbsId) {
        return null;
    }

    @Override
    public BaseResponse getCommentCount(@RequestParam("bbsId") Long bbsId) {
        return null;
    }

    @Override
    public BaseResponse deleteComment(@RequestParam("commentId") Long commentId) {
        return null;
    }
}
