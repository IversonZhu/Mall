package com.ok.mall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName LoggerTest
 * @Description TODO
 * @Author Iverson
 * @Date 2018/6/15 8:58
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoggerTest {

    private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test(){
        logger.debug("debug...");
        logger.info("info...");
        logger.error("error...");
    }
}
