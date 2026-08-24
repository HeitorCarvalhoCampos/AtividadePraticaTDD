public class CarrinhoDeCompras {

    private List<Item> itens;

    public CarrinhoDeCompras() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(Item item) {
        if (item.getPreco() <= 0) {
            throw new IllegalArgumentException("O preço do item tem que ser positivo.");
        }
        if (item.getQuantidade() <= 0) {
            throw new IllegalArgumentException("A quantidade do item tem que ser positiva.");
        }

        this.itens.add(item);
    }

    public void removerItem(Item item) {
        if (!this.itens.contains(item)) {
            throw new IllegalArgumentException("O item não existe no carrinho.");
        }
        this.itens.remove(item);
    }

    public List<Item> getItens() {
        return itens;
    }

    public double calcularValorTotal() {
        return itens.stream().mapToDouble(item -> item.getPreco() * item.getQuantidade()).sum();
    }
}
