package com.jsmu.backendata.service;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.jsmu.backendata.repository.UserRepository;

@Service
public class NameService {

    private final UserRepository userRepository;

    public NameService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> findNamesStartingWith(String prefix) {
        String effectivePrefix = prefix == null ? "" : prefix;
        String normalizedPrefix = effectivePrefix.toLowerCase(Locale.ROOT);
        return userRepository.findUsernamesStartingWith(normalizedPrefix);
    }
}
