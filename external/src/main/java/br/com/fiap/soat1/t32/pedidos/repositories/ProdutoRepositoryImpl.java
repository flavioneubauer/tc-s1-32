package br.com.fiap.soat1.t32.pedidos.repositories;

import br.com.fiap.soat1.t32.pedidos.entities.CategoriaProduto;
import br.com.fiap.soat1.t32.pedidos.entities.Produto;
import br.com.fiap.soat1.t32.pedidos.adapters.ProdutoAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProdutoRepositoryImpl implements ProdutoRepository {

    private final ProdutoCrudRepository produtoCrudRepository;

    @Override
    public Long cadastrar(Produto produto) {
        return produtoCrudRepository.save(ProdutoAdapter.toEntityDb(produto)).getId();
    }

    @Override
    public void excluir(Long produtoId) {
        produtoCrudRepository.deleteById(produtoId);
    }

    @Override
    public void editar(Produto produto) {
        produtoCrudRepository.save(ProdutoAdapter.toEntityDb(produto));
    }

    @Override
    public Set<Produto> listarPorCategoria(CategoriaProduto categoriaProduto) {
        return ProdutoAdapter.toEntity(produtoCrudRepository.findAllByCategoria(categoriaProduto));
    }

    @Override
    public Produto consultarPorId(Long id) {
        var produto = produtoCrudRepository.findById(id);

        return produto.map(ProdutoAdapter::toEntity).orElse(null);
    }
}