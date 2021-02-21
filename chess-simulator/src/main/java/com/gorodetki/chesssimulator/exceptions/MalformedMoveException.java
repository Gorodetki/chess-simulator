package com.gorodetki.chesssimulator.exceptions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MalformedMoveException extends RuntimeException {
    private final String message;
}
