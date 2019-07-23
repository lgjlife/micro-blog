package com.github.bloomfilter;

import com.github.bloomfilter.filter.BloomFilterBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FilterApplication.class})
public class BloomFilterBaseApplicationTests {

    @Resource(name = "redisBloomFilter")
    BloomFilterBase redisBloomFilter;

    @Test
    public void test1() {
        boolean addFlag = redisBloomFilter.add("key1","url1");
        boolean existsFlag1 = redisBloomFilter.exists("key1","url1");
        boolean existsFlag2 = redisBloomFilter.exists("key1","url11");
        log.info("redis-addFlag = [{}], existsFlag1 = [{}], existsFlag2 = [{}]",addFlag,existsFlag1,existsFlag2);
    }

    @Resource(name = "guavaBloomFilter")
    BloomFilterBase guavaBloomFilter;

    @Test
    public void test2() {
        boolean addFlag = guavaBloomFilter.add("key1","url1");
        boolean existsFlag1 = guavaBloomFilter.exists("key1","url1");
        boolean existsFlag2 = guavaBloomFilter.exists("key1","url11");
        log.info("guava addFlag = [{}], existsFlag1 = [{}], existsFlag2 = [{}]",addFlag,existsFlag1,existsFlag2);
    }


}
