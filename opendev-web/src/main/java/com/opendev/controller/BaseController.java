package com.opendev.controller;

import org.wf.jwtp.provider.Token;
import org.wf.jwtp.util.SubjectUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller基类
 * @author hungkuei
 */
public class BaseController {

    /**
     * 获取当前登录用户
     * @param request
     * @return
     */
    public Integer getLoginUserId(HttpServletRequest request){
        Token token = getLoginToken(request);
        return token == null ? null : Integer.parseInt(token.getUserId());
    }

    public Token getLoginToken(HttpServletRequest request){
        return SubjectUtil.getToken(request);
    }

}
