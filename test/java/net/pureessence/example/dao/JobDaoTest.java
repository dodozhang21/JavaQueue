package net.pureessence.example.dao;


import net.pureessence.example.domain.Job;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

@ContextConfiguration(locations={"/TestContext.xml"})
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class JobDaoTest {
    @Autowired
    private GenericDaoImpl<Job> jobDao;

    // http://stackoverflow.com/questions/1939229/how-to-create-background-process-in-spring-webapp
    @Autowired
    private LinkedBlockingQueue<Job> jobQueue;

    @Test
    public void testSaveFindDelete() throws InterruptedException {
        Job job = new Job();
        job.setJobName("myJob");
        job.setJobDescription("my job");
        job.setRequestTime(new Date());

        jobDao.save(job);
        List<Job> results = jobDao.getAll();
        assertEquals(1, results.size());

        Job fetchedJob = results.get(0);
        assertEquals("myJob", fetchedJob.getJobName());
        assertEquals("my job", fetchedJob.getJobDescription());

        jobDao.delete(fetchedJob);

        assertTrue(jobDao.getAll().isEmpty());
    }
}
