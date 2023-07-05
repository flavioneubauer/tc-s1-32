package br.com.fiap.soat1.t32.pedidos.driven.repositories;

import br.com.fiap.soat1.t32.pedidos.domain.CategoriaProduto;
import br.com.fiap.soat1.t32.pedidos.domain.Produto;
import br.com.fiap.soat1.t32.pedidos.driven.repositories.dao.ProdutoDAO;
import br.com.fiap.soat1.t32.pedidos.ports.ProdutoPort;
import br.com.fiap.soat1.t32.pedidos.utils.mappers.ProdutoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
@RequiredArgsConstructor
public class ProdutoRepositoryImpl implements ProdutoPort {

    private final ProdutoDAO produtoDAO;

    @Override
    public void criarProduto(Produto produto) {
        produtoDAO.save(ProdutoMapper.map(produto));
    }

    @Override
    public void excluirProduto(Long produtoId) {
        produtoDAO.deleteById(produtoId);
    }

    @Override
    public void editarProduto(Produto produto) {
        produtoDAO.save(ProdutoMapper.map(produto));
    }

    @Override
    public Set<Produto> consultarProdutoPorCategoria(CategoriaProduto categoriaProduto) {
        final var produtos =
                produtoDAO.findAllByCategoria(categoriaProduto);

        return ProdutoMapper.map(produtos);
    }
}