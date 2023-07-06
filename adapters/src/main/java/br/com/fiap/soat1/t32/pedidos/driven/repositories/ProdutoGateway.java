package br.com.fiap.soat1.t32.pedidos.driven.repositories;

import br.com.fiap.soat1.t32.pedidos.domain.CategoriaProduto;
import br.com.fiap.soat1.t32.pedidos.domain.Produto;
import br.com.fiap.soat1.t32.pedidos.driven.repositories.dao.ProdutoDAO;
import br.com.fiap.soat1.t32.pedidos.ports.ProdutoPort;
import br.com.fiap.soat1.t32.pedidos.utils.mappers.ProdutoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProdutoGateway implements ProdutoPort {

    private final ProdutoDAO produtoDAO;

    @Override
    public Long criarProduto(Produto produto) {
        return produtoDAO.save(ProdutoMapper.toEntity(produto)).getId();
    }

    @Override
    public void excluirProduto(Long produtoId) {
        produtoDAO.deleteById(produtoId);
    }

    @Override
    public void editarProduto(Produto produto) {
        produtoDAO.save(ProdutoMapper.toEntity(produto));
    }

    @Override
    public Set<Produto> listarProdutosPorCategoria(CategoriaProduto categoriaProduto) {
        return ProdutoMapper.toDomain(produtoDAO.findAllByCategoria(categoriaProduto));
    }

    @Override
    public Produto consultarProduto(Long id) {
        var produto = produtoDAO.findById(id);

        return produto.map(ProdutoMapper::toDomain).orElse(null);
    }
}