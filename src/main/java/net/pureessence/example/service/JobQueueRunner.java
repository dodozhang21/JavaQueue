package net.pureessence.example.service;


import net.pureessence.example.dao.GenericDaoImpl;
import net.pureessence.example.domain.Job;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;

@Service
public class JobQueueRunner implements Runnable {
    @Autowired
    @Qualifier("jobQueue")
    private LinkedBlockingQueue<Job> jobQueue;

    @Autowired
    @Qualifier("deleteJobQueue")
    private LinkedBlockingQueue<Job> deleteJobQueue;

    @Autowired
    private GenericDaoImpl<Job> jobDao;

    @Autowired
    private Log log;

    public void run() {
        try {
            while (true) {
                Job job = jobQueue.take();
                log.info(String.format("insert jobName '%s', jobDescription '%s', requestTime '%s'", job.getJobName(), job.getJobDescription(), job.getRequestTime()));
//                System.out.println(String.format("jobName '%s', jobDescription '%s', requestTime '%s'", job.getJobName(), job.getJobDescription(), job.getRequestTime()));
//                job = jobDao.getById(job.getId());
//                jobDao.delete(job);
                deleteJobQueue.put(job);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}