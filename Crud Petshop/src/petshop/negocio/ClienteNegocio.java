
package petshop.negocio;

import petshop.dao.impl_bd.ClienteDaoBD;
import petshop.dominio.Cliente;


public class ClienteNegocio {

  
    
    private ClienteDaoBD clienteDao;

    public ClienteNegocio() {
        clienteDao = new ClienteDaoBD();
    
}
    
        public void salvar(Cliente c) throws NegocioException {
        this.validarCamposObrigatorios(c);
        this.validarRgExistente(c);
        clienteDao.salvar(c);
    }

   private void validarCamposObrigatorios(Cliente c) throws NegocioException {
        if (c.getRg() == null || c.getRg().isEmpty()) {
            throw new NegocioException("Campo RG nao informado");
    }
         if (c.getNome() == null || c.getNome().isEmpty()) {
            throw new NegocioException("Campo nome nao informado");
        }
    }
   
   private void validarRgExistente(Cliente c) throws NegocioException{
         if (clienteExiste(c.getRg())) {
            throw new NegocioException("RG ja existente");
    }
        
}
    private boolean clienteExiste(String rg) {
        Cliente cliente = clienteDao.procurarPorRg(rg);
        return (cliente != null);
    }
    
      public Cliente  procurarPorRg(String rg) throws NegocioException {
       if (rg == null || rg.isEmpty()) {
            throw new NegocioException("Campo RG nao informado");
    }  Cliente cliente = clienteDao.procurarPorRg(rg);
       if(rg == null){
           throw new NegocioException("Cliente não encontrado!");
       }
        return (cliente);
      }
}