package com.jsmu.backendata.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class NameService {

    private static final String RESOURCE_PATH = "names.txt";

    public List<String> findNamesStartingWith(String prefix) {
        String normalizedPrefix = prefix.toLowerCase(Locale.ROOT);
        try (InputStream inputStream = new ClassPathResource(RESOURCE_PATH).getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines()
                    .map(String::trim)
                    .filter(name -> !name.isEmpty())
                    .filter(name -> name.toLowerCase(Locale.ROOT).startsWith(normalizedPrefix))
                    .collect(Collectors.toList());
        } catch (IOException exception) {
            throw new UncheckedIOException("Unable to load names from " + RESOURCE_PATH, exception);
        }
    }
}
