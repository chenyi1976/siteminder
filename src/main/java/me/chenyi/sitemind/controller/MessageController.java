package me.chenyi.sitemind.controller;

import me.chenyi.sitemind.pojo.*;
import me.chenyi.sitemind.service.IMessageService;
import me.chenyi.sitemind.util.EmailAddressValidationUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MessageController {

    @Autowired
    @Qualifier("messageService")
    private IMessageService messageService;

    private static transient Log log = LogFactory.getLog(MessageController.class);

    @PostMapping(value = "/message")
    @ResponseBody
    public BaseResponse addMessageToQueue(@RequestParam(value = "from") String sender,
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

        if ("".equals(title))
            title = "No Title";

        if ("".equals(message))
            title = "No Message";

        try {
            MailMessage mailMessage = new MailMessage(sender, to, cc, bcc, title, message);
            messageService.sendMessage(mailMessage);
            return ResponseFactory.createSuccessfulResponse("Email has been successfully queued.");
        } catch (Exception e) {
            if(log.isErrorEnabled()){
                log.error("Failed to add email to queue!",e);
            }
            return ResponseFactory.createErrorResponse("Failed to add email to queue!");
        }
    }

    @GetMapping(value = "/message")
    @ResponseBody
    public PojoDataResponse getQueueStatus(@RequestParam(value = "queueName",required = false) String queueName) {
        List<QueueStateInfo> queueList = null;
        try {
            if(StringUtils.isEmpty(queueName)){
                queueList = messageService.getAllQueueInfo();
            } else{
                queueList = messageService.getQueueInfo(queueName);
            }
            return ResponseFactory.createSuccessfulResponse(queueList);
        } catch (Exception e) {
            if(log.isErrorEnabled()){
                log.error("Send message failed!",e);
            }
            return ResponseFactory.createErrorResponse(queueList);
        }
    }
}