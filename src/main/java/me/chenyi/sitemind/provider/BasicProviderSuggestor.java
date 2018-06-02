package me.chenyi.sitemind.provider;

import java.util.Collection;
import java.util.Map;

public class BasicProviderSuggestor implements IProviderSuggestor {

    @Override
    public String suggestProvider(IProviderHelper providerHelper, Collection<String> excludes) {
        Map<String, String> allProviders = providerHelper.getAllProviders();
        for (String e : excludes) {
            allProviders.remove(e);
        }

        if (allProviders.isEmpty())
            return "";

        return allProviders.keySet().iterator().next();
    }

    @Override
    public int retryTimes() {
        return 1;
    }
}
