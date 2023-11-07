package br.com.fiap.soat1.t32.pedidos.apis;

import br.com.fiap.soat1.t32.handler.vo.RespostaErro;
import br.com.fiap.soat1.t32.pedidos.controllers.ProdutoController;
import br.com.fiap.soat1.t32.pedidos.entities.CategoriaProduto;
import br.com.fiap.soat1.t32.pedidos.apis.vo.request.ProdutoVo;
import br.com.fiap.soat1.t32.pedidos.apis.vo.response.ConsultaProdutoResponse;
import br.com.fiap.soat1.t32.pedidos.apis.vo.response.CriacaoProdutoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    private final ProdutoController produtoController;

    @Operation(description = "Inclui produto")
    @PostMapping(consumes = {APPLICATION_JSON_VALUE, ALL_VALUE},
            produces = {APPLICATION_JSON_VALUE, ALL_VALUE})
    @ApiResponse(responseCode = "201", description = "Produto criado com sucesso")
    @ApiResponse(responseCode = "422", description = "Erro de validação",
            content = @Content(schema = @Schema(implementation = RespostaErro.class)))
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<CriacaoProdutoResponse> criarProduto(@RequestBody ProdutoVo produtoVo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoController.cadastrar(produtoVo));
    }

    @Operation(description = "Exclui produto")
    @DeleteMapping(path = "/{produtoId}",
            consumes = {APPLICATION_JSON_VALUE, ALL_VALUE},
            produces = {ALL_VALUE})
    @ApiResponse(responseCode = "204", description = "Produto excluído com sucesso")
    @ApiResponse(responseCode = "422", description = "Erro de validação",
            content = @Content(schema = @Schema(implementation = RespostaErro.class)))
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<Void> excluirProduto(@PathVariable Long produtoId) {

        produtoController.excluir(produtoId);

        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Editar produto")
    @PutMapping(path = "/{produtoId}",
            consumes = {APPLICATION_JSON_VALUE, ALL_VALUE},
            produces = {ALL_VALUE})
    @ApiResponse(responseCode = "204", description = "Produto alterado com sucesso")
    @ApiResponse(responseCode = "422", description = "Erro de validação",
            content = @Content(schema = @Schema(implementation = RespostaErro.class)))
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<Void> editarProduto(@PathVariable Long produtoId,
                                              @RequestBody ProdutoVo produtoVo) {

        produtoController.editar(produtoId, produtoVo);

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
        return ResponseEntity.ok(produtoController.listarPorCategoria(categoriaProduto));
    }
}
