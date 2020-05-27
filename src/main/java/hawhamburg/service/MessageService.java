package hawhamburg.service;

import com.google.gson.Gson;
import hawhamburg.controller.MessageController;
import hawhamburg.response.CommunicationServiceResponse;
import hawhamburg.model.User;
import hawhamburg.requests.SendMessageRequest;
import hawhamburg.response.StandardResponse;
import hawhamburg.response.StatusResponse;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static spark.Spark.get;
import static spark.Spark.post;

public class MessageService {
    private Gson gson = new Gson();
    private MessageController messageController;
    private User user;
    private String baseURL = "http://172.27.0.6:5000";
    private String localURL = InetAddress.getLocalHost().getHostAddress();

    public MessageService() throws UnknownHostException {
        this.messageController = MessageController.getInstance();
        registerServices();
    }

    public void registerServices() {
        post("/api/send/:userId", (req, res) -> {
            //TODO request
            String body = req.body();
            String goalUserId = req.params(":userId");

            SendMessageRequest request = gson.fromJson(body, SendMessageRequest.class);
            //request.getMessage().setSenderId(myUserId);
            messageController.sendMessage(goalUserId, request.getMessage());

            return "message sent";
        });

        get("/api/contact/:name",(req, res) ->{
            try{
                String userName = req.params(":name");
                CommunicationServiceResponse csr = new CommunicationServiceResponse();
                csr.setUser(baseURL + "/users/" + userName);
                csr.setGroup("");
                csr.setHirings(localURL+ "/api/hirings/" +userName);
                csr.setAssignments(localURL+ "/api/assignments/" +userName);
                res.type("application/json");
                return new Gson().toJson(
                        new StandardResponse(StatusResponse.SUCCESS,new Gson()
                                .toJsonTree(csr))
                );
            }catch (Exception e){
                e.printStackTrace();
                return "invalid username";
            }
        } );

    }
}