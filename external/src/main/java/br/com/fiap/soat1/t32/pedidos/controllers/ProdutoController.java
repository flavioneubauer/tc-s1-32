package br.com.fiap.soat1.t32.pedidos.controllers;

import br.com.fiap.soat1.t32.pedidos.adapters.ProdutoAdapter;
import br.com.fiap.soat1.t32.pedidos.apis.vo.request.ProdutoVo;
import br.com.fiap.soat1.t32.pedidos.apis.vo.response.ConsultaProdutoResponse;
import br.com.fiap.soat1.t32.pedidos.apis.vo.response.CriacaoProdutoResponse;
import br.com.fiap.soat1.t32.pedidos.entities.CategoriaProduto;
import br.com.fiap.soat1.t32.pedidos.use_cases.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    public CriacaoProdutoResponse cadastrar(ProdutoVo produtoVo) {
        final var idProduto = produtoService.cadastrar(ProdutoAdapter.toEntity(produtoVo, null));

        return ProdutoAdapter.toResponse(idProduto);
    }

    public void excluir(Long id) {
        produtoService.excluir(id);
    }

    public void editar(Long id, ProdutoVo produtoVo) {
        produtoService.editar(ProdutoAdapter.toEntity(produtoVo, id));
    }

    public ConsultaProdutoResponse listarPorCategoria(CategoriaProduto categoriaProduto) {
        final var produtos =
                produtoService.listarPorCategoria(categoriaProduto);

        return ProdutoAdapter.toResponse(produtos);
    }

}
