package com.almod.flow.broker.activemq.route;

import com.almod.flow.broker.ErrorDBHandlerQueue;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQErrorDBRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("activemq:error")
                .routeId("activemq-error-to-mysql")
                .log("${body}")
                .bean(ErrorDBHandlerQueue.class, "save");
    }
}
