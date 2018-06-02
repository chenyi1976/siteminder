package me.chenyi.sitemind.provider;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProviderHelper implements IProviderHelper {


    private Map<String, String> providerMap = new HashMap<>();

    public ProviderHelper() {
    }

    @Override
    public Map<String, String> getAllProviders(){
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
