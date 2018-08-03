package com.nyapplication.web_service;

import com.nyapplication.data_models.Error;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ErrorUtils {

    public static Error parseError(Response<?> response) {
        Converter<ResponseBody, Error> converter =
                ApiClient.getClient()
                        .responseBodyConverter(Error.class, new Annotation[0]);

        Error error;

        try {
            error = converter.convert(response.errorBody());

            if (error.getMessage().equalsIgnoreCase("UnAuthorised") && error.getCode() != 401) {
                error.setCode(401);
            }


        } catch (IOException e) {
            return new Error();
        }

        return error;
    }
}