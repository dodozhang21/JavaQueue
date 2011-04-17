package net.pureessence.example.service;

import net.pureessence.example.dao.GenericDaoImpl;
import net.pureessence.example.domain.Job;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.LinkedBlockingDeque;

public class JobQueueSingleRunner implements Runnable {
    @Autowired
    private LinkedBlockingDeque<Job> jobQueue;
    @Autowired
    private GenericDaoImpl<Job> jobDao;

    @Autowired
    private Log log;

    public void run() {
        try {
            while (true) {
                Job job = jobQueue.take();
                log.info(String.format("insert jobName '%s', jobDescription '%s', requestTime '%s'", job.getJobName(), job.getJobDescription(), job.getRequestTime()));
                System.out.println(String.format("jobName '%s', jobDescription '%s', requestTime '%s'", job.getJobName(), job.getJobDescription(), job.getRequestTime()));
                job = jobDao.getById(job.getId());
                jobDao.delete(job);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
