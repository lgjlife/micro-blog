package com.microblog.filesystem.upload;

import lombok.Data;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 *功能描述
 * @author lgj
 * @Description  上传的对象
 * @date 6/18/19
*/
@Data
public class UpLoadObject {

    private String group;
    private String path;
    private String name;
    private InputStream inputStream;
    private long size;
    private Map<String,Object> metaDate =  new HashMap<String, Object>();
}
