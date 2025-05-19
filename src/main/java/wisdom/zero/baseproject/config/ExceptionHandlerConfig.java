package wisdom.zero.baseproject.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import io.sentry.Sentry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import wisdom.zero.baseproject.util.dto.generic.ErrorResponseTO;
import wisdom.zero.baseproject.util.exception.BadRequestException;
import wisdom.zero.baseproject.util.exception.ConflictException;
import wisdom.zero.baseproject.util.exception.ForbiddenException;
import wisdom.zero.baseproject.util.exception.UnauthorizedException;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

@ControllerAdvice
public class ExceptionHandlerConfig {

    @Autowired
    private ObjectMapper mapper;

    @ExceptionHandler
    public ResponseEntity<ErrorResponseTO> handle(ForbiddenException ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        ErrorResponseTO.builder().status(HttpStatus.FORBIDDEN).error(ex.getMessage()).build()
                );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseTO> handle(MethodArgumentNotValidException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        ErrorResponseTO.builder().status(HttpStatus.BAD_REQUEST).error(ex.getMessage()).build()
                );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseTO> handle(InstanceAlreadyExistsException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        ErrorResponseTO.builder().status(HttpStatus.CONFLICT).error(ex.getMessage()).build()
                );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseTO> handle(InstanceNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        ErrorResponseTO.builder().status(HttpStatus.NOT_FOUND).error(ex.getMessage()).build()
                );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseTO> handle(BadRequestException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        ErrorResponseTO.builder().status(HttpStatus.BAD_REQUEST).error(ex.getMessage()).build()
                );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseTO> handle(UnauthorizedException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        ErrorResponseTO.builder().status(HttpStatus.UNAUTHORIZED).error(ex.getMessage()).build()
                );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseTO> handle(ConflictException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        ErrorResponseTO.builder().status(HttpStatus.CONFLICT).error(ex.getMessage()).build()
                );
    }

    @ExceptionHandler
    ResponseEntity<ErrorResponseTO> handle(FeignException ex) {
        try {
            if (HttpStatus.valueOf(ex.status()).is5xxServerError()) {
                Sentry.captureException(ex);
            }
        } catch (IllegalArgumentException ex2) {
        }

        Object response = "";

        if (!ex.contentUTF8().isEmpty()) {
            try {
                response = mapper.readValue(ex.contentUTF8(), Object.class);
            } catch (JsonProcessingException e) {
                response = ex.contentUTF8();
            }
        }

        return ResponseEntity
                .status(ex.status())
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        ErrorResponseTO.builder()
                                .status(HttpStatus.valueOf(ex.status()))
                                .error(response).build()
                );
    }


}
