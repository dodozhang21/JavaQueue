package net.pureessence.example.service;


import net.pureessence.example.TestLogger;
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
import java.util.List;
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

    @Autowired
    private TestLogger log;

    @Test
    @Rollback(false)
    public void testJob1() {
        Job job = job("1");
        jobDao.save(job);
    }

    @Test
    @Rollback(false)
    public void testJob2() {
        Job job = job("2");
        jobDao.save(job);
    }

    @Test
    @Rollback(false)
    public void testJob3() {
        Job job = job("3");
        jobDao.save(job);
    }

    @Test
    @Rollback(false)
    public void testJob4() {
        Job job = job("4");
        jobDao.save(job);
    }

    @Test
    @Rollback(false)
    public void testJob5() {
        Job job = job("5");
        jobDao.save(job);
    }

    @Test
    @Rollback(false)
    public void testJob5Log() throws InterruptedException {
        Thread.sleep(1000);
        for (String infoMessage : log.getInfoMessages()) {
            System.out.println("infoMessage = " + infoMessage);
        }

        assertTrue(containsStringStartWith(log.getInfoMessages(), "insert jobName 'job 1'"));
        assertTrue(containsStringStartWith(log.getInfoMessages(), "insert jobName 'job 2'"));
        assertTrue(containsStringStartWith(log.getInfoMessages(), "insert jobName 'job 3'"));
        assertTrue(containsStringStartWith(log.getInfoMessages(), "insert jobName 'job 4'"));
        assertTrue(containsStringStartWith(log.getInfoMessages(), "insert jobName 'job 5'"));

        assertTrue(containsStringStartWith(log.getInfoMessages(), "delete job id '1'"));
        assertTrue(containsStringStartWith(log.getInfoMessages(), "delete job id '2'"));
        assertTrue(containsStringStartWith(log.getInfoMessages(), "delete job id '3'"));
        assertTrue(containsStringStartWith(log.getInfoMessages(), "delete job id '4'"));
        assertTrue(containsStringStartWith(log.getInfoMessages(), "delete job id '5'"));

        assertTrue(jobDao.getAll().isEmpty());
    }

    @Test
    @Rollback(false)
    public void testJob7() {
        Job job = job("7");
        jobDao.save(job);
    }

    @Test
    @Rollback(false)
    public void testJob8() {
        Job job = job("8");
        jobDao.save(job);
    }

    @Test
    @Rollback(false)
    public void testJob9() {
        Job job = job("9");
        jobDao.save(job);
    }

    @Test
    @Rollback(false)
    public void testJob9Log() throws InterruptedException {
        Thread.sleep(1000);
        for (String infoMessage : log.getInfoMessages()) {
            System.out.println("infoMessage = " + infoMessage);
        }

        assertTrue(containsStringStartWith(log.getInfoMessages(), "insert jobName 'job 7'"));
        assertTrue(containsStringStartWith(log.getInfoMessages(), "insert jobName 'job 8'"));

        assertTrue(containsStringStartWith(log.getInfoMessages(), "delete job id '7'"));
        assertTrue(containsStringStartWith(log.getInfoMessages(), "delete job id '8'"));

        assertTrue(jobDao.getAll().isEmpty());
    }

    @Ignore
    @Rollback(false)
    public void testA() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Job job = job(String.valueOf(i));
            jobDao.save(job);

            Thread.sleep(1000);
        }
    }

    private static boolean containsStringStartWith(List<String> messages, String message) {
        for (String s : messages) {
            if(s.startsWith(message)) {
                return true;
            }
        }
        return false;
    }

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
