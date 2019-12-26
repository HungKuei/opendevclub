package com.opendev.api.service;


import com.opendev.base.BaseResponse;
import com.opendev.bean.BbsComment;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/comment")
public interface CommentService {

    /**
     * 添加评论
     * @param comment
     * @return
     */
    @ApiOperation(value = "添加评论")
    @PostMapping("/addComment")
    BaseResponse addComment(@RequestParam("comment") BbsComment comment);

    /**
     * 根据论坛id查询评论
     * @param bbsId
     * @return
     */
    @ApiOperation(value = "根据论坛id查询评论")
    @GetMapping("/getComments")
    BaseResponse getCommentsByBbsId(@RequestParam("bbsId") Long bbsId);

    /**
     * 根据论坛Id查询评论数
     * @param bbsId
     * @return
     */
    @ApiOperation(value = "根据论坛Id查询评论数")
    @GetMapping("/getCommentCount")
    BaseResponse getCommentCount(@RequestParam("bbsId") Long bbsId);

    /**
     * 根据id删除评论
     * @param commentId
     * @return
     */
    @ApiOperation(value = "根据id删除评论")
    @DeleteMapping("/delComment")
    BaseResponse deleteComment(@RequestParam("commentId") Long commentId);

}
