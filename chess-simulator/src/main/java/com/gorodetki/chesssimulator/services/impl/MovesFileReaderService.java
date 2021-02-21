package com.gorodetki.chesssimulator.services.impl;

import com.gorodetki.chesssimulator.services.abstraction.FileLinesReaderService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

@Service
public class MovesFileReaderService implements FileLinesReaderService {
    private static final String ERROR_MESSAGE = "\nException occurred while trying to read file: ";

    @Override
    public List<String> readAllLines(String path) {
        Path absolutePath = Paths.get(path);
        try {
            return Files.readAllLines(absolutePath);
        } catch (IOException e) {
            System.out.printf(ERROR_MESSAGE + "%s", path);
        }
        return Collections.emptyList();
    }
}
