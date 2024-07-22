package com.almod.flow.broker.activemq.route;

import com.almod.flow.broker.activemq.infrastructure.EmployeeHandlerQueue;
import com.almod.flow.broker.activemq.infrastructure.VacationHandlerQueue;
import com.almod.store.entity.broker.activemq.EmployeeEntity;
import com.almod.store.entity.broker.activemq.VacationEntity;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQToDBRoute extends RouteBuilder {
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
                .log("[${header.UUID}] Try send a message to the db")
                .choice()
                    .when(simple(String.format("${header.BROKER_ENTITY_TYPE} == '%s'", VacationEntity.class.getSimpleName())))
                        .bean(VacationHandlerQueue.class, "save")
                    .when(simple(String.format("${header.BROKER_ENTITY_TYPE} == '%s'", EmployeeEntity.class.getSimpleName())))
                        .bean(EmployeeHandlerQueue.class, "save")
                    .otherwise()
                     .log("Stop processing, the required type was not found")
                     .stop()
                 .end()
                .log("[${header.UUID}] Successfully persisting to the db");
    }
}

