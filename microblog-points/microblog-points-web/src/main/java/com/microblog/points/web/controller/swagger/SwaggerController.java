package com.microblog.points.web.controller.swagger;


import com.swagger.util.SwaggerUri;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class SwaggerController {
    @RequestMapping("/points"+ SwaggerUri.API_URI)
    public void swagger(HttpServletRequest request, HttpServletResponse response)  throws Exception{
        log.info("SwaggerController ");
        request.getRequestDispatcher(SwaggerUri.API_URI).forward(request,response);
    }

}
