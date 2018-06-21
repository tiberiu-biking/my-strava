package com.tpo.fitness.service.sync;

import com.tpo.fitme.domain.Athlete;

/**
 * @author Tiberiu
 * @since 04.10.17
 */
public interface Synchronizer {

    void sync(Athlete athlete);
}
