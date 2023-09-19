package dataFactory;

import modulos.pojo.ComponentePojo;
import modulos.pojo.ProdutoPojo;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDataFactory {
    public static ProdutoPojo criarProdutoComumComOValorIgualA(double valor){
        ProdutoPojo produto = new ProdutoPojo();
        produto.setProdutoNome("Supernintendo");
        produto.setProdutoValor(valor);

        List<String> cores = new ArrayList<>();
        cores.add("roxo");

        produto.setProdutoCores(cores);
        produto.setProdutoUrlMock("");

        List<ComponentePojo> componentes = new ArrayList<>();

        ComponentePojo componente = new ComponentePojo();
        componente.setComponenteNome("Contorle");
        componente.setComponenteQuantidade(1);
        componentes.add(componente);
        produto.setComponentes(componentes);

        return produto;
    }
}
