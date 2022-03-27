package org.epst.chat;


//import javax.inject.Inject;
//import javax.ws.rs.PathParam;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@ServerEndpoint(
  value = "/chat/{username}",
  decoders = MessageDecoder.class, 
  encoders = MessageEncoder.class
)
public class ChatEndpoint {

    private Session session;
    private static Set<ChatEndpoint> chatEndpoints 
      = new CopyOnWriteArraySet<>();
      private static Set<Session> sessions 
      = new CopyOnWriteArraySet<>();
    private static HashMap<String, String> users = new HashMap<>();
    private static List<HashMap<String, String>> listeUsers = new LinkedList<HashMap<String, String>>(); 

    @OnOpen
    public void onOpen(
      Session session, 
      @PathParam("username") String username) throws IOException, EncodeException {
 
        //
        HashMap<String, String> user = new HashMap<>();
        user.put("sessionId", session.getId());
        user.put("username", username);
        user.put("statut", "en-attente");
        System.out.println("Votre nom est: ____________________________: "+username);
        
        //
        this.session = session;
        chatEndpoints.add(this);
        users.put(session.getId(), username);
        //
        sessions.add(session);
        //
        //UserChat userChat = new UserChat(session.getId(),username,session);
        if(!username.equals("0")){//&& !username.equals("4")
          listeUsers.add(user);
        }
        

        Message message = new Message();
        message.setFrom(username);
        message.setContent("Connected!");
        //broadcast(message);
    }

    @OnMessage
    public void onMessage(Session session, Message message) 
      throws IOException, EncodeException {
 
        //
        ObjectMapper obj = new ObjectMapper();
        
        if(message.getFrom().equals("0") || message.getFrom().equals("4")){
          if(message.getTo().equals("system")){//l'utilisateur veut faire une action dans le systeme
          /*
          String r = message.getContent();
          String comm = "";
          //System.out.println("conn: "+r);
            String[] result = r.split(",");
            for (String str : result) {
              System.out.println(str + ": ");
            }
            comm = result[0];
            System.out.println("comm: "+comm);
            */
            if(message.getContent().equals("getall")){
              System.out.println(message.getContent());
              System.out.println(message.getFrom());
              System.out.println(message.getTo());
              
              //
              Predicate<HashMap<String, String>> byStatut = u -> u.get("statut") == "en-attente";
              listeUsers = listeUsers.stream().filter(byStatut)
              .collect(Collectors.toList());
              //
              String rep = obj.writeValueAsString(listeUsers);
              /*
              try{
              JsonArrayBuilder jsonStr = Json.createArrayBuilder(listeUsers);
              System.out.println(rep);
              } catch (Exception ex){
                System.out.println("Du: "+ex);
              }
              */
              /*
              listeUsers.forEach((e)->{
                System.out.println("le truc: "+e.get("sessionId"));
                System.out.println("le truc: "+e.get("username"));
                System.out.println("le truc: "+e.get("session"));
              });
              */             

              //session.getBasicRemote().sendObject("users");
              session.getAsyncRemote().sendText("{\"idsession\":\""+session.getId()+"\",\"liste\":"+rep+" }");
              
            }else if(message.getContent().split(",")[0].equals("communique")){//Etablissement de la connexion par epst agent
              //
              String[] commandes = message.getContent().split(",");
              String c1 = commandes[0];
              String c2 = commandes[1];
              String c3 = commandes[2];
              //regle sementhyque: 1:code, 2:idsessionhote, 3:idsessionclient
              //Je dois maintenant renvoyer au client une commande pour commencer la communication...
              System.out.println("la commande: "+commandes);
              System.out.println("c1: "+c1);
              System.out.println("c2: "+c2);
              System.out.println("c3: "+c3);
              //
              sessions.forEach((s)->{
                if(s.getId().equals(c3)){
                  HashMap<String, String> action = new HashMap<>();
                  action.put("requete", "start");
                  action.put("idSessionHote", c2);
                  //action.put("contenu", "");
                  try{
                    s.getAsyncRemote().sendText(obj.writeValueAsString(action));
                    HashMap<String, String> ac = new HashMap<>();
                    action.put("requete", "start");
                    action.put("idSessionHote", session.getId());
                    action.put("contenu", "");
                    //session.getAsyncRemote().sendText(obj.writeValueAsString(ac));//Accué de réception
                    //
                    listeUsers.forEach((u)->{
                      
                      //
                      if(u.get("sessionId").equals(c3)){
                        u.replace("statut", "en-communication");
                      }
                    });
                  }catch(Exception ex){
                    sessions.forEach((ss)->{
                      if(s.getId().equals(c3)){
                        ss.getAsyncRemote().sendText(ex.getMessage());
                      }
                    });
                  }
                }
              });
              
            }else if(message.getContent() == "close"){

            }else{
              System.out.println("conn: "+message.getContent());
            }
            
          }else{//epst: Tu t'adresse à quelqu'un...
            //
            HashMap<String, String> action = new HashMap<>();
            action.put("requete", "start");
            action.put("idSessionHote", session.getId());
            action.put("contenu", message.getContent());
            //
            sessions.forEach((tc)->{
              if(tc.getId().equals(message.getTo())){
                try{
                  tc.getAsyncRemote().sendText(obj.writeValueAsString(action));
                  //
                  HashMap<String, String> ac = new HashMap<>();
                  action.put("requete", "start");
                  action.put("idSessionHote", session.getId());
                  action.put("contenu", "");
                  //session.getAsyncRemote().sendText(obj.writeValueAsString(ac));//Accué de réception
                }catch(Exception ex){

                }
              }
            });
          }
        }else{
          //{"from":"0","to":"system","content":"getall"}
          HashMap<String, String> action = new HashMap<>();
          action.put("requete", "start");
          action.put("idSessionHote", session.getId());
          action.put("contenu", message.getContent());
          //
          sessions.forEach((tc)->{
            if(tc.getId().equals(message.getTo())){
              try{
                tc.getAsyncRemote().sendText(obj.writeValueAsString(action));
              }catch(Exception ex){

              }
            }
          });
        }
        
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
 
        chatEndpoints.remove(this);
        Message message = new Message();
        message.setFrom(users.get(session.getId()));
        message.setContent("Disconnected!");
        //broadcast(message);
        //
        users.remove(users.get(session.getId()));
        //
        listeUsers.forEach((r)->{if(r.get("sessionId") == session.getId()){listeUsers.remove(r);}});
        //
        sessions.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }

    private static void broadcast(Message message) 
      throws IOException, EncodeException {
 
        chatEndpoints.forEach(endpoint -> {
            synchronized (endpoint) {
                try {
                    endpoint.session.getBasicRemote().
                      sendObject(message);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
}
