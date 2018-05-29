package me.chenyi.sitemind.controller.provider;

import me.chenyi.sitemind.pojo.BaseResponse;
import org.springframework.web.bind.annotation.RequestParam;

public interface IProvider {

    //send email
    BaseResponse sendMail(@RequestParam(value = "from") String sender,
                          @RequestParam(value = "to") String to,
                          @RequestParam(value = "cc", required = false) String cc,
                          @RequestParam(value = "bcc", required = false) String bcc,
                          @RequestParam(value = "title") String title,
                          @RequestParam(value = "message") String message);

    //get the current status of provider
    BaseResponse getStatus();
}
