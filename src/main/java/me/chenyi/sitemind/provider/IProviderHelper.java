package me.chenyi.sitemind.provider;

import java.util.Map;

public interface IProviderHelper {
    Map<String, String> getAllProviders();

    void registerProvider(String id, String url);

    void deregisterProvider(String id);

    void updateProviderUrl(String id, String url);

    String getProviderUrl(String id);

}
