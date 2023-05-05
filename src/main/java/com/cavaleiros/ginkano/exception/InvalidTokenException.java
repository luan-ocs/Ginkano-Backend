package com.cavaleiros.ginkano.exception;

public class InvalidTokenException extends Exception{

    public InvalidTokenException(String title){
        super(title);
    }
}
