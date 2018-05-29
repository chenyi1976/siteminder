package me.chenyi.sitemind.controller;

import java.util.List;
import java.util.regex.Pattern;

import me.chenyi.sitemind.pojo.*;
import me.chenyi.sitemind.service.IMessageService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


@RestController
public class MessageController {

    @Autowired
    @Qualifier("messageService")
    private IMessageService messageService;

    private static transient Log log = LogFactory.getLog(MessageController.class);
    private Pattern p = null;

    private boolean isValidEmailAddress(String email) {
        if (p == null) {
            String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
            p = Pattern.compile(ePattern);
        }
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    private boolean isValidateEmailList(String emailList) {
        String[] addrs = emailList.split(",");
        int count = 0;
        for (String addr : addrs) {
            if (addr.trim().length() != 0){
                count ++;
                if (!isValidEmailAddress(addr))
                    return false;
            }
        }
        return count > 0;
    }


    @PostMapping(value = "/message")
    @ResponseBody
    public BaseResponse addMessageToQueue(@RequestParam(value = "from") String sender,
                                          @RequestParam(value = "to") String to,
                                          @RequestParam(value = "cc", required = false) String cc,
                                          @RequestParam(value = "bcc", required = false) String bcc,
                                          @RequestParam(value = "title") String title,
                                          @RequestParam(value = "message") String message) {

        if (!isValidEmailAddress(sender))
            return ResponseFactory.createErrorResponse("Invalid Sender Email:" + sender);

        if (!isValidateEmailList(to))
            return ResponseFactory.createErrorResponse("Invalid Receiver Email:" + to);

        if (cc != null && !"".equals(cc))
            if (!isValidateEmailList(cc))
                return ResponseFactory.createErrorResponse("Invalid cc:" + cc);

        if (bcc != null && !"".equals(bcc))
            if (!isValidateEmailList(bcc))
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