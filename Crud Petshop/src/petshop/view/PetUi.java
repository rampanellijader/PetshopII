
package petshop.view;


import java.util.InputMismatchException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import petshop.dominio.Cliente;
import petshop.dominio.Pet;
import petshop.negocio.ClienteNegocio;
import petshop.negocio.NegocioException;
import petshop.negocio.PetNegocio;
import petshop.util.Console;
import petshop.view.menu.ClienteMenu;
import petshop.view.menu.PetMenu;


public class PetUi {
   private PetNegocio petNegocio;
   private ClienteNegocio clienteNegocio;
   
        public PetUi() {
        petNegocio = new PetNegocio();
        clienteNegocio = new ClienteNegocio();
    }

    public void menuPet() throws NegocioException  {
        int opcao = -1;
        do {
            try {
                System.out.println(PetMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção:");
                switch (opcao) {
                    case PetMenu.OP_CADASTRAR:
                        cadastrarPet();
                        break;
                    case PetMenu.OP_DELETAR:
                        deletarPet();
                        break;
                    case PetMenu.OP_ATUALIZAR:
                        atualizarPet();
                        break;
                    case PetMenu.OP_LISTAR:
                        listarPets();
                        break;
                    case PetMenu.OP_CONSULTAR:
                        listarPorNome();
                        break;
                    case PetMenu.OP_SAIR:
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

    private void cadastrarPet() {
       
       String nome = Console.scanString("nome: ");
       String tp_animal = Console.scanString("tipo animal: ");
       String rg_cli = Console.scanString("Digite o rg do dono do pet: ");
       try{
        Cliente c = clienteNegocio.procurarPorRg(rg_cli);
        petNegocio.salvar(new Pet(nome, tp_animal, c));
       System.out.println("Pet " + nome + " cadastrado com sucesso!");
       
       }  catch (NegocioException ex){
         System.out.println("Cadstrar as informações de acordo com o item solicitado");
    }
    }

    private void deletarPet() throws NegocioException {
       String nome = Console.scanString("Nome do pet a ser deletado: ");
       Pet pet = petNegocio.procurarPorNome(nome);
        this.listarPet(pet);
    }

    private void atualizarPet()  {
       try {
           String nome = Console.scanString("Nome do pet a ser alterado: ");
           
           Pet pet = petNegocio.procurarPorNome(nome);
           this.listarPet(pet);
           
           System.out.println("Digite os dados do pet que quer alterar [Vazio caso nao queira]");
           String tp_animal = Console.scanString("Tipo animal: ");
           if (!nome.isEmpty()) {
               pet.setTp_animal(tp_animal);
           }
           
           petNegocio.atualizar(pet);
           System.out.println("Pet " + nome + " atualizado com sucesso!");
       } catch (NegocioException ex) {
           Logger.getLogger(PetUi.class.getName()).log(Level.SEVERE, null, ex);
       }
 
    }

    private void listarPets() {
        List<Pet> listarPets = petNegocio.listarPets();
        this.listarPets(listarPets);
       
    }

    private void listarPorNome() {
        String nome = Console.scanString("Nome: ");
        List<Pet> listarPet = petNegocio.listarPorNome(nome);
        this.listarPets(listarPet);
    }
     
    private void listarPet(Pet p){
        System.out.println("-----------------------------");
        System.out.println("Pet");
        System.out.println("Nome: " + p.getNome());
        System.out.println("Tipo animal: " + p.getTp_animal());
        System.out.println("Cliente: " + p.getCliente().getNome())  ;
        System.out.println("-----------------------------");
    }
    private void listarPets(List<Pet> listaPets) {
      if(listaPets.isEmpty())  {
          System.out.println("Pets não encontrados");
      }else {
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-20s", "|NOME") + "\t"
                    + String.format("%-20s", "|TIPO ANIMAL") + "\t"
                    + String.format("%-20s", "|CLIENTE"));
            for (Pet pet : listaPets) {
                System.out.println(String.format("%-20s", pet.getNome()) + "\t"
                        + String.format("%-20s", "|" + pet.getTp_animal())
                        + String.format("%-20s", "|" + pet.getCliente().getNome()));
            }
        }
    }
    }



