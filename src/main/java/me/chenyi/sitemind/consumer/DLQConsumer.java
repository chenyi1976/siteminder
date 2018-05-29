/**
 * 
 */
package me.chenyi.sitemind.consumer;

import me.chenyi.sitemind.pojo.MailMessage;

import org.springframework.jms.annotation.JmsListener;

/**
 * 
 * @ClassName: DLQConsumer.java
 * @Description: When message consumer fails, the message will push to DLQ(Dead
 *               Letter Queue), this consumer consume the message from DLQ
 * @version: v1.0.0
 * @author: chenyi
 */
public class DLQConsumer {
    
    @JmsListener(destination = "ActiveMQ.DLQ")
    public void receiveQueue(MailMessage mail) {
        System.out.println("Dead Letter Queue: msgId:" + mail.getMsgId());
        System.out.println("Dead Letter Queue:" + mail);
        //TODO consumer message to call sendGrid/mainGun to delivery the email
    }
}
