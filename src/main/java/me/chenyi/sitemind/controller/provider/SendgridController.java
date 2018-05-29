package me.chenyi.sitemind.controller.provider;

import me.chenyi.sitemind.pojo.BaseResponse;
import me.chenyi.sitemind.pojo.PojoDataResponse;
import me.chenyi.sitemind.pojo.ResponseFactory;
import me.chenyi.sitemind.util.ResponseCodeConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@RestController
public class SendgridController implements IProvider {

    public static final String ID_SENDGRID = "sendgrid";

    public static final String URL_SENDGRID = "/provider/sendgrid";

    @Value( "${sendgrid.api_key}" )
    private String API_KEY;

    @Value( "${mailgun.domain_name}" )
    private String YOUR_DOMAIN_NAME;

    @PostMapping(value=URL_SENDGRID)
    @ResponseBody
    public BaseResponse sendMail(@RequestParam(value = "from") String sender,
                                 @RequestParam(value = "to") String to,
                                 @RequestParam(value = "cc", required = false) String cc,
                                 @RequestParam(value = "bcc", required = false) String bcc,
                                 @RequestParam(value = "title") String title,
                                 @RequestParam(value = "message") String message) {
        HttpResponse<String> response = null;
        try {
            String requestStr = constructEmailMessage(sender, to, title,
                    message);
            System.out.println("Input:"+requestStr);
            response = Unirest.post("https://api.sendgrid.com/v3/mail/send")
                    .header("authorization", "Bearer "+API_KEY)
                    .header("content-type", "application/json")
                    .body(requestStr)
                    .asString();
            System.out.println("Response status:" + response.getStatus() + " "
                    + response.getStatusText());

            return ResponseFactory.createSuccessfulResponse("Mail has been sent");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseFactory.createErrorResponse(e.toString());
        }
    }

    @GetMapping(value=URL_SENDGRID)
    @ResponseBody
    public BaseResponse getStatus() {
        //todo: check status of provider periodically
        return ResponseFactory.createSuccessfulResponse("");
    }

    private String constructEmailMessage(String sender, String to,
                                         String title, String message) throws JSONException {
        JSONObject json = new JSONObject();
        JSONArray toList = new JSONArray();
        JSONObject to1 = new JSONObject();
        toList.put(to1);
        to1.put("email", to);
        to1.put("name", to);

        JSONObject from = new JSONObject();
        from.put("email", sender);
        from.put("name", sender);

        JSONArray content = new JSONArray();
        JSONObject text1 = new JSONObject();
        content.put(text1);
        text1.put("type", "text/plain");
        text1.put("value", message);


        JSONObject replyTo = new JSONObject();
        replyTo.put("email", "");
        replyTo.put("name", "");

        JSONArray personalizations = new JSONArray();
        JSONObject asyn = new JSONObject();
        asyn.put("to", toList);
        asyn.put("subject", title);

        personalizations.put(asyn);

        json.put("personalizations", personalizations);
        json.put("from", from);
        json.put("content", content);
        json.put("reply_to", replyTo);

        String requestStr = json.toString();
        return requestStr;
    }

}