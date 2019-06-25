# 文件存储模块
文件存储模块目前仅支持FastDFS分布式存储。

# 使用

## 引入依赖
```xml

<dependency>
    <groupId>com.microblog</groupId>
    <artifactId>filesystem</artifactId>
    <version>1.0.0</version>
</dependency>
```
### yml配置
```yaml
fdfs:
  so-timeout: 1501
  connect-timeout: 100
  thumb-image: # 缩略图
    width: 60
    height: 60
  tracker-list: # tracker地址
    - 127.0.0.1:22122
```

### 使用测试


```java
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FilesystemApplication.class)
public class FastdfsClientTest {

    @Autowired
    FSProvider fastdfsClient;


    @Test
    public void upLoad() throws Exception {


        File file = new File("pic/timg.jpg");
        log.info("path = " + file.getAbsolutePath());
        InputStream ins = new FileInputStream(file);

        UpLoadObject upLoadObject = new UpLoadObjectBuilder().fileExtName("jpg")
               .path(file.getAbsolutePath()).size(ins.available()).inputStream(ins)
                .metaDate(new HashMap<>()).build();

        String path = fastdfsClient.upLoad(upLoadObject);

        log.info("保存的地址:"+ path);

    }
}
```

