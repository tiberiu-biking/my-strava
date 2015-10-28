package com.tpo.strava.persistence.repository;

import com.tpo.strava.persistence.PersistenceConfiguration;
import com.tpo.strava.persistence.entities.Activity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Tiberiu on 23/10/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PersistenceConfiguration.class)
public class ActivityRepositoryTest {

    @Autowired
    private ActivityRepository activityRepository;

    @Test
    public void executesQueryMethodsCorrectly() {
        List<Activity> activities = activityRepository.findAll();
        Assert.assertEquals(activities.size(), 0);
    }
}

