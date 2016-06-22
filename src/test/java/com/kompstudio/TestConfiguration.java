package com.kompstudio;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(Main.class)
public class TestConfiguration extends Main {

}
