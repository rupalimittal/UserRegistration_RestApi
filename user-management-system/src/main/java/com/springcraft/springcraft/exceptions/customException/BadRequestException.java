package com.springcraft.springcraft.exceptions.customException;

public class BadRequestException extends  RuntimeException{
    public BadRequestException(String exceptionMsg)
    {
        super(exceptionMsg);
    }
}
