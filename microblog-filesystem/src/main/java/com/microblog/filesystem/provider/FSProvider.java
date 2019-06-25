package com.microblog.filesystem.provider;

import com.microblog.filesystem.upload.UpLoadObject;

/**
 *功能描述
 * @author lgj
 * @Description 文件系统接口
 * @date 6/18/19
*/
public interface FSProvider {

    String upLoad(UpLoadObject upLoadObject);
    Boolean delete(String path);

}
