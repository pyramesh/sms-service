package com.routee.codechallenge.smsservicelatest;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Ramesh.Yaleru on 5/26/2021
 */
public class SMSScheduler {

    static int NUMBER_OF_EXECUTION_PER_RUN= 10;
    void startJob() throws IOException {
        System.out.println("start of startJOb");
        SMSServiceClient client = new SMSServiceClient();
            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            Runnable task = () -> {
                int count =0;
                while (count <NUMBER_OF_EXECUTION_PER_RUN){
                    count++;
                    String response = null;
                    try {
                        response = client.process();
                        System.out.println("Final response for "+count +" is "+response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
               count=0;
            };
            executor.scheduleWithFixedDelay(task, 0, 10, TimeUnit.MINUTES);
            System.out.println("end of startJOb");
    }

    public static void main(String[] args) throws IOException {
        SMSScheduler smsScheduler = new SMSScheduler();
        smsScheduler.startJob();
    }
}
