package com.eli.service.errors;

/**
 * Created by colpaertel on 26/09/2017.
 */
public class MatchWithoutPlayerException extends Exception {

    public MatchWithoutPlayerException(String message) {
        super(message);
    }
}
