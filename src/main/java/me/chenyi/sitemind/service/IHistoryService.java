package me.chenyi.sitemind.service;

import me.chenyi.sitemind.entity.HistoryEntity;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface IHistoryService {

    Set<HistoryEntity> getAllHistory();
}
