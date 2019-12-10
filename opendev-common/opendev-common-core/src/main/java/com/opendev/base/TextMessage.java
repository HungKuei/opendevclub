package com.opendev.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextMessage extends BaseMessage {

    /**
     * 消息内容
     */
    private String Content;

}
