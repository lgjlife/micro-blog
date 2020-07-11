package com.microblog.gateway.auth;


import com.microblog.common.zk.ListenerEventHandler;
import com.microblog.common.zk.ZkCli;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class AuthFilterService {


    private ZkCli zkCli;

    private final String rootPath = "/microblog";
    private final String subPath = "/microblog";

    Map<String , List<String>> authPathMaps = new ConcurrentHashMap<>();

    /**
     *功能描述
     * @author lgj
     * @Description  判断当前的路径是否需要校验token
     *               还需要优化:1. “/blog/c” /blog/b  需要拦截，但"/blog/a" 不需要拦截  这种情况
     *                        ２. 效率优化
     * @date 2/28/19
     * @param:
     * @return:
     *
    */
    public  boolean needFilter(HttpServletRequest request){
        String servletPath =  request.getServletPath();
        log.debug("servletPath={}",servletPath);

        AntPathMatcher matcher =  new AntPathMatcher();

        Set<String> keys =  authPathMaps.keySet();

        for(String key:keys){
            List<String> paths = authPathMaps.get(key);
            for(String path:paths){

                log.debug("matcher pattern=[{}] ,path=[{}]",path,servletPath);
                if(matcher.match(path,servletPath)==true){
                    log.debug("拦截{}",servletPath);
                    return true;
                }
            }

        }
        log.debug("不拦截{}",servletPath);
        return  false;
    }




    /**
     *功能描述
     * @author lgj
     * @Description  设置zk监听器
     * @date 5/29/19
     * @param:
     * @return:
     *
    */
    public void setListen(){

        zkCli.setListener(new ListenerEventHandlerImpl(),getPath());
    }

    /**
     *功能描述
     * @author lgj
     * @Description　 　zk监听结果处理
     * @date 5/29/19
    */
    class ListenerEventHandlerImpl implements ListenerEventHandler {

        /**
         *功能描述
         * @author lgj
         * @Description  新增数据处理,在子应用启动时触发
         * @date 5/29/19
         * @param:
         * @return:
         *
        */
        @Override
        public void addHandler(ChildData data) {

            log.debug("ListenerEventHandlerImpl　addHandler");
            byte[] body = data.getData();
            if(body.length == 0){
                log.debug("zk read 0 byte!");
                return;
            }
            String path = data.getPath();
            log.info("body-len[{}],value={}",body.length,new String(body));
            Map<String , List<String>> pathMaps = ( Map<String , List<String>>)zkCli.byteToObject(body,Map.class);

            pathMaps.forEach((k,v)->{
                authPathMaps.put(k,v);
            });

            log.info("path ={},authPathMaps={}",path,authPathMaps);
        }

        /**
         *功能描述
         * @author lgj
         * @Description  移除数据处理，在子应用下线时处理
         * 　　　　　　　　子应用下线时，需不需要移除拦截?需要!
         *
         * @date 5/29/19
         * @param:
         * @return:
         *
        */
        @Override
        public void removeHandler(ChildData data) {
            log.debug("ListenerEventHandlerImpl　removeHandler");

            byte[] body = data.getData();
            String path = data.getPath();

            Map<String , List<String>> pathMaps = ( Map<String , List<String>>)zkCli.byteToObject(body,Map.class);

            pathMaps.forEach((k,v)->{
                authPathMaps.remove(k);
            });
            log.info("path ={},authPathMaps={}",path,authPathMaps);
        }

        @Override
        public void updateHandler(ChildData data) {
            log.debug("ListenerEventHandlerImpl　updateHandler");

            byte[] body = data.getData();
            String path = data.getPath();

            Map<String , List<String>> pathMaps = ( Map<String , List<String>>)zkCli.byteToObject(body,Map.class);

            pathMaps.forEach((k,v)->{
                authPathMaps.put(k,v);
            });

            log.info("path ={},authPathMaps={}",path,authPathMaps);

        }
    }


    public void setZkCli(ZkCli zkCli) {
        this.zkCli = zkCli;
    }


    private String getPath(){

        return rootPath + subPath ;
    }


    public static void main(String args[]){

        AntPathMatcher matcher =  new AntPathMatcher();

        List<String> paths = new ArrayList<>();

        //10000 -- 100ms
        for(int i = 0; i< 10000; i++){
            paths.add("/"+i + "/" + i);
        }

        long start = System.currentTimeMillis();
        for(String path:paths){

            matcher.match("/10000/10000",path);
        }
        long end = System.currentTimeMillis();

        System.out.println("time = " + (end - start) + " ms");



    }
}
