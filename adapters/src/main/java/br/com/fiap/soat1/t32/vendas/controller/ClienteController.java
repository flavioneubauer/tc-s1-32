package br.com.fiap.soat1.t32.vendas.controller;

import org.springframework.stereotype.Service;

import br.com.fiap.soat1.t32.vendas.adapter.ClienteAdapter;
import br.com.fiap.soat1.t32.vendas.api.vo.request.ClienteVO;
import br.com.fiap.soat1.t32.vendas.api.vo.response.ConsultaClienteResponse;
import br.com.fiap.soat1.t32.vendas.use_case.ClienteService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteController {
	
	private final ClienteService clienteService;
	
	public void cadastrar(ClienteVO clienteVO) {
		clienteService.cadastrar(ClienteAdapter.toEntity(clienteVO, null));
	}
	
	public ConsultaClienteResponse consultarPorCpf(String cpf) {
		final var cliente = clienteService.consultarPorCpf(cpf);
		return ClienteAdapter.toResponse(cliente);
	}
}
