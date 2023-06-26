package br.com.fiap.soat1.t32.vendas.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    private Long id;
    private String nome;
    private String cpf;
    private String email;

}
