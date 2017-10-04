package com.tpo.strava.web.controller.core;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by Tiberiu on 25/11/15.
 */
@RestController
public class CloudController {

    @RequestMapping("/env")
    public void env(HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.println("System Environment:");
        for (Map.Entry<String, String> envvar : System.getenv().entrySet()) {
            out.println(envvar.getKey() + ": " + envvar.getValue());
        }

        out.println("System Properties:");
        for (Map.Entry<Object, Object> property : System.getProperties().entrySet()) {
            out.println(property.getKey() + ": " + property.getValue());
        }
    }
}
