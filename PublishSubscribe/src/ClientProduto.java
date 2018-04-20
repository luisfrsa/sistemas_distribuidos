import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ClientProduto {

    private APIGatewayServiceInterface apiGateway;

    public static void main(String[] args) {
        try {
            APIGatewayServiceInterface apiGateway = (APIGatewayServiceInterface) Naming.lookup("//127.0.0.1:1099/APIGateway");
            ClientProduto self = new ClientProduto(apiGateway);

            System.out.println("\n--------Criando produtos--------");
            self.createUsers();

            System.out.println("\n--------Get produtos--------");
            self.getUserById(1L);
            self.getUserById(2L);
            self.getUserById(3L);

            System.out.println("\n--------Get All produtos--------");
            self.getAllUsers();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ClientProduto(APIGatewayServiceInterface apiGateway) {
        this.apiGateway = apiGateway;
    }

    private void createUsers() {
        List<Produto> listProdutos = new ArrayList<>();
        listProdutos.add(new Produto(1L, "celular",1200.00));
        listProdutos.add(new Produto(2L, "computador",3500.00));
        listProdutos.add(new Produto(3L, "mouse e teclado",150.00));
        listProdutos.forEach(produto -> {
            try {
                System.out.println("ClientProduto.java - Adicionando produto " + produto.toString());
                apiGateway.addProduto(produto);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }


    private void getUserById(Long id) throws RemoteException {
        System.out.println("ClientProduto.java - Buscando produto pelo id " + id);
        Produto produto = apiGateway.getProdutoById(id);
        System.out.println("ClientProduto.java - Produto encontrado pelo pelo id " + produto.toString());
    }

    private void getAllUsers() throws RemoteException {
        List<Produto> listProdutos = apiGateway.getAllProdutos();
        listProdutos.forEach(produto -> {
            System.out.println("ClientProduto.java - Printando todos os produtos " + produto.toString());
        });

    }
}
