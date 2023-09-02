package br.com.fiap.soat1.t32.handler.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RespostaErro {

    private String timestamp;
    private Integer status;
    private String statusDescription;
    private List<DetalheErro> errors;

}
