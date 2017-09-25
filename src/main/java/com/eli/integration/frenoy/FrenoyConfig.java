package com.eli.integration.frenoy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class FrenoyConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("src.main.java.com.eli.integration");
        return marshaller;
    }

    @Bean
    public FrenoyPlayers frenoyPlayers(Jaxb2Marshaller marshaller) {
        FrenoyPlayers client = new FrenoyPlayers();
        client.setDefaultUri("http://api.vttl.be/0.7/");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
