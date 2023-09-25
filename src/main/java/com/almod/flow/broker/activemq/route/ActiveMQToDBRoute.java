package com.almod.flow.broker.activemq.route;

import com.almod.flow.broker.activemq.repo.ActiveMQClientRequestRepo;
import com.almod.flow.broker.activemq.util.ActiveMQClientRequestHandlerQueue;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQToDBRoute extends RouteBuilder {
    ActiveMQClientRequestRepo requestClientRepo;

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
                .bean(ActiveMQClientRequestHandlerQueue.class, "save");
    }
}
