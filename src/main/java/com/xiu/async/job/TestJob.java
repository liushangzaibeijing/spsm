package com.xiu.async.job;

import java.util.concurrent.Callable;

/**
 * @ClassName PmTask
 * @Desc 研发任务
 * @Author xieqx
 * @Date 2020/12/9 16:03
 **/
public class TestJob implements Job {
    @Override
    public void execute()  {
        System.out.println("测试开始测试");
        try {
            Thread.sleep(10);
            System.out.println("测试用例测试...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

