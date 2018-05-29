/**
 * 
 */
package me.chenyi.sitemind.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.jms.Queue;

import me.chenyi.sitemind.pojo.MailMessage;
import me.chenyi.sitemind.pojo.QueueStateInfo;
import me.chenyi.sitemind.util.ActiveMQUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service("messageService")
public class MessageServiceImpl implements IMessageService {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;
    
    @Override
    public void sendMessage(MailMessage mailMessage) {
        this.jmsMessagingTemplate.convertAndSend(this.queue, mailMessage);
    }
    
    @Override
    public List<QueueStateInfo> getQueueInfo(String queueName){
        List<QueueStateInfo> queueInfoList = new ArrayList<QueueStateInfo>();
        QueueStateInfo queue= null;
        Map<String,QueueStateInfo> queueMap= ActiveMQUtil.getAllQueueInfoMap();
        if(queueMap.containsKey(queueName)){
            queue = queueMap.get(queueName);
        }
        if(queue != null){
            queueInfoList.add(queue);
        }
        return queueInfoList;
    }
    
    @Override
    public List<QueueStateInfo> getAllQueueInfo(){
        List<QueueStateInfo> queueInfoList = new ArrayList<QueueStateInfo>();
        Collection<QueueStateInfo> queueInfos = null;
        Map<String,QueueStateInfo> queueMap = ActiveMQUtil.getAllQueueInfoMap();
        if(queueMap != null){
            queueInfos = queueMap.values();
        }
        if(queueInfos != null && queueInfos.size() > 0){
            queueInfoList.addAll(queueInfos);
        }
        return queueInfoList;
    }

}
