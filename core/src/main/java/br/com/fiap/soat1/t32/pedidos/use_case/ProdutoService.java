package br.com.fiap.soat1.t32.pedidos.use_case;

import br.com.fiap.soat1.t32.pedidos.domain.CategoriaProduto;
import br.com.fiap.soat1.t32.pedidos.domain.Produto;
import br.com.fiap.soat1.t32.pedidos.ports.ProdutoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoPort produtoPort;

    public void criarProduto(Produto produto) {

        validaProduto(produto);

        this.produtoPort.criarProduto(produto);
    }

    private void validaProduto(Produto produto) {
        if(isNull(produto.getCategoria()) || isNull(produto.getDescricao()) ||
                (isNull(produto.getPreco()) || BigDecimal.ZERO.equals(produto.getPreco()))) {
            throw new RuntimeException("Dados inválidos para alteração/inclusão de produto.");
        }
    }

    public void excluirProduto(Long produtoId) {
        this.produtoPort.excluirProduto(produtoId);
    }

    public void editarProduto(Produto produto) {
        this.produtoPort.editarProduto(produto);
    }

    public Set<Produto> consultarProdutoPorCategoria(CategoriaProduto categoriaProduto) {
        return this.produtoPort.consultarProdutoPorCategoria(categoriaProduto);
    }

}
