package com.almod.flow.broker.activemq.route;

import com.almod.flow.broker.activemq.infrastructure.ActiveMQEmployeeHandlerQueue;
import com.almod.flow.broker.activemq.infrastructure.VacationHandlerQueue;
import com.almod.store.entity.broker.activemq.ActiveMQEmployeeEntity;
import com.almod.store.entity.broker.activemq.VacationEntity;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQEmployeeToDBRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        onException(Exception.class)
                .handled(true)
                .log(LoggingLevel.ERROR, "[${header.UUID}] Error when sending to the db: \n${exception.cause}")
                .setHeader("textBody", simple("${body}"))
                .setBody(simple("${exception.stacktrace}"))
                .to("activemq:error");

         from("activemq:incoming.files")
                .routeId("activemq-to-mysql")
                .log("[${header.UUID}] Try send a message to the db").log("${header.BROKER_ENTITY_TYPE}")
                .choice()
                    .when(simple(String.format("${header.BROKER_ENTITY_TYPE} == '%s'", VacationEntity.class.getSimpleName())))
                        .bean(VacationHandlerQueue.class, "save")
                    .when(simple(String.format("${header.BROKER_ENTITY_TYPE} == '%s'", ActiveMQEmployeeEntity.class.getSimpleName())))
                        .bean(ActiveMQEmployeeHandlerQueue.class, "save")
                    .otherwise()
                     .log("Stop processing, the required type was not found")
                     .stop()
                 .end()
                .log("[${header.UUID}] Successfully persisting to the db");
    }
}

