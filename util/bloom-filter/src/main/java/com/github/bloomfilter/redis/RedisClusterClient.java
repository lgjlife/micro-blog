package com.github.bloomfilter.redis;

import io.rebloom.client.ClusterClient;
import redis.clients.jedis.HostAndPort;

import java.util.Set;

public class RedisClusterClient implements RedisClient {

    private ClusterClient clusterClient;
    private Set<HostAndPort> nodes;

    public void init(){
        clusterClient = new ClusterClient(nodes);
    }
    @Override
    public boolean add(final String key,final String value){
        return  clusterClient.add(key,value);
    }
    @Override
    public boolean[] addMulti(final String key, final String... values){
        return  clusterClient.addMulti(key,values);

    }
    @Override
    public boolean exists(final String key, final String value){
        return clusterClient.exists(key,value);
    }
    @Override
    public boolean[] existsMulti(final String key, final String... values){
        return clusterClient.existsMulti(key,values);
    }

    public void setNodes(Set<HostAndPort> nodes) {
        this.nodes = nodes;
    }
}
