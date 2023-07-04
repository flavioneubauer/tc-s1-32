package br.com.fiap.soat1.t32.vendas.domain;

import java.util.UUID;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    private UUID id;
    private String nome;
    private String cpf;
    private String email;

}
