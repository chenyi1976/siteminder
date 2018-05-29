/**
 * 
 */
package me.chenyi.sitemind.controller.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.chenyi.sitemind.pojo.MailMessage;
import me.chenyi.sitemind.pojo.PojoDataResponse;
import me.chenyi.sitemind.util.ResponseCodeConstant;
import org.junit.Assert;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SendgridControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void sendMessage() throws Exception {

        MailMessage mailMessage = new MailMessage("sean.chen@innovit.com",
                "sean.chen@innovit.com", "",
                "", "Mock Mail Title", "Mock Mail Message");
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(SendgridController.URL_SENDGRID)
                        .param("from", mailMessage.getSender())
                        .param("to", mailMessage.getTo())
                        .param("cc", mailMessage.getCc())
                        .param("bcc", mailMessage.getBcc())
                        .param("title", mailMessage.getTitle())
                        .param("message", mailMessage.getMessage());

        MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();

        ObjectMapper objectMapper = new ObjectMapper();
        PojoDataResponse jsonNode = objectMapper.readValue(response.getContentAsString(), PojoDataResponse.class);

        Assert.assertNotNull(jsonNode);
        Assert.assertEquals(jsonNode.getReturnCode(), ResponseCodeConstant.SUCCESS.getCode());

    }
}
