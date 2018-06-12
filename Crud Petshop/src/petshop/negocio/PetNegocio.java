
package petshop.negocio;

import petshop.dao.impl_bd.PetDaoBD;
import petshop.dominio.Pet;


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
            throw new NegocioException("Campo nome nao informado");
        }
    }
 
 
    private boolean petExiste(String nome) {
        Pet pet = petDao.procurarPorNome(nome);
        return (pet != null);
    }
}
