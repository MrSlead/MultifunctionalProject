package com.almod.flow.broker.activemq.route;

import com.almod.flow.broker.activemq.repo.ActiveMQPersonalCardRepo;
import com.almod.flow.broker.activemq.service.ActiveMQPersonalCardService;
import com.almod.flow.broker.activemq.util.ActiveMQPersonalCardHandlerQueue;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQPersonalCardToDBRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        onException(Exception.class)
                .handled(true)
                .log(LoggingLevel.ERROR, "Error when sending to the db: \n${exception.cause}")
                .setBody(simple("${exception.stacktrace}"))
                .to("activemq:error");

         from("activemq:incoming.files")
                .routeId("activemq-to-mysql")
                .log("Try send a message to the db")
                .bean(ActiveMQPersonalCardService.class, "save");
    }
}

