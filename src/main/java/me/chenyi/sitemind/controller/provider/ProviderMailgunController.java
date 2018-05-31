package me.chenyi.sitemind.controller.provider;

import me.chenyi.sitemind.pojo.BaseResponse;
import me.chenyi.sitemind.pojo.PojoDataResponse;
import me.chenyi.sitemind.pojo.ResponseFactory;
import me.chenyi.sitemind.util.ResponseCodeConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@RestController
public class ProviderMailgunController implements IProvider{

    public static final String ID_MAILGUN = "mailgun";
    public static final String URL_MAILGUN = "/provider/mailgun";

    @Value( "${mailgun.api_key}" )
    private String API_KEY;

    @Value( "${mailgun.domain_name}" )
    private String YOUR_DOMAIN_NAME;

    @PostMapping(value=URL_MAILGUN)
    @ResponseBody
    public BaseResponse sendMail(@RequestParam(value = "from") String sender,
                                 @RequestParam(value = "to") String to,
                                 @RequestParam(value = "cc", required = false) String cc,
                                 @RequestParam(value = "bcc", required = false) String bcc,
                                 @RequestParam(value = "title") String title,
                                 @RequestParam(value = "message") String message) {

        try {
            HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + YOUR_DOMAIN_NAME + "/messages")
                    .basicAuth("api", API_KEY)
                    .queryString("from", sender)
                    .queryString("to", to)
                    .queryString("cc", cc)
                    .queryString("bcc", bcc)
                    .queryString("subject", title)
                    .queryString("text", message)
                    .asJson();
            JsonNode body = request.getBody();

            return ResponseFactory.createSuccessfulResponse(body);
        } catch (UnirestException e) {
            e.printStackTrace();
//            logger.log();
            return ResponseFactory.createErrorResponse(e.toString());
        }

    }

    @GetMapping(value=URL_MAILGUN)
    @ResponseBody
    public BaseResponse getStatus() {
        //todo: check status of provider periodically
        return ResponseFactory.createSuccessfulResponse("");
    }

}