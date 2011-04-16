package net.pureessence.example.service;


import net.pureessence.example.TestLogger;
import net.pureessence.example.dao.GenericDaoImpl;
import net.pureessence.example.domain.Job;
import org.apache.commons.logging.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@ContextConfiguration(locations={"/TestContext.xml"})
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class JobQueueRunnerTest {
    @Autowired
    private GenericDaoImpl<Job> jobDao;

    @Autowired
    private TestLogger log;

    @Test
    public void testQueue() throws InterruptedException {
        log.getInfoMessages().clear();

        for(int i = 0; i < 5; i++) {
            Job job = job("job " + i, "job " + i + " desc");
            jobDao.save(job);

            Thread.sleep(1000);
        }

//        for(String infoMessage : log.getInfoMessages()) {
//            System.out.println(infoMessage);
//        }
    }

    private static Job job(String jobName, String jobDescription) {
        Job job = new Job();
        job.setJobName(jobName);
        job.setJobDescription(jobDescription);
        job.setRequestTime(new Date());
        return job;
    }
}
