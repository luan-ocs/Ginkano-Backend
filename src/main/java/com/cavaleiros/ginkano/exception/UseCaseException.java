package com.cavaleiros.ginkano.exception;

public class UseCaseException extends Exception{

    public UseCaseException(String title, Throwable e){
        super(title, e);
    }

    public UseCaseException(String title){
        super(title);
    }
}
