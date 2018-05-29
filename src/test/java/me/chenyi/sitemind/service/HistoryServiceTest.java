package me.chenyi.sitemind.service;

import me.chenyi.sitemind.controller.provider.MailgunController;
import me.chenyi.sitemind.entity.HistoryEntity;
import me.chenyi.sitemind.entity.HistoryRepository;
import me.chenyi.sitemind.pojo.BaseResponse;
import me.chenyi.sitemind.pojo.MailMessage;
import me.chenyi.sitemind.pojo.ResponseFactory;
import me.chenyi.sitemind.util.ResponseCodeConstant;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Set;

@RunWith(SpringRunner.class)
public class HistoryServiceTest {

    @TestConfiguration
    static class HistoryServiceTestContextConfiguration {

        @Bean
        public HistoryService historyService() {
            return new HistoryService();
        }
    }

    @Autowired
    private HistoryService employeeService;

    @MockBean
    private HistoryRepository historyRepository;


    @Test
    public void whenValidName_thenEmployeeShouldBeFound() {
        MailMessage mailMessage = new MailMessage("chenyi@hotmail.com",
                "chenyi@hotmail.com", "chenyi@hotmail.com",
                "chenyi@hotmail.com", "BusinessMeeting", "BusinessMeeting");

        BaseResponse baseResponse = ResponseFactory.createSuccessfulResponse("some message");

        Set<HistoryEntity> mockEntity = Collections.singleton(new HistoryEntity(mailMessage, MailgunController.ID_MAILGUN, baseResponse));
        Mockito.when(historyRepository.findAll()).thenReturn(mockEntity);


        Set<HistoryEntity> allHistory = employeeService.getAllHistory();
        Assert.assertNotNull(allHistory);
        Assert.assertEquals(allHistory.size(), 1);

        HistoryEntity next = allHistory.iterator().next();
        Assert.assertEquals(next.getProvider(), MailgunController.ID_MAILGUN);
    }
}
