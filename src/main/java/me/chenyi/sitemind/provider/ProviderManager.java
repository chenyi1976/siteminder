package me.chenyi.sitemind.provider;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import me.chenyi.sitemind.pojo.BaseResponse;
import me.chenyi.sitemind.pojo.MailMessage;
import me.chenyi.sitemind.pojo.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class ProviderManager {
    private static Logger logger = Logger.getLogger(ProviderManager.class.getName());

    @Autowired
    private IProviderHelper providerHelper;

    public Map<String, BaseResponse> sendMessage(MailMessage message){

        Map<String, BaseResponse> result = new LinkedHashMap<>();

        BasicProviderSuggestor suggestor = new BasicProviderSuggestor();

        int retryTimes = Math.max(1, suggestor.retryTimes());

        Collection<String> excludedProviders = new HashSet<>();

        while(true){
            String providerId = suggestor.suggestProvider(providerHelper, excludedProviders);

            if (providerId == null || "".equals(providerId))
                break;

            excludedProviders.add(providerId);

            String providerUrl = providerHelper.getProviderUrl(providerId);
            if (providerUrl == null || "".equals(providerUrl))
                continue;

            for (int i = 0; i < retryTimes; i++) {
                try {
                    HttpResponse<JsonNode> response = Unirest.post(providerUrl)
                            .queryString("from", message.getSender())
                            .queryString("to", message.getTo())
                            .queryString("cc", message.getCc())
                            .queryString("bcc", message.getBcc())
                            .queryString("subject", message.getTitle())
                            .queryString("text", message.getMessage())
                            .asJson();

                    result.put(providerId, ResponseFactory.createSuccessfulResponse(response.getBody()));

                    return result;
                } catch (UnirestException e) {
                    logger.severe("Failed to send message via " + providerUrl);
                    result.put(providerId, ResponseFactory.createErrorResponse("UnirestException: " + e.getMessage()));
                }
            }
        }

        logger.severe("All providers failed to send message");

        return result;
    }
}
