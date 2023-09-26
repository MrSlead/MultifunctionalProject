package com.almod.flow.broker.activemq.route;

import com.almod.util.ErrorDBHandlerQueue;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQErrorDBRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("activemq:error")
                .routeId("activemq-error-to-mysql")
                .bean(ErrorDBHandlerQueue.class, "save");
    }
}
