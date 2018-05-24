package org.baeldung.service;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

import org.baeldung.web.dto.Third;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class ThirdService {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Third findById(long id) {
        return new Third(Long.parseLong(randomNumeric(2)), randomAlphabetic(4));
    }

}
