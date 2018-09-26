package com.tpo.fitme.service.sync;

import com.tpo.fitme.domain.Athlete;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author Tiberiu
 * @since 17.07.18
 */
@Slf4j
public class GroupSynchronizer implements Synchronizer {


    private final List<Synchronizer> synchronizers;

    public GroupSynchronizer(List<Synchronizer> synchronizers) {
        this.synchronizers = synchronizers;
    }

    @Override
    public void sync(Athlete athlete) {
        synchronizers.stream()
                .parallel()
                .forEach(synchronizer -> synchronizer.sync(athlete));
    }
}
