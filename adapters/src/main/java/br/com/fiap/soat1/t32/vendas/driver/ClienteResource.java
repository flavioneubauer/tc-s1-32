package br.com.fiap.soat1.t32.vendas.driver;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import br.com.fiap.soat1.t32.handler.vo.RespostaErro;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.soat1.t32.vendas.utils.mappers.ClienteMapper;
import br.com.fiap.soat1.t32.vendas.driver.vo.request.ClienteVO;
import br.com.fiap.soat1.t32.vendas.driver.vo.response.ConsultaClienteResponse;
import br.com.fiap.soat1.t32.vendas.use_case.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Cliente", description = "API de Clientes")
@RequestMapping(value = "/v1/clientes",
        consumes = {APPLICATION_JSON_VALUE, ALL_VALUE},
        produces = {APPLICATION_JSON_VALUE})
@RestController
@RequiredArgsConstructor
public class ClienteResource {

	private final ClienteService clienteService;
	
	@Operation(description = "Inclui cliente")
    @PostMapping(consumes = {APPLICATION_JSON_VALUE, ALL_VALUE},
            produces = {ALL_VALUE})
	@ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso")
	@ApiResponse(responseCode = "422", description = "Erro de validação",
			content = @Content(schema = @Schema(implementation = RespostaErro.class)))
    public ResponseEntity<Void> cadastrarCliente(@RequestBody @Valid ClienteVO clienteVO) {

		clienteService.cadastrarCliente(ClienteMapper.toDomain(clienteVO, null));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
	
	@Operation(description = "Consultar cliente por CPF")
	@GetMapping(path = "/{cpf}", 
		consumes = { ALL_VALUE }, 
		produces = { APPLICATION_JSON_VALUE, ALL_VALUE })
	@ApiResponse(responseCode = "200", description = "Clientes listados com sucesso")
	@ApiResponse(responseCode = "422", description = "Erro de validação",
			content = @Content(schema = @Schema(implementation = RespostaErro.class)))
	public ResponseEntity<ConsultaClienteResponse> consultarClientePorCpf(@PathVariable String cpf) {

		final var cliente = clienteService.consultarClientePorCpf(cpf);

		return ResponseEntity.ok(ClienteMapper.toResponse(cliente));
	}
}