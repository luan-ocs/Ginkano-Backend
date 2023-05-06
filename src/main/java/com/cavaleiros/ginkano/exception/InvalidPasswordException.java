package com.cavaleiros.ginkano.exception;

public class InvalidPasswordException extends Exception{

    public InvalidPasswordException(String title){
        super(title);
    }
}
