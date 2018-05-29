package me.chenyi.sitemind.provider;

import org.springframework.beans.factory.annotation.Value;

import java.util.Collection;
import java.util.Map;

public class BasicProviderSuggestor implements IProviderSuggestor {

    @Value( "${provider.retry_times:1}" )
    private int retry_times;

    @Override
    public String suggestProvider(Collection<String> excludes) {
        Map<String, String> allProviders = ProviderHelper.getAllProviders();
        for (String e : excludes) {
            allProviders.remove(e);
        }

        if (allProviders.isEmpty())
            return "";

        return allProviders.keySet().iterator().next();
    }

    @Override
    public int retryTimes() {
        return retry_times;
    }
}
