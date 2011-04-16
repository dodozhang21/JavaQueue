package net.pureessence.example.service;


import net.pureessence.example.domain.Job;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;

@Service
public class JobQueueRunner implements Runnable {
    @Autowired
    private LinkedBlockingQueue<Job> jobQueue;

    @Autowired
    private Log log;

    public void run() {
        try {
            while (true) {
                Job job = jobQueue.take();
//                log.info(String.format("jobName '%s', jobDescription '%s', requestTime '%s'", job.getJobName(), job.getJobDescription(), job.getRequestTime()));
                System.out.println(String.format("jobName '%s', jobDescription '%s', requestTime '%s'", job.getJobName(), job.getJobDescription(), job.getRequestTime()));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}