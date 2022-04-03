package org.epst.chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {

    private String from;
    private String to;
    private String content;
    private String hostId;
    private String clientId;
    private Boolean close;
    private Boolean all;
    private String visible;
    private Boolean conversation;
    
    //from,to,content,hostId,clientId,close,all,visible,conversation
    

    Message(String from, String to , String content, String hostId, String clientId, Boolean close, Boolean all, String visible, Boolean conversation){

    }

    Message(){}

}