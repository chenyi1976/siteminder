package me.chenyi.sitemind.controller.provider;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import me.chenyi.sitemind.pojo.BaseResponse;
import me.chenyi.sitemind.pojo.ResponseFactory;
import me.chenyi.sitemind.util.EmailAddressValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProviderMailgunController implements IProvider{

    private static final Logger logger = LoggerFactory.getLogger(ProviderMailgunController.class);

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

        if (!EmailAddressValidationUtil.isValidEmailAddress(sender))
            return ResponseFactory.createErrorResponse("Invalid Sender Email:" + sender);

        if (!EmailAddressValidationUtil.isValidateEmailList(to))
            return ResponseFactory.createErrorResponse("Invalid Receiver Email:" + to);

        if (cc != null && !"".equals(cc))
            if (!EmailAddressValidationUtil.isValidateEmailList(cc))
                return ResponseFactory.createErrorResponse("Invalid cc:" + cc);

        if (bcc != null && !"".equals(bcc))
            if (!EmailAddressValidationUtil.isValidateEmailList(bcc))
                return ResponseFactory.createErrorResponse("Invalid bcc:" + bcc);

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

            //todo: we should verify the body here,

            return ResponseFactory.createSuccessfulResponse(body);
        } catch (UnirestException e) {
//            e.printStackTrace();
            logger.error("Failed to send via mailgun: " + e.getStackTrace());
            return ResponseFactory.createErrorResponse(e.getStackTrace());
        }

    }

    @GetMapping(value=URL_MAILGUN)
    @ResponseBody
    public BaseResponse getStatus() {
        //todo: check status of provider periodically
        return ResponseFactory.createSuccessfulResponse("");
    }

}