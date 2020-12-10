package com.xiu.async.job;

/**
 * @ClassName PmTask
 * @Desc 产品经理任务
 * @Author xieqx
 * @Date 2020/12/9 16:03
 **/
public class PmJob implements Job {
    @Override
    public void execute() {
        System.out.println("产品经理开评审会议");
        try {
            Thread.sleep(10);
            System.out.println("模拟需求评审会议...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

