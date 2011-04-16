package net.pureessence.example.service;

import net.pureessence.example.dao.GenericDaoImpl;
import net.pureessence.example.domain.Job;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;

@Service
public class DeleteJobQueueRunner implements Runnable {
    @Autowired
    @Qualifier("deleteJobQueue")
    private LinkedBlockingQueue<Job> deleteJobQueue;

    @Autowired
    private GenericDaoImpl<Job> jobDao;

    @Autowired
    private Log log;

    public void run() {
        while(true) {
            try {
                Job job = deleteJobQueue.take();
                job = jobDao.getById(job.getId());
                jobDao.delete(job);
                log.info(String.format("delete job id '%s'", job.getId()));
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
}
