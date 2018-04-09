

/**
 * @author guest-u1pwic
 */
public class CompraProduto {
    private Integer quantidade;
    private Produto produto;

    public CompraProduto(Integer quantidade, Produto produto) {
        this.quantidade = quantidade;
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "CompraProduto{" + "quantidade=" + quantidade + ", produto=" + produto + '}';
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }


}
