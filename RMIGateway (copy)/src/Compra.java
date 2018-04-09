
import java.util.List;


/**
 *
 * @author guest-u1pwic
 */
public final class Compra {

    private static Long lastId = 1L;
    private Long id;
    private String nome;
    private List<CompraProduto> listaCompraProdutos;
    private Usuario usuario;

    public Compra() {
        athomicId();
    }

    @Override
    public String toString() {
        return "Compra{" + "id=" + id + ", nome=" + nome +  ", listaCompraProdutos=" + listaCompraProdutos + ", usuario=" + usuario + '}';
    }

    public synchronized void athomicId() {
        this.id = lastId;
        lastId++;
    }

    public List<CompraProduto> getListaProdutos() {
        return listaCompraProdutos;
    }

    public void setListaProdutos(List<CompraProduto> listaCompraProdutos) {
        this.listaCompraProdutos = listaCompraProdutos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
