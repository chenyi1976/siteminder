package me.chenyi.sitemind.controller.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.chenyi.sitemind.pojo.BaseResponse;
import me.chenyi.sitemind.pojo.MailMessage;
import me.chenyi.sitemind.util.ResponseCodeConstant;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MailgunController.class)
public class MailgunControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void sendMessage() throws Exception {

        MailMessage mailMessage = new MailMessage("sean.chen@innovit.com",
                "sean.chen@innovit.com", "",
                "", "Mock Mail Title", "Mock Mail Message");
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(MailgunController.URL_MAILGUN)
                        .param("from", mailMessage.getSender())
                        .param("to", mailMessage.getTo())
                        .param("cc", mailMessage.getCc())
                        .param("bcc", mailMessage.getBcc())
                        .param("title", mailMessage.getTitle())
                        .param("message", mailMessage.getMessage());


        //todo: mailgun has ssl problem, will fail below, can be fixed by adding certificate into system,

//        MockHttpServletResponse response = mvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk()).andReturn().getResponse();
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        BaseResponse baseResponse = objectMapper.readValue(response.getContentAsString(), BaseResponse.class);
//
//        Assert.assertNotNull(baseResponse);
//        Assert.assertEquals(baseResponse.getReturnCode(), ResponseCodeConstant.SUCCESS.getCode());

    }
}
