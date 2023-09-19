package modulos.produto;

import dataFactory.ProdutoDataFactory;
import dataFactory.UsuarioDataFactory;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import modulos.pojo.ComponentePojo;
import modulos.pojo.ProdutoPojo;
import modulos.pojo.UsuarioPojo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest do modulo de Produto")
public class ProdutoTest {
    private String token;

    @BeforeEach
    public void beforeEach() {
        // Configuirando os dados da API Rest da Lojinha
        baseURI = "";
        basePath = "";

        // Obter o token do usuario admin

        this.token = given()
                .contentType(ContentType.JSON)
                .body(UsuarioDataFactory.criarUsuarioAdministrador())
            .when()
                .post("")
            .then()
                .extract()
                    .path("data.token");
    }

    @Test
    @DisplayName("Validar que o valor do produto igual a 0.00 nao e permitido")
    public void testValidarLimitesZeradoProibidoValorProduto() {

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProdutoComumComOValorIgualA(0.00))
            .when()
                .post("")
            .then()
                .assertThat()
                    .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                    .statusCode(422);
        }

        @Test
        @DisplayName("Validar que o valor do produto igual a 7000.01 nao e permitido")
        public void testValidarLimitesMaiorSeteMilProibidoValorProduto() {

            given()
                    .contentType(ContentType.JSON)
                    .header("token", this.token)
                    .body(ProdutoDataFactory.criarProdutoComumComOValorIgualA(7000.01))
                .when()
                    .post("")
                .then()
                    .assertThat()
                        .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                        .statusCode(422);

        }
}
