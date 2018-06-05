
package petshop.view;

import dao.ClienteDao;
import java.util.InputMismatchException;
import java.util.List;
import petshop.dao.impl_bd.ClienteDaoBD;
import petshop.dominio.Cliente;
import petshop.util.Console;
import petshop.view.menu.ClienteMenu;


public class ClienteUi {
    
    private ClienteDao clienteDao;
    
        public ClienteUi() {
        clienteDao = new ClienteDaoBD();
    }

    public void menu() {
        int opcao = -1;
        do {
            try {
                System.out.println(ClienteMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção:");
                switch (opcao) {
                    case ClienteMenu.OP_CADASTRAR:
                        cadastrarCliente();
                        break;
                    case ClienteMenu.OP_DELETAR:
                        deletarCliente();
                        break;
                    case ClienteMenu.OP_ATUALIZAR:
                        atualizarCliente();
                        break;
                    case ClienteMenu.OP_LISTAR:
                        mostrarClientes();
                        break;
                    case ClienteMenu.OP_CONSULTAR:
                        consultarClientesPorNome();
                        break;
                    case ClienteMenu.OP_SAIR:
                        System.out.println("Finalizando a aplicacao..");
                        break;
                    default:
                        System.out.println("Opção inválida..");
                }
            } catch (InputMismatchException ex) {
                UIUtil.mostrarErro("Somente numeros sao permitidos!");
            }

        } while (opcao != ClienteMenu.OP_SAIR);
    } 
      private void cadastrarCliente() {
        String rg = Console.scanString("RG: ");
        String nome = Console.scanString("Nome: ");
        String telefone = Console.scanString("Telefone: ");
        clienteDao.salvar(new Cliente(rg, nome, telefone));
        System.out.println("Cliente " + nome + " cadastrado com sucesso!");
    }

    public void mostrarClientes() {
        List<Cliente> listaClientes = clienteDao.listar();
        this.mostrarClientes(listaClientes);
    }

    private void deletarCliente() {
        String rg = Console.scanString("RG do cliente a ser deletado: ");
        Cliente cli = clienteDao.procurarPorRg(rg);
        this.mostrarCliente(cli);
        if (UIUtil.getConfirmacao("Realmente deseja excluir esse cliente?")) {
            clienteDao.deletar(cli);
            System.out.println("Cliente deletado com sucesso!");
        } else {
            System.out.println("Operacao cancelada!");
        }
    }

    private void atualizarCliente() {
        String rg = Console.scanString("RG do cliente a ser alterado: ");

        Cliente cli = clienteDao.procurarPorRg(rg);
        this.mostrarCliente(cli);

        System.out.println("Digite os dados do cliente que quer alterar [Vazio caso nao queira]");
        String nome = Console.scanString("Nome: ");
        String telefone = Console.scanString("Telefone:");
        if (!nome.isEmpty()) {
           cli.setNome(nome);        
        }
        if (!telefone.isEmpty()){
           cli.setTelefone(telefone);        }

        clienteDao.atualizar(cli);
        System.out.println("Cliente " + nome + " atualizado com sucesso!");

    }

    private void consultarClientesPorNome() {
        String nome = Console.scanString("Nome: ");
        List<Cliente> listaCli = clienteDao.listarPorNome(nome);
        this.mostrarClientes(listaCli);

    }

    private void mostrarCliente(Cliente c) {
        System.out.println("-----------------------------");
        System.out.println("Cliente");
        System.out.println("RG: " + c.getRg());
        System.out.println("Nome: " + c.getNome());
        System.out.println("Telefone: " + c.getTelefone());
        System.out.println("-----------------------------");
    }

    private void mostrarClientes(List<Cliente> listaClientes) {
        if (listaClientes.isEmpty()) {
            System.out.println("Clientes nao encontrados!");
        } else {
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "RG") + "\t"
                    + String.format("%-20s", "|NOME") + "\t"
                    + String.format("%-20s", "|TELEFONE"));
            for (Cliente cliente : listaClientes) {
                System.out.println(String.format("%-10s", cliente.getRg()) + "\t"
                        + String.format("%-20s", "|" + cliente.getNome()) + "\t"
                        + String.format("%-20s", "|" + cliente.getTelefone()));
            }
        }
    }
}
