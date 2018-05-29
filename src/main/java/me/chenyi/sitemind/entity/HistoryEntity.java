package me.chenyi.sitemind.entity;

import me.chenyi.sitemind.pojo.BaseResponse;
import me.chenyi.sitemind.pojo.MailMessage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class HistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date dateTime;

    private Long messageId;
    private String sender;
    private String to;
    private String cc;
    private String bcc;
    private String title;
    private String message;

    private String provider;
    private int returnCode;
    private String returnMessage;

    protected HistoryEntity() {}

    public HistoryEntity(MailMessage message, String providerId, BaseResponse response) {
        this.messageId = 0L;
        this.sender = message.getSender();
        this.to = message.getTo();
        this.cc = message.getCc();
        this.bcc = message.getBcc();
        this.title = message.getTitle();
        this.message = message.getMessage();
        this.provider = providerId;
        this.returnCode = response.getReturnCode();
        this.returnMessage = response.getMessage();
        this.dateTime = response.getDateTime();
    }

    public HistoryEntity(Long messageId, String sender, String to, String cc, String bcc, String title, String message,
                         String provider, int returnCode, String returnMessage, Date dateTime) {
        this.messageId = messageId;
        this.sender = sender;
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.title = title;
        this.message = message;
        this.provider = provider;
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public Long getMessageId() {
        return messageId;
    }

    public String getSender() {
        return sender;
    }

    public String getTo() {
        return to;
    }

    public String getCc() {
        return cc;
    }

    public String getBcc() {
        return bcc;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getProvider() {
        return provider;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public Date getDateTime() {
        return dateTime;
    }
}