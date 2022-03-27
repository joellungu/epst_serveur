package org.epst.chat;

import javax.websocket.Session;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserChat {
    String sessionId;
    String username;
    Session session;

    public UserChat(){

    }

    public UserChat(String sessionId, String username, Session session){
        this.session = session;
        this.sessionId = sessionId;
        this.username = username;
    }
}
