import java.util.List;

public class CarrinhoDeComprasTest {

    @Test
    public void testAdicionarItem() {
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();

        Item item = new Item("Produto A", 10.00, 2);
        carrinho.adicionarItem(item);

        List<Item> itens = carrinho.getItens();
        assertEquals(1, itens.size());
        assertEquals("Produto A", itens.get(0).getNome());
        assertEquals(10.00, itens.get(0).getPreco(), 0.01);
        assertEquals(2, itens.get(0).getQuantidade());
    }

    @Test
    public void testAdicionarItemValorNegativoOuZeradaDeveRetornarUmException() {
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();

        Item item = new Item("Produto A", -10.00, 2);

        assertThrows(IllegalArgumentException.class, () -> {
            carrinho.adicionarItem(item);
        });
    }

    @Test
    public void testAdicionarItemQuantidadeZeradaOuNegativaDeveRetornarUmException() {
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();

        Item item = new Item("Produto A", 10.00, 0);

        assertThrows(IllegalArgumentException.class, () -> {
            carrinho.adicionarItem(item);
        });
    }

    @Test
    public void testRemoverItem() {
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
        Item item = new Item("Produto A", 10.00, 2);
        Item item2 = new Item("Produto B", 15.00, 1);

        carrinho.adicionarItem(item);
        carrinho.adicionarItem(item2);
        carrinho.removerItem(item);

        assertEquals(1, carrinho.getItens().size());
        assertEquals(item2, carrinho.getItens().getFirst());
    }

    @Test
    public void testRemoverItemQueNãoExiste() {
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
        Item item = new Item("Produto A", 10.00, 2);
        Item item2 = new Item("Produto B", 15.00, 1);

        carrinho.adicionarItem(item2);

        assertThrows(IllegalArgumentException.class, () -> {
            carrinho.removerItem(item);
        });
    }

    // Testes para antigo formato de função calcularValorTotal() que retorna valor
    // bruto ou liquido
    // @Test
    // public void testDeveCalcularValorDaCompra() {
    // CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
    // Item item1 = new Item("Produto A", 10.00, 2);
    // Item item2 = new Item("Produto B", 15.00, 1);

    // carrinho.adicionarItem(item1);
    // carrinho.adicionarItem(item2);

    // double valorTotal = carrinho.calcularValorTotal();

    // assertEquals(35.00, valorTotal, 0.01);
    // }

    // @Test
    // public void
    // testDeveCalcularValorDaCompraComDescontoQuandoValorBrutoForMaiorQue500() {
    // CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
    // Item item1 = new Item("Produto A", 100.00, 6);

    // carrinho.adicionarItem(item1);

    // double valorTotal = carrinho.calcularValorTotal();

    // assertEquals(540.00, valorTotal, 0.01);
    // }

    // Testes para novo formato de função calcularValorTotal() que retorna uma lista
    // com valor bruto e liquido
    @Test
    public void testDeveCalcularValorDaCompra() {
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
        Item item1 = new Item("Produto A", 10.00, 2);
        Item item2 = new Item("Produto B", 15.00, 1);

        carrinho.adicionarItem(item1);
        carrinho.adicionarItem(item2);

        double valorEsperado = 35;
        assertEquals(valorEsperado, carrinho.calcularValorTotal().get(0), 0.01);
        assertEquals(valorEsperado, carrinho.calcularValorTotal().get(1), 0.01);
    }

    @Test
    public void testDeveCalcularValorDaCompraComDescontoQuandoValorBrutoForMaiorQue500() {
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
        Item item1 = new Item("Produto A", 100.00, 6);
        Item item2 = new Item("Produto B", 100.00, 6);

        carrinho.adicionarItem(item1);
        carrinho.adicionarItem(item2);

        double valorBruto = 1200;
        double valorLiquido = 1080;
        assertEquals(valorBruto, carrinho.calcularValorTotal().get(0), 0.01);
        assertEquals(valorLiquido, carrinho.calcularValorTotal().get(1), 0.01);
    }

}
