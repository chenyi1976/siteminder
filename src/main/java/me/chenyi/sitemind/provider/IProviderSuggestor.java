package me.chenyi.sitemind.provider;

import java.util.Collection;

public interface IProviderSuggestor {

    String suggestProvider(Collection<String> excludedSuggestor);

    int retryTimes();
}
