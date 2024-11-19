package org.example.javaecommercet3p1.Config.General;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;


@Configuration
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)

public class WebConfig {

}
