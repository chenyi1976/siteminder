package me.chenyi.sitemind.provider;

import me.chenyi.sitemind.controller.provider.ProviderMailgunController;
import me.chenyi.sitemind.controller.provider.ProviderSendgridController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProviderHelper implements IProviderHelper {

    @Value( "${server.address}" )
    private String serverAddress;
    @Value( "${server.port}" )
    private String serverPort;

    private Map<String, String> providerMap = null;

    public ProviderHelper() {
    }

    @Override
    public Map<String, String> getAllProviders(){
        if (providerMap == null) {
            providerMap = new HashMap<>();

            String serverRoot = serverPort == null || "".equals(serverPort) ?
                    "http://" + serverAddress : "http://" + serverAddress + ":" + serverPort;
            registerProvider(ProviderMailgunController.ID_MAILGUN, serverRoot + ProviderMailgunController.URL_MAILGUN);
            registerProvider(ProviderSendgridController.ID_SENDGRID, serverRoot + ProviderSendgridController.URL_SENDGRID);
        }

        return new HashMap<>(providerMap);
    }

    @Override
    public void registerProvider(String id, String url){
        providerMap.put(id, url);
    }

    @Override
    public void deregisterProvider(String id){
        providerMap.remove(id);
    }

    @Override
    public void updateProviderUrl(String id, String url){
        if (providerMap.containsKey(id))
            providerMap.put(id, url);
    }

    @Override
    public String getProviderUrl(String id){
        return providerMap.get(id);
    }

}
