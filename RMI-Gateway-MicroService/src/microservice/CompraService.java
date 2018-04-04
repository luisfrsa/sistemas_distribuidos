
import java.util.ArrayList;
import java.util.List;

public class CompraService  extends java.rmi.server.UnicastRemoteObject {

    private ArrayList<Compra> listaCompras;

    public CompraService() throws java.rmi.RemoteException {
    }

    private void novaCompra(List<CompraProduto> listaCompras, Usuario usuario) {
        Compra compra = new Compra();
        compra.setNome("Compra com id " + compra.getId());
        compra.setListaProdutos(listaCompras);
        compra.setUsuario(usuario);
    }
}
