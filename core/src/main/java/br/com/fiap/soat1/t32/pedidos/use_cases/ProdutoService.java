package br.com.fiap.soat1.t32.pedidos.use_cases;

import br.com.fiap.soat1.t32.exceptions.ValidationException;
import br.com.fiap.soat1.t32.pedidos.entities.CategoriaProduto;
import br.com.fiap.soat1.t32.pedidos.entities.Produto;
import br.com.fiap.soat1.t32.pedidos.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public Long cadastrar(Produto produto) {

        validarInclusaoAlteracao(produto);

        return this.produtoRepository.cadastrar(produto);
    }

    private void validarInclusaoAlteracao(Produto produto) {
        if(isNull(produto.getCategoria()) || isNull(produto.getDescricao()) ||
                (isNull(produto.getPreco()) || BigDecimal.ZERO.equals(produto.getPreco()))) {
            throw new ValidationException("Dados inválidos para alteração/inclusão de produto.");
        }
    }

    public void excluir(Long produtoId) {
        validarExclusao(produtoId);
        this.produtoRepository.excluir(produtoId);
    }

    private void validarExclusao(Long id) {

        var produto = produtoRepository.consultarPorId(id);

        if(produto == null) {
            throw new ValidationException("Produto não localizado.");
        }
    }

    public void editar(Produto produto) {
        this.produtoRepository.editar(produto);
    }

    public Set<Produto> listarPorCategoria(CategoriaProduto categoriaProduto) {
        return this.produtoRepository.listarPorCategoria(categoriaProduto);
    }

}
