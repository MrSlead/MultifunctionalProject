package com.almod.flow.cache.hazelcast.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.stereotype.Component;

@Component
public class ClientConfigHazelcast {
    private final HazelcastInstance hazelcastInstance;

    public ClientConfigHazelcast() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setClusterName("test-technologies-hz");

        hazelcastInstance = HazelcastClient.newHazelcastClient(clientConfig);
    }

    public HazelcastInstance getHazelcastInstance() {
        return hazelcastInstance;
    }
}
