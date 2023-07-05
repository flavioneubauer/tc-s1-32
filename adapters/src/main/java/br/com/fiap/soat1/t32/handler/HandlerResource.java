package br.com.fiap.soat1.t32.handler;

import br.com.fiap.soat1.t32.exceptions.NotFoundException;
import br.com.fiap.soat1.t32.exceptions.ValidationException;
import br.com.fiap.soat1.t32.handler.vo.DetalheErro;
import br.com.fiap.soat1.t32.handler.vo.RespostaErro;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@Slf4j
public class HandlerResource {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespostaErro> tratarException(Exception e) {
        log.error("HANDLER EXCEPTION: ", e);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(getRespostaErro(e, INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<RespostaErro> tratarNotFoundException(NotFoundException nfe) {
        return ResponseEntity.status(NOT_FOUND).body(getRespostaErro(nfe, NOT_FOUND));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<RespostaErro> tratarValidationException(ValidationException ve) {
        return ResponseEntity.status(UNPROCESSABLE_ENTITY).body(getRespostaErro(ve, UNPROCESSABLE_ENTITY));
    }

    private RespostaErro getRespostaErro(Exception re,
                                         HttpStatus status) {
        return RespostaErro.builder()
                .timestamp(DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now()))
                .statusDescription(status.name())
                .status(status.value())
                .errors(getDetalheErro(re))
                .build();
    }

    private List<DetalheErro> getDetalheErro(Exception re) {
        return List.of(DetalheErro.builder()
                .message(re.getMessage())
                .build());
    }

}
