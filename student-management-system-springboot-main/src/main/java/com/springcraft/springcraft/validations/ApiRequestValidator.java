package com.springcraft.springcraft.validations;

import java.text.ParseException;

public interface ApiRequestValidator<T> {
    String isRequestValid(T request) throws ParseException;
}
