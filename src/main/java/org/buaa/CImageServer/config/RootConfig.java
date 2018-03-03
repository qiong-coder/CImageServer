package org.buaa.CImageServer.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = {"org.buaa.CImageServer.service", "org.buaa.CImageServer.logic", "org.buaa.CImageServer.exception"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value= EnableWebMvc.class)
        })
public class RootConfig {
}
