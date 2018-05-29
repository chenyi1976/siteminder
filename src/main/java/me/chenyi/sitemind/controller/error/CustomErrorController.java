package me.chenyi.sitemind.controller.error;

import me.chenyi.sitemind.pojo.PojoDataResponse;
import me.chenyi.sitemind.pojo.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;

@RestController
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @Value("${debug:false}")
    private boolean debug;

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(value = PATH)
    PojoDataResponse error(WebRequest request, HttpServletResponse response) {

        // TODO: add logger
        if (response.getStatus() == 404){
            //do some thing for page not found
        } else if (response.getStatus() == 500){
            //do some thing for server internal error
        }

        return ResponseFactory.createErrorResponse(errorAttributes.getErrorAttributes(request, debug));
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

}
