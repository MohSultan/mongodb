package com.mongodb.week1;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author msultan
 */
public class HelloWorldFreeMarkerStyle {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreeMarkerStyle.class, "/");

        try {
            configuration.setDirectoryForTemplateLoading(new File(
                    ".\\src\\main\\java\\resources"));
            Template helloTemplate = configuration.getTemplate("hello.ftl");
            StringWriter writer = new StringWriter();
            Map<String, Object> helloMap = new HashMap<String, Object>();
            helloMap.put("name", "FreeMarker");

            helloTemplate.process(helloMap, writer);
            System.out.println(writer);

        } catch (Exception ex) {
            Logger.getLogger(HelloWorldFreeMarkerStyle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
