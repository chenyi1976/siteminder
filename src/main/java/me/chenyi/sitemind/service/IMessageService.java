/**
 * 
 */
package me.chenyi.sitemind.service;

import java.util.List;

import me.chenyi.sitemind.pojo.MailMessage;
import me.chenyi.sitemind.pojo.QueueStateInfo;

public interface IMessageService {
    
    public void sendMessage(MailMessage mailMessage);
    
    public List<QueueStateInfo> getAllQueueInfo();
    
    public List<QueueStateInfo> getQueueInfo(String queueName);
}
