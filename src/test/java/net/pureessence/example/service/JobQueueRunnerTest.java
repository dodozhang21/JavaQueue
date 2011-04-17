package net.pureessence.example.service;


import net.pureessence.example.dao.GenericDaoImpl;
import net.pureessence.example.domain.Job;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

@ContextConfiguration(locations = {"/TestContext.xml"})
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class JobQueueRunnerTest {
    @Autowired
    private GenericDaoImpl<Job> jobDao;

    @Autowired
    @Qualifier("jobQueue")
    private LinkedBlockingQueue<Job> jobQueue;

    @Autowired
    @Qualifier("deleteJobQueue")
    private LinkedBlockingQueue<Job> deleteJobQueue;

//    @Autowired
//    private static TestLogger log;

    @Test
    @Rollback(false)
    public void testA() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Job job = job(String.valueOf(i));
            jobDao.save(job);

            Thread.sleep(1000);
        }
//        assertEquals(1, jobQueue.size());
    }

    @Ignore
    @Rollback(false)
    public void testQueue() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Job job = job("job " + i, "job " + i + " desc");
            jobDao.save(job);

            Thread.sleep(1000);
        }
        System.out.println(jobDao.getAll().size());
    }

//    @AfterClass
//    public static void testLog() {
//        for(String infoMessage : log.getInfoMessages()) {
//            System.out.println(infoMessage);
//        }
//    }

    private static Job job(String prefix) {
        return job("job " + prefix, "job " + prefix + " desc");
    }

    private static Job job(String jobName, String jobDescription) {
        Job job = new Job();
        job.setJobName(jobName);
        job.setJobDescription(jobDescription);
        job.setRequestTime(new Date());
        return job;
    }
}
