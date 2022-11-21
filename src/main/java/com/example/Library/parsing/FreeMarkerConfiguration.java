package com.example.Library.parsing;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;


public class FreeMarkerConfiguration {

    private static Configuration cfg = null;

    public static Configuration get() throws IOException {
        if (cfg !=null) return cfg;
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setDirectoryForTemplateLoading(new File(System.getenv("JAVA_RESOURCES") + "/my_library/templates"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(true);
        return cfg;

    }

}
