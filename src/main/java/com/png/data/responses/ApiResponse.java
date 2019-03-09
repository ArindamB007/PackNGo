package com.png.data.responses;

import java.util.ArrayList;
import java.util.HashMap;

public class ApiResponse {
    private ArrayList<HashMap<String, String>> validationErrors;
    private HashMap<String, String> apiError;
    private Object responseData;
    private String message;

    public ArrayList<HashMap<String, String>> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(ArrayList<HashMap<String, String>> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public HashMap<String, String> getApiError() {
        return apiError;
    }

    public void setApiError(HashMap<String, String> apiError) {
        this.apiError = apiError;
    }

    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
