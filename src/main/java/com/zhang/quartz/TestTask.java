package com.zhang.quartz;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhenghua.zhang on 2017/8/28.
 */
@Component
public class TestTask {

    /*
    1 Seconds (0-59)
    2 Minutes (0-59)
    3 Hours (0-23)
    4 Day of month (1-31)
    5 Month (1-12 or JAN-DEC)
    6 Day of week (1-7 or SUN-SAT)
    7 Year (1970-2099)
    取值：可以是单个值，如6；
        也可以是个范围，如9-12；
        也可以是个列表，如9,11,13
        也可以是任意取值，使用*
*/
    //@Scheduled(cron = "0 0 1 * * ?")      // 每天凌晨1点整
    //@Scheduled(cron = "0 30 0 * * ?")     // 每天凌晨0点30分
    //@Scheduled(cron = "0 */60 * * * ?")   // 1小时处理一次
    @Scheduled(cron = "*/60 * * * * ?")      // 每隔60秒执行一次
    public void test() throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println(sdf.format(date));
    }

}
