/**
 *
 */
package me.chenyi.sitemind.controller.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.Assert;
import me.chenyi.sitemind.pojo.PojoDataResponse;
import me.chenyi.sitemind.provider.IProviderHelper;
import me.chenyi.sitemind.util.ResponseCodeConstant;
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
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProviderController.class)
public class ProviderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IProviderHelper providerHelper;


    @Test
    public void testGetProviders() throws Exception {
        String mock_id = "mock_id";
        String mock_url = "mock_url";
        Map<String, String> providers = Collections.singletonMap(mock_id, mock_url);
        Mockito.when(providerHelper.getAllProviders()).thenReturn(providers);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/provider");

        MockHttpServletResponse response = mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        PojoDataResponse jsonNode = objectMapper.readValue(response.getContentAsString(), PojoDataResponse.class);
        Assert.assertEquals(ResponseCodeConstant.SUCCESS.getCode(), jsonNode.getReturnCode());
        Map<String, String> returnedProviders = (Map<String, String>) jsonNode.getData();
        Assert.assertNotNull(returnedProviders);
        Assert.assertEquals(returnedProviders.size(), 1);

        String id = returnedProviders.keySet().iterator().next();
        String url = returnedProviders.values().iterator().next();

        Assert.assertEquals(id, mock_id);
        Assert.assertEquals(url, mock_url);
    }

}
