package net.pureessence.example.dao;

import net.pureessence.example.domain.Job;
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostInsertEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;

@Component
public class PostInsertListener implements PostInsertEventListener {
    @Autowired
    private LinkedBlockingQueue<Job> jobQueue;

    public void onPostInsert(PostInsertEvent postInsertEvent) {
        if(postInsertEvent.getEntity() instanceof Job) {
            Job job = (Job)postInsertEvent.getEntity();
            try {
                jobQueue.put(job);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
}
