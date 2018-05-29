package me.chenyi.sitemind.pojo;


public class MailMessage extends MetaMessage{

    private String sender;
    private String to;
    private String cc;
    private String bcc;
    private String title;
    private String message;

    public MailMessage(String sender, String to, String cc, String bcc, String title, String message) {
        super();
        this.sender = sender;
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.title = title;
        this.message = message;
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

    @Override
    public String toString() {
        return "MailMessage [sender=" + sender + ", to=" + to + ", cc=" + cc
                + ", bcc=" + bcc + ", title=" + title + ", message=" + message
                + "]";
    }
}
