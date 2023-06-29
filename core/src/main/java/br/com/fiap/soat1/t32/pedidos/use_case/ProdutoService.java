package br.com.fiap.soat1.t32.pedidos.use_case;

import br.com.fiap.soat1.t32.pedidos.domain.CategoriaProduto;
import br.com.fiap.soat1.t32.pedidos.domain.Produto;
import br.com.fiap.soat1.t32.pedidos.ports.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public void criarProduto(Produto produto) {

        validaProduto(produto);

        this.produtoRepository.criarProduto(produto);
    }

    private void validaProduto(Produto produto) {
        if(isNull(produto.getCategoria()) || isNull(produto.getDescricao()) ||
                (isNull(produto.getPreco()) || BigDecimal.ZERO.equals(produto.getPreco()))) {
            throw new RuntimeException("Dados inválidos para alteração/inclusão de produto.");
        }
    }

    public void excluirProduto(Long produtoId) {
        this.produtoRepository.excluirProduto(produtoId);
    }

    public void editarProduto(Produto produto) {
        this.produtoRepository.editarProduto(produto);
    }

    public Set<Produto> consultarProdutoPorCategoria(CategoriaProduto categoriaProduto) {
        return this.produtoRepository.consultarProdutoPorCategoria(categoriaProduto);
    }

}
