import java.util.ArrayList;

import controller.CafeteriaController;
import model.Cliente;

public class App {
    public static void main(String[] args) throws Exception {
        CafeteriaController cafeteria = new CafeteriaController(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        /*cafeteria.buscarPedido(0);
        cafeteria.buscarFuncionario(0);
        cafeteria.buscarFuncionario(0);*/
        //cafeteria.alterarProduto(0, null, 0, 0);
        cafeteria.excluirPedido(0);
    }
}
