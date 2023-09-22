package com.almod.flow.broker.activemq.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQToDBRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        /*from("timer:active-mq-timer?period=6000")
                .routeId("Timer")
                .log("Moving the message to the incoming.files")
                .to("activemq:incoming.files");

        from("activemq:incoming.files")
                .routeId("activemq-to-mysql")
                .log("Well!");*/
                //.to("activemq:incoming.files");
    }
}
