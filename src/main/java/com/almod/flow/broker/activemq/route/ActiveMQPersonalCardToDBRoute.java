package com.almod.flow.broker.activemq.route;

import com.almod.flow.broker.activemq.repo.ActiveMQPersonalCardRepo;
import com.almod.flow.broker.activemq.service.ActiveMQPersonalCardService;
import com.almod.flow.broker.activemq.util.ActiveMQPersonalCardHandlerQueue;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQPersonalCardToDBRoute extends RouteBuilder {
    ActiveMQPersonalCardRepo requestClientRepo;

    @Override
    public void configure() throws Exception {
        /*from("timer:active-mq-timer?period=6000")
                .routeId("Timer")
                .log("Moving the message to the incoming.files")
                .to("activemq:incoming.files");;*/

         from("activemq:incoming.files")
                .routeId("activemq-to-mysql")
                .log("Try send a message to the db!")
                //.bean(ActiveMQClientRequestHandlerQueue.class, "save(${header.clientRequestString})");
                .bean(ActiveMQPersonalCardHandlerQueue.class, "save");
    }
}
