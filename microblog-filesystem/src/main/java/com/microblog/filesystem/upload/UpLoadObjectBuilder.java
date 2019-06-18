package com.microblog.filesystem.upload;

import java.io.InputStream;
import java.util.Map;

/**
 *功能描述
 * @author lgj
 * @Description UpLoadObject 构建器
 * @date 6/18/19
*/
public class UpLoadObjectBuilder {

    private UpLoadObject upLoadObject;
    public  UpLoadObjectBuilder(){
        upLoadObject = new UpLoadObject();
    }

    public UpLoadObjectBuilder path(String path){
        upLoadObject.setPath(path);
        return this;
    }
    public UpLoadObjectBuilder name(String name){
        upLoadObject.setName(name);
        return this;
    }
    public UpLoadObjectBuilder fileExtName(String fileExtName){
        upLoadObject.setFileExtName(fileExtName);
        return this;
    }

    public UpLoadObjectBuilder group(String group){
        upLoadObject.setGroup(group);
        return this;
    }
    public UpLoadObjectBuilder inputStream(InputStream inputStream){
        upLoadObject.setInputStream(inputStream);
        return this;
    }
    public UpLoadObjectBuilder size(long size){
        upLoadObject.setSize(size);
        return this;
    }
    public UpLoadObjectBuilder metaDate(Map<String,Object> metaDate){
        upLoadObject.setMetaDate(metaDate);
        return this;
    }

    public UpLoadObject build(){
        return upLoadObject;
    }
}
