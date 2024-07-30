package com.almod.api.factory;

import com.almod.api.dto.broker.BrokerDto;
import com.almod.store.entity.broker.BrokerEntity;
import org.springframework.stereotype.Component;

@Component("BrokerEntityFactory")
public class BrokerEntityFactory extends EntityFactory<BrokerDto, BrokerEntity> {
}
