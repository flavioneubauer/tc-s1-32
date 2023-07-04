package br.com.fiap.soat1.t32.pedidos.driver;

import br.com.fiap.soat1.t32.pedidos.domain.CategoriaProduto;
import br.com.fiap.soat1.t32.pedidos.driver.vo.request.ProdutoVo;
import br.com.fiap.soat1.t32.pedidos.driver.vo.response.ConsultaProdutoResponse;
import br.com.fiap.soat1.t32.pedidos.use_case.ProdutoService;
import br.com.fiap.soat1.t32.utils.mappers.ProdutoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Produto", description = "API de Produtos")
@RequestMapping(value = "/v1/produtos",
        consumes = {APPLICATION_JSON_VALUE, ALL_VALUE},
        produces = {APPLICATION_JSON_VALUE})
@RestController
@RequiredArgsConstructor
public class ProdutoResource {

    private final ProdutoService produtoService;

    @Operation(description = "Inclui produto")
    @PostMapping(consumes = {APPLICATION_JSON_VALUE, ALL_VALUE},
            produces = {ALL_VALUE})
    public ResponseEntity<Void> criarProduto(@RequestBody @Valid ProdutoVo produtoVo) {

        produtoService.criarProduto(ProdutoMapper.map(produtoVo, null));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(description = "Exclui produto")
    @DeleteMapping(path = "/{produtoId}",
            consumes = {APPLICATION_JSON_VALUE, ALL_VALUE},
            produces = {ALL_VALUE})
    public ResponseEntity<Void> excluirProduto(@PathVariable Long produtoId) {

        produtoService.excluirProduto(produtoId);

        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Editar produto")
    @PutMapping(path = "/{produtoId}",
            consumes = {APPLICATION_JSON_VALUE, ALL_VALUE},
            produces = {ALL_VALUE})
    public ResponseEntity<Void> editarProduto(@PathVariable Long produtoId,
                                              @RequestBody @Valid ProdutoVo produtoVo) {

        produtoService.editarProduto(ProdutoMapper.map(produtoVo, produtoId));

        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Consultar produto por categoria")
    @GetMapping(path = "/{categoriaProduto}",
            consumes = {ALL_VALUE},
            produces = {APPLICATION_JSON_VALUE, ALL_VALUE})
    public ResponseEntity<ConsultaProdutoResponse> consultarProdutoPorCategoria(@PathVariable CategoriaProduto categoriaProduto) {

        final var produtos =
                produtoService.consultarProdutoPorCategoria(categoriaProduto);

        return ResponseEntity.ok(ProdutoMapper.mapResponse(produtos));
    }
}
