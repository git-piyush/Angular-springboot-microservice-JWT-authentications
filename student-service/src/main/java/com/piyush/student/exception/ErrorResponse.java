package com.piyush.student.exception;

import java.util.Map;

public record ErrorResponse(
    Map<String, String> errors
) {

}
