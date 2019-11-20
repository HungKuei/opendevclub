package com.opendev.component;

import org.springframework.stereotype.Component;

@Component
public class PayContextHandler {

    public String getPayHtml(String payCode){
        // 使用payCode参数查询数据库获取beanid

        // 获取到beanid之后，使用spring容器获取实例对象

        // 执行该实现的方法即可.... aliPayStrategy

        return null;
    }
}
