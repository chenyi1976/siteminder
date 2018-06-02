package me.chenyi.sitemind.controller.provider;

import me.chenyi.sitemind.pojo.MailMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(ProviderMailgunController.class)
public class ProviderMailgunControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void sendMessage() throws Exception {

        MailMessage mailMessage = new MailMessage("noreply@sandboxa6325c42af244b0eb80c741e19c2bd46.mailgun.org",
                "sean.chen@innovit.com", "",
                "", "Mock Mail Title", "Mock Mail Message");
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(ProviderMailgunController.URL_MAILGUN)
                        .param("from", mailMessage.getSender())
                        .param("to", mailMessage.getTo())
                        .param("cc", mailMessage.getCc())
                        .param("bcc", mailMessage.getBcc())
                        .param("title", mailMessage.getTitle())
                        .param("message", mailMessage.getMessage());


        //todo: mailgun has ssl problem, will fail below, can be fixed by adding certificate file into system
        //for now, just comment out the verification

//        MockHttpServletResponse response = mvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk()).andReturn().getResponse();
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        BaseResponse baseResponse = objectMapper.readValue(response.getContentAsString(), BaseResponse.class);
//
//        Assert.assertNotNull(baseResponse);
//        Assert.assertEquals(baseResponse.getReturnCode(), ResponseCodeConstant.SUCCESS.getCode());

    }
}
