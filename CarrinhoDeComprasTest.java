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

    @Test
    public void testDeveCalcularValorDaCompra() {
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
        Item item1 = new Item("Produto A", 10.00, 2);
        Item item2 = new Item("Produto B", 15.00, 1);

        carrinho.adicionarItem(item1);
        carrinho.adicionarItem(item2);

        double valorTotal = carrinho.calcularValorTotal();

        assertEquals(35.00, valorTotal, 0.01);
    }

}
