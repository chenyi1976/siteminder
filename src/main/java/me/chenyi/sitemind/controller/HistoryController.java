package me.chenyi.sitemind.controller;

import me.chenyi.sitemind.entity.HistoryEntity;
import me.chenyi.sitemind.pojo.PojoDataResponse;
import me.chenyi.sitemind.pojo.ResponseFactory;
import me.chenyi.sitemind.service.IHistoryService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HistoryController {

    private static transient Log log = LogFactory.getLog(HistoryController.class);

    @Autowired
    private IHistoryService historyService;

    @GetMapping(value = "/history")
    @ResponseBody
    public PojoDataResponse getHistory() {
        Iterable<HistoryEntity> all = historyService.getAllHistory();
        return ResponseFactory.createSuccessfulResponse(all);
    }
}