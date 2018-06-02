package me.chenyi.sitemind;

import java.util.Arrays;

import javax.jms.Queue;

import me.chenyi.sitemind.controller.provider.ProviderMailgunController;
import me.chenyi.sitemind.controller.provider.ProviderSendgridController;
import me.chenyi.sitemind.provider.ProviderHelper;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public Queue queue() {
        return new ActiveMQQueue("email.queue");
    }
    
    @Bean
    public Queue DLQueue() {
        return new ActiveMQQueue("ActiveMQ.DLQ");
    }

    @Value( "${server.address}" )
    private String serverAddress;
    @Value( "${server.port}" )
    private String serverPort;

    @Autowired
    private ProviderHelper providerHelper;

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            String serverRoot = serverPort == null || "".equals(serverPort) ?
                    "http://" + serverAddress : "http://" + serverAddress + ":" + serverPort;

            providerHelper.registerProvider(ProviderMailgunController.ID_MAILGUN, serverRoot + ProviderMailgunController.URL_MAILGUN);
            providerHelper.registerProvider(ProviderSendgridController.ID_SENDGRID, serverRoot + ProviderSendgridController.URL_SENDGRID);

        };
    }

}