package com.xiu.async.job;

import java.util.concurrent.Callable;

/**
 * @ClassName PmTask
 * @Desc 研发任务
 * @Author xieqx
 * @Date 2020/12/9 16:03
 **/
public class RDJob implements Job {
    @Override
    public void execute() {
        System.out.println("程序猿开始开发");
        try {
            Thread.sleep(10);
            System.out.println("程序猿哼哧哼哧干活中...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

