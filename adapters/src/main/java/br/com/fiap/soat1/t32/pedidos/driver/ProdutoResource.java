package br.com.fiap.soat1.t32.pedidos.driver;

import br.com.fiap.soat1.t32.handler.vo.RespostaErro;
import br.com.fiap.soat1.t32.pedidos.domain.CategoriaProduto;
import br.com.fiap.soat1.t32.pedidos.driver.vo.request.ProdutoVo;
import br.com.fiap.soat1.t32.pedidos.driver.vo.response.ConsultaProdutoResponse;
import br.com.fiap.soat1.t32.pedidos.driver.vo.response.CriacaoProdutoResponse;
import br.com.fiap.soat1.t32.pedidos.use_case.ProdutoService;
import br.com.fiap.soat1.t32.pedidos.utils.mappers.ProdutoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
            produces = {APPLICATION_JSON_VALUE, ALL_VALUE})
    @ApiResponse(responseCode = "201", description = "Produto criado com sucesso")
    @ApiResponse(responseCode = "422", description = "Erro de validação",
            content = @Content(schema = @Schema(implementation = RespostaErro.class)))
    public ResponseEntity<CriacaoProdutoResponse> criarProduto(@RequestBody ProdutoVo produtoVo) {

        final var idProduto = produtoService.criarProduto(ProdutoMapper.toDomain(produtoVo, null));

        return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoMapper.toResponse(idProduto));
    }

    @Operation(description = "Exclui produto")
    @DeleteMapping(path = "/{produtoId}",
            consumes = {APPLICATION_JSON_VALUE, ALL_VALUE},
            produces = {ALL_VALUE})
    @ApiResponse(responseCode = "204", description = "Produto excluído com sucesso")
    @ApiResponse(responseCode = "422", description = "Erro de validação",
            content = @Content(schema = @Schema(implementation = RespostaErro.class)))
    public ResponseEntity<Void> excluirProduto(@PathVariable Long produtoId) {

        produtoService.excluirProduto(produtoId);

        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Editar produto")
    @PutMapping(path = "/{produtoId}",
            consumes = {APPLICATION_JSON_VALUE, ALL_VALUE},
            produces = {ALL_VALUE})
    @ApiResponse(responseCode = "204", description = "Produto alterado com sucesso")
    @ApiResponse(responseCode = "422", description = "Erro de validação",
            content = @Content(schema = @Schema(implementation = RespostaErro.class)))
    public ResponseEntity<Void> editarProduto(@PathVariable Long produtoId,
                                              @RequestBody ProdutoVo produtoVo) {

        produtoService.editarProduto(ProdutoMapper.toDomain(produtoVo, produtoId));

        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Consultar produto por categoria")
    @GetMapping(path = "/{categoriaProduto}",
            consumes = {ALL_VALUE},
            produces = {APPLICATION_JSON_VALUE, ALL_VALUE})
    @ApiResponse(responseCode = "200", description = "Lista de produtos por categoria retornada com sucesso")
    @ApiResponse(responseCode = "422", description = "Erro de validação",
            content = @Content(schema = @Schema(implementation = RespostaErro.class)))
    public ResponseEntity<ConsultaProdutoResponse> consultarProdutoPorCategoria(@PathVariable CategoriaProduto categoriaProduto) {

        final var produtos =
                produtoService.listarProdutosPorCategoria(categoriaProduto);

        return ResponseEntity.ok(ProdutoMapper.toResponse(produtos));
    }
}
