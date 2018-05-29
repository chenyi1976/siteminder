/**
 *
 */
package me.chenyi.sitemind.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import junit.framework.Assert;
import me.chenyi.sitemind.pojo.PojoDataResponse;
import me.chenyi.sitemind.pojo.MailMessage;
import me.chenyi.sitemind.pojo.QueueStateInfo;
import me.chenyi.sitemind.util.ResponseCodeConstant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void sendMessage() throws Exception {

        MailMessage mailMessage = new MailMessage("chenyi@hotmail.com",
                "chenyi@hotmail.com", "chenyi@hotmail.com",
                "chenyi@hotmail.com", "BusinessMeeting", "BusinessMeeting");
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.post("/message")
                        .param("from", mailMessage.getSender())
                        .param("to", mailMessage.getTo())
                        .param("cc", mailMessage.getCc())
                        .param("bcc", mailMessage.getBcc())
                        .param("title", mailMessage.getTitle())
                        .param("message", mailMessage.getMessage());

        MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        PojoDataResponse jsonNode = objectMapper.readValue(response.getContentAsString(), PojoDataResponse.class);
        Assert.assertEquals(ResponseCodeConstant.SUCCESS.getCode(), jsonNode.getReturnCode());
    }

    @Test
    public void getMessageQueueInfoByName() throws Exception {
        String queueName = "email.queue";
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/message")
                        .param("queueName", queueName);
        MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        PojoDataResponse<List<QueueStateInfo>> jsonNode = objectMapper.readValue(response.getContentAsString(), PojoDataResponse.class);
        Assert.assertEquals(ResponseCodeConstant.SUCCESS.getCode(), jsonNode.getReturnCode());
        Assert.assertNotNull(jsonNode.getData());
    }

}
