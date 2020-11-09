package com.xiu.mvc;

import org.junit.Test;

public class ThreadTest {

    @Test
    public void test(){
       Thread thread = new Thread(new TestTask());
       System.out.println("创建线程完成后的线程状态："+thread.getState());

       thread.start();

     for(int i=0;i<100000;i++){
           System.out.println("创建线程完成后的线程状态："+thread.getState());
      }
    }

    @Test
    public void testTwo(){
        int a = 1;
        int c = 1;
        //int b = a++ + ++a;
        int b =  a+++c++;
        System.out.println("b:" + b);

    }
    class TestTask implements Runnable{

        @Override
        public void run() {
            System.out.println("线程开始运行："+Thread.currentThread().getState());

            try {
                Thread.sleep(10L);
                System.out.println("线程阻塞："+Thread.currentThread().getState());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
