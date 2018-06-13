
package petshop.negocio;

import dao.ClienteDao;
import dao.PetDao;
import java.util.List;
import petshop.dao.impl_bd.ClienteDaoBD;
import petshop.dao.impl_bd.PetDaoBD;
import petshop.dominio.Pet;
import petshop.util.Console;


public class PetNegocio {
    
      private PetDaoBD petDao;

    public PetNegocio() {
        petDao = new PetDaoBD();
    
}
    
        public void salvar(Pet p) throws NegocioException {
        this.validarCamposObrigatorios(p);
        petDao.salvar(p);
    }

   private void validarCamposObrigatorios(Pet p) throws NegocioException {
         if (p.getNome() == null || p.getNome().isEmpty()) {
            throw new NegocioException("Campo nome não informado");
        }
    }
 
 
    private boolean petExiste(String nome) {
        Pet pet = petDao.procurarPorNome(nome);
        return (pet != null);
    }

    public List<Pet> listarPorNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Pet> listar() {
         return(petDao.listar()) ;
    }

    public void atualizar(Pet p) throws NegocioException {
         if (p == null || p.getNome()== null) {
            throw new NegocioException("Sala nao existe!");
        }
        this.validarCamposObrigatorios(p);
         petDao.atualizar(p);
    }

    public Pet procurarPorNome(String nome) throws NegocioException {
          if (nome == null || nome.isEmpty()) {
            throw new NegocioException("Campo Nome nao informado");
    }  Pet pet = petDao.procurarPorNome(nome);
       if(nome == null){
           throw new NegocioException("Cliente não encontrado!");
       }
        return (pet);
      }
    }

