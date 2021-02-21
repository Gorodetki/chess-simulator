package com.gorodetki.chesssimulator.services.abstraction;

import java.util.List;

public interface FileLinesReaderService {
    List<String> readAllLines(String path);
}
