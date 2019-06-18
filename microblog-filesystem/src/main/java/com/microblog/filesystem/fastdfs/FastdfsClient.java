package com.microblog.filesystem.fastdfs;

import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.upload.FastFile;
import com.github.tobato.fastdfs.service.DefaultFastFileStorageClient;
import com.microblog.filesystem.provider.AbstractFileProvider;
import com.microblog.filesystem.upload.UpLoadObject;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *功能描述
 * @author lgj
 * @Description  fastdfs 客户端操作
 * @date 6/18/19
*/
public class FastdfsClient extends AbstractFileProvider {

    private DefaultFastFileStorageClient storageClient;


    public FastdfsClient(DefaultFastFileStorageClient storageClient) {
        this.storageClient = storageClient;
    }

    /**
     *功能描述
     * @author lgj
     * @Description  上传文件
     * @date 6/18/19
    */
    public String upLoad(UpLoadObject upLoadObject) {

        Set<MetaData> metaDataSet = new HashSet<MetaData>();
        Map<String,Object> inputMetaDataMap = upLoadObject.getMetaDate();
        Set<String> keys= inputMetaDataMap.keySet();
        for(String key:keys){

            MetaData metaData = new MetaData(key,(String) inputMetaDataMap.get(key));
            metaDataSet.add(metaData);
        }

        FastFile fastFile = new FastFile.Builder()
                .withFile(upLoadObject.getInputStream(),upLoadObject.getSize(),upLoadObject.getFileExtName())
                //.toGroup(group)
                .withMetaData(metaDataSet)
                .build();

        StorePath storePath = storageClient.uploadFile(fastFile);

        return storePath.getFullPath();
    }

    public Boolean delete(String path) {
        storageClient.deleteFile(path);
        return true;
    }
}
