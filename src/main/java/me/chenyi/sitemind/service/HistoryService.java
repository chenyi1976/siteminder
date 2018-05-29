package me.chenyi.sitemind.service;

import me.chenyi.sitemind.entity.HistoryEntity;
import me.chenyi.sitemind.entity.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class HistoryService implements IHistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public Set<HistoryEntity> getAllHistory() {

        Iterable<HistoryEntity> all = historyRepository.findAll();

        Set<HistoryEntity> result = new HashSet<>();
        for (HistoryEntity entity : all) {
            result.add(entity);
        }

        return result;
    }
}
