package com.almod.flow.cache.hazelcast.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.stereotype.Component;

@Component
public class ClientConfigHazelcast {
    private final HazelcastInstance hazelcastInstance;

    private final String map = "CACHE_MAP";

    public ClientConfigHazelcast() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setClusterName("test-technologies-hz");
        clientConfig.getNetworkConfig().setSmartRouting(false);

        hazelcastInstance = HazelcastClient.newHazelcastClient(clientConfig);
    }

    public HazelcastInstance getHazelcastInstance() {
        return hazelcastInstance;
    }

    public IMap<Object, Object> getMap() {
        return hazelcastInstance.getMap(map);
    }
}
