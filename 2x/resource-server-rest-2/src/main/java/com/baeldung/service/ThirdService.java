package com.baeldung.service;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.baeldung.web.dto.Third;

@Service
public class ThirdService {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Third findById(long id) {
        return new Third(Long.parseLong(randomNumeric(2)), randomAlphabetic(4));
    }

}
