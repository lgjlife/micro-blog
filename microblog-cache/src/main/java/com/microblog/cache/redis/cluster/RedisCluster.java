package com.microblog.cache.redis.cluster;

import com.microblog.cache.redis.config.ClusterProperties;
import com.microblog.cache.redis.config.JedisProperties;
import com.microblog.cache.redis.exception.RedisAddressException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Slf4j
public class RedisCluster {

    private JedisProperties jedisProperties;

    public RedisCluster(JedisProperties jedisProperties) {
        this.jedisProperties = jedisProperties;
    }

    public void connect(){

        ClusterProperties cluster = jedisProperties.getCluster();

        Set<HostAndPort> nodes = new HashSet<>();
        String[] address =  cluster.getNodes();
        for(String addr:address){
            String[] addrs = addr.split(":");
            if( !isIPv4Host(addrs[0]) || !isPort(addrs[1])){

                throw new RedisAddressException("Redis address[" + addr + "] error!");
            }

            HostAndPort hostAndPort  = new HostAndPort(addrs[0],Integer.valueOf(addrs[1]));
            nodes.add(hostAndPort);

        }

        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(cluster.getMaxIdle());
        poolConfig.setMaxTotal(cluster.getMaxTotal());
        poolConfig.setMinIdle(cluster.getMinIdle());

        JedisCluster jedisCluster = new JedisCluster(nodes,
                cluster.getConnectionTimeout(),
                cluster.getSoTimeout(),
                cluster.getMaxAttempts(),
                cluster.getPassword(),
                cluster.getClientName(),
                poolConfig);

    }



    public void setJedisProperties(JedisProperties jedisProperties) {
        this.jedisProperties = jedisProperties;
    }


    private static boolean isIPv4Host(String IPV4Address){

        String patten = "^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$";
        return Pattern.matches(patten,IPV4Address);
    }

    private static boolean isPort(String port){

        try{
            Integer p =  Integer.valueOf(port);
            if((p > 0)&&(p < 65536 )){
                return true;
            }

        }
        catch(Exception ex){
            log.error(ex.getMessage());

        }
        return false;

    }

    public static void main(String args[]){

        System.out.println(isIPv4Host("127.0.1.1"));
        System.out.println(isPort("4136"));
    }

}
