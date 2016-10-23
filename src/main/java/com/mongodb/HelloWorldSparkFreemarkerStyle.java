package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloWorldSparkFreemarkerStyle {
    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class, "/");
        Spark.get("/",new Route() {
            @Override
            public Object handle(final Request request,
                    final Response response) {
                StringWriter writer = new StringWriter();
                try {
                    configuration.setDirectoryForTemplateLoading(new File(
                            ".\\src\\main\\java\\resources"));
                    Template helloTemplate = configuration.getTemplate("hello.ftl");
                    Map<String, Object> helloMap = new HashMap<String, Object>();
                    helloMap.put("name", "FreeMarker Spark");

                    helloTemplate.process(helloMap, writer);
                    System.out.println(writer);

                } catch (Exception ex) {
                    Logger.getLogger(HelloWorldFreeMarkerStyle.class.getName()).log(Level.SEVERE, null, ex);
                }
                return writer;
            }
        });
    }
}
