package com.xiu.async;

import com.xiu.async.job.Job;
import com.xiu.async.job.PmJob;
import com.xiu.async.job.RDJob;
import com.xiu.async.job.TestJob;
import org.junit.internal.runners.statements.RunAfters;

import javax.servlet.AsyncContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName WeekTask
 * @Desc 每周任务
 * @Author xieqx
 * @Date 2020/12/10 9:36
 **/
public class WeekTask implements Runnable {

    private List<Job> jobs = null;

    private AsyncContext asyncContext = null;

    public WeekTask(AsyncContext asyncContext) {
       this.asyncContext = asyncContext;
       jobs = new ArrayList<>();
       PmJob pmJob = new PmJob();
       RDJob rdJob = new RDJob();
       TestJob testJob = new TestJob();
       jobs.add(pmJob);
       jobs.add(rdJob);
       jobs.add(testJob);
    }

    @Override
    public void run() {
        for(Job job:jobs){
            job.execute();
        }
        System.out.println("周任务工作完成");
        //job执行完成后通知
      asyncContext.complete();
    }
}
