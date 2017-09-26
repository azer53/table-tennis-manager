package com.eli.integration.frenoy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

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
        setConfig(marshaller, client);
        return client;
    }

    @Bean
    public FrenoyMatches frenoyMatches(Jaxb2Marshaller marshaller) {
        FrenoyMatches client = new FrenoyMatches();
        setConfig(marshaller, client);
        return client;
    }

    private void setConfig(Jaxb2Marshaller marshaller, WebServiceGatewaySupport client) {
        client.setDefaultUri("http://api.vttl.be/0.7/");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
    }

}
