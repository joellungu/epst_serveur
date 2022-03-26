package com.epst.chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {

    private String from;
    private String to;
    private String content;

}