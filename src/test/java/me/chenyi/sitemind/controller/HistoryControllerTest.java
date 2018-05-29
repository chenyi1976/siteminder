/**
 *
 */
package me.chenyi.sitemind.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.Assert;
import me.chenyi.sitemind.controller.provider.MailgunController;
import me.chenyi.sitemind.entity.HistoryEntity;
import me.chenyi.sitemind.pojo.BaseResponse;
import me.chenyi.sitemind.pojo.MailMessage;
import me.chenyi.sitemind.pojo.PojoDataResponse;
import me.chenyi.sitemind.pojo.ResponseFactory;
import me.chenyi.sitemind.service.IHistoryService;
import me.chenyi.sitemind.util.ResponseCodeConstant;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HistoryController.class)
public class HistoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IHistoryService historyService;

    @Before
    public void setup(){
        MailMessage mailMessage = new MailMessage("chenyi@hotmail.com",
                "chenyi@hotmail.com", "chenyi@hotmail.com",
                "chenyi@hotmail.com", "BusinessMeeting", "BusinessMeeting");

        BaseResponse baseResponse = ResponseFactory.createSuccessfulResponse("some message");

        Set<HistoryEntity> mockEntity = Collections.singleton(new HistoryEntity(mailMessage, MailgunController.ID_MAILGUN, baseResponse));
        Mockito.when(historyService.getAllHistory()).thenReturn(mockEntity);
    }

    @Test
    public void testGetHistory() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/history");

        MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        PojoDataResponse jsonNode = objectMapper.readValue(response.getContentAsString(), PojoDataResponse.class);
        Assert.assertEquals(ResponseCodeConstant.SUCCESS.getCode(), jsonNode.getReturnCode());
    }

}
