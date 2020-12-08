package boosktore.frontend.websocket;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author hhugohm
 */
@ServerEndpoint("/chat/{user}")
public class ChatServer {

    private static Session sessionAdmin;

    private static Map<String, Session> sessions = Collections.synchronizedMap(new HashMap<String, Session>());

    @OnOpen
    public void onOpen(Session session, @PathParam("user") String user) {
        if (user.equals("Bookstore")) {
            System.out.println("(\"#### ADMIN::::" + user);
            sessionAdmin = session;
        } else {
            System.out.println("(\"#### NAME::::" + user);
            sessions.put(user, session);
        }

    }

    @OnMessage
    public void onMessage(String jsonMessage, Session session, @PathParam("user") String user) {
        System.out.println("#### Message from ::::" + user);
        System.out.println("#### Text: " + jsonMessage);

        try {
            if (user.equals("Bookstore")) {

                System.out.println("##### EN el IF");

                JsonReader jr = Json.createReader(new StringReader(jsonMessage));
                JsonObject jor = jr.readObject();
                String to = jor.getString("to");
                String value = jor.getString("value");

                StringWriter sw = new StringWriter();
                JsonWriter jw = Json.createWriter(sw);

                JsonObject jow = Json.createObjectBuilder()
                        .add("type", "TEXT")
                        .add("from", user)
                        .add("value", value)
                        .build();
                jw.writeObject(jow);
                jsonMessage = sw.toString();

                sessionAdmin.getBasicRemote().sendText(jsonMessage);//admin
                Session customerSession = sessions.get(to);
                customerSession.getBasicRemote().sendText(jsonMessage);//admin

            } else {
                session.getBasicRemote().sendText(jsonMessage);//customer
                sessionAdmin.getBasicRemote().sendText(jsonMessage);//admin
            }

        } catch (Exception e) {

        }

    }

    @OnClose
    public void onClose(Session session, @PathParam("user") String user) {
        System.out.println("#### Close Session::::" + user);

        try {
            StringWriter sw = new StringWriter();
            JsonWriter jw = Json.createWriter(sw);

            JsonObject jow = Json.createObjectBuilder()
                    .add("type", "ACTION")
                    .add("from", user)
                    .add("value", "CLOSE")
                    .build();
            jw.writeObject(jow);
            String jsonMessage = sw.toString();
            
            sessionAdmin.getBasicRemote().sendText(jsonMessage);
            

        } catch (Exception e) {
            e.printStackTrace();
        }

        sessions.remove(user);

    }

}
