package com.tpo.fitme.service.login;

import com.tpo.fitme.domain.Athlete;

/**
 * @author Tiberiu
 * @since 06.07.18
 */
public interface LoginService {

    Athlete login(String authCode);
}
