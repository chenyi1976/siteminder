package me.chenyi.sitemind.controller.provider;

import me.chenyi.sitemind.pojo.BaseResponse;
import me.chenyi.sitemind.pojo.ResponseFactory;
import me.chenyi.sitemind.provider.IProviderHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class ProviderController {

    @Autowired
    private IProviderHelper providerHelper;

    private static transient Log log = LogFactory.getLog(ProviderController.class);

    @GetMapping(value = "/provider")
    @ResponseBody
    public BaseResponse listProviders() {
        Map<String, String> allProviders = providerHelper.getAllProviders();
        return ResponseFactory.createSuccessfulResponse(allProviders);
    }

    @PostMapping(value = "/provider")
    @ResponseBody
    public BaseResponse addProvider(String id, String url) {

        //todo: verify url is correct, etc here
        providerHelper.registerProvider(id, url);

        Map<String, String> allProviders = providerHelper.getAllProviders();
        return ResponseFactory.createSuccessfulResponse(allProviders);
    }

    @DeleteMapping(value = "/provider")
    @ResponseBody
    public BaseResponse deleteProvider(String id) {

        //todo: verify url is correct, etc here
        providerHelper.deregisterProvider(id);

        Map<String, String> allProviders = providerHelper.getAllProviders();
        return ResponseFactory.createSuccessfulResponse("allProviders");
    }

}