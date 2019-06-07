package com.microblog.user.service.utils.fastdfs;

import com.github.tobato.fastdfs.domain.fdfs.GroupState;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorageNode;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.upload.FastFile;
import com.github.tobato.fastdfs.service.DefaultFastFileStorageClient;
import com.github.tobato.fastdfs.service.DefaultTrackerClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

/**
 *功能描述
 * @author lgj
 * @Description  fastdfs操作类
 * @date 6/4/19

fdfs:
so-timeout: 1501
connect-timeout: 100
thumb-image: # 缩略图
width: 60
height: 60
tracker-list: # tracker地址
- 127.0.0.1:22122

*/
@Slf4j
@Component
public class FastdfsUtil {

    @Autowired
    private DefaultFastFileStorageClient storageClient;

    @Autowired
    DefaultTrackerClient trackerClient;

    /**
     *功能描述
     * @author lgj
     * @Description  fastdfs上传文件
     * @date 6/4/19
     * @param:   group: 文件所在的组
     * 　　　　　　file: 文件
     * 　　　　　　metaDataSet: 自定义的元数据
     * @return:
     *
    */
    public String upload(String group,String fileName ,InputStream inputStream, long fileSize, Set<MetaData> metaDataSet)
            throws FileNotFoundException {


        log.debug("Upload the file [{}] to fastdfs-group[{}]",fileName,group);
        FastFile fastFile = new FastFile.Builder()
                .withFile(inputStream,fileSize,getFileExtName(fileName))
                //.toGroup(group)
                .withMetaData(metaDataSet)
                .build();

        StorePath storePath = storageClient.uploadFile(fastFile);
        // 带分组的路径
        log.debug("The file path is :{}",storePath.getFullPath());
        return storePath.getFullPath();

    }

    /**
     *功能描述
     * @author lgj
     * @Description 获取fastdfs各个group信息
     * @date 6/4/19
     * @param:
     * @return:
     *
    */
    public List<GroupState> listGroups() {
        return  trackerClient.listGroups();
    }

    /**
     *功能描述 
     * @author lgj
     * @Description  
     * @date 6/4/19
     * @param:  
     * @return:  
     *
    */
    public StorageNode getStoreStorage(){
        StorageNode storageNode = trackerClient.getStoreStorage();
        log.debug("StorageNode = {}" ,storageNode);
        return storageNode;
    }
    



    /**
     *功能描述
     * @author lgj
     * @Description  获取文件的后缀名称　　aaa.pic -> pic
     * @date 6/4/19
     * @param:
     * @return:
     *
    */
    private String getFileExtName(String fileName){
        String fileExtName = fileName.substring(fileName.lastIndexOf(".")+1);
        return fileExtName;

    }


}
