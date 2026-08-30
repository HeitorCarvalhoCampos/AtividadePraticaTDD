import java.util.ArrayList;
import java.util.List;

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

    // Função específica para retornar valor bruto ou liquido, caso o valor bruto
    // seja maior que 500, retorna o valor liquido com desconto de 10%
    // public double calcularValorTotal() {
    // double valorBruto = itens.stream().mapToDouble(item -> item.getPreco() *
    // item.getQuantidade()).sum();

    // if (valorBruto > 500) {
    // return valorBruto * 0.9;
    // }

    // return valorBruto;
    // }

    // Função específica para retornar valor total, bruto e liquido
    public List<Double> calcularValorTotal() {
        double valorBruto = itens.stream().mapToDouble(item -> item.getPreco() * item.getQuantidade()).sum();

        if (valorBruto > 500) {
            double valorLiquido = valorBruto * 0.9; // 10% de desconto
            return List.of(valorBruto, valorLiquido);
        }

        return List.of(valorBruto, valorBruto);
    }
}
