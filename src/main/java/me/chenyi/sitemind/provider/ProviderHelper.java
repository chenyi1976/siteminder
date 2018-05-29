package me.chenyi.sitemind.provider;

import me.chenyi.sitemind.controller.provider.MailgunController;
import me.chenyi.sitemind.controller.provider.SendgridController;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

public class ProviderHelper {

    @Value( "${server.address}" )
    private static String serverAddress;
    @Value( "${server.port}" )
    private static String serverPort;

    private static Map<String, String> providerMap = new HashMap<>();

    static {
        String serverRoot = serverPort == null || "".equals(serverPort)?
                "http://" + serverAddress : "http://" + serverAddress + ":" + serverPort;

        providerMap.put(MailgunController.ID_MAILGUN, serverRoot + MailgunController.URL_MAILGUN);
        providerMap.put(SendgridController.ID_SENDGRID, serverRoot + SendgridController.URL_SENDGRID);
    }

    public static synchronized Map<String, String> getAllProviders(){
        return new HashMap<>(providerMap);
    }

    public static synchronized void registerProvider(String id, String url){
        providerMap.put(id, url);
    }

    public static synchronized void deregisterProvider(String id){
        providerMap.remove(id);
    }

    public static synchronized void updateProviderUrl(String id, String url){
        if (providerMap.containsKey(id))
            providerMap.put(id, url);
    }

    public static String getProviderUrl(String id){
        return providerMap.get(id);
    }

//    public static IProvider

}
