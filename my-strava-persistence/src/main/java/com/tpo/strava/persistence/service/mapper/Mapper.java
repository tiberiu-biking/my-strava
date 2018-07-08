package com.tpo.strava.persistence.service.mapper;

/**
 * @author Tiberiu
 * @since 06.10.17
 */
public interface Mapper<A, B> {
    A from(B from);

    B to(A to);
}
