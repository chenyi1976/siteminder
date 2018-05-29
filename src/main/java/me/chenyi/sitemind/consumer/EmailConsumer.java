package me.chenyi.sitemind.consumer;

import me.chenyi.sitemind.pojo.MailMessage;
import me.chenyi.sitemind.entity.HistoryEntity;
import me.chenyi.sitemind.entity.HistoryRepository;
import me.chenyi.sitemind.pojo.BaseResponse;
import me.chenyi.sitemind.provider.ProviderManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EmailConsumer {
    private static final Logger logger = LoggerFactory.getLogger(EmailConsumer.class);

    @Autowired
    private HistoryRepository historyRepository;

    @JmsListener(destination = "email.queue")
    public void receiveQueue(MailMessage mail) {
        if(logger.isDebugEnabled()){
            logger.debug("msgId = " + mail.getMsgId());
            logger.debug("msgText = " + mail.toString());
        }
        Map<String, BaseResponse> activities = ProviderManager.sendMessage(mail);

        for (String providerId : activities.keySet()) {
            HistoryEntity entity = new HistoryEntity(mail, providerId, activities.get(providerId));
            historyRepository.save(entity);
        }
    }
}