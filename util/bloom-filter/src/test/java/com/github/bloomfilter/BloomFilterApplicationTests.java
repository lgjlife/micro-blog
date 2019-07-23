package com.github.bloomfilter;

import com.github.bloomfilter.filter.BloomFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FilterApplication.class})
public class BloomFilterApplicationTests {

    @Autowired
    BloomFilter bloomFilter;

    @Test
    public void test1() {
        boolean addFlag = bloomFilter.add("key1","url1");
        boolean existsFlag1 = bloomFilter.exists("key1","url1");
        boolean existsFlag2 = bloomFilter.exists("key1","url11");
        log.info("addFlag = [{}], existsFlag1 = [{}], existsFlag2 = [{}]",addFlag,existsFlag1,existsFlag2);
    }

}
