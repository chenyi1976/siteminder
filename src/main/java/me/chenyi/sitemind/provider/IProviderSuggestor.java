package me.chenyi.sitemind.provider;

import java.util.Collection;

public interface IProviderSuggestor {

    String suggestProvider(IProviderHelper providerHelper, Collection<String> excludedSuggestor);

    int retryTimes();
}
