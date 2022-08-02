package com.optimagrowth.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class FeignClientErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String method, Response response) {
        log.info("Feign error decoding (method={})", method);
        OGErrorResponse errorResponse;
        try (InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            errorResponse = mapper.readValue(bodyIs, OGErrorResponse.class);
        } catch (IOException e) {
            return new OGException(HttpStatus.INTERNAL_SERVER_ERROR, "IE", "Error body deserialization error", e);
        }
        return new OGException(
                HttpStatus.resolve(response.status()),
                errorResponse.code(),
                errorResponse.message()
        );
    }
}
