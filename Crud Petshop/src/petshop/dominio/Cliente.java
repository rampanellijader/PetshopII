
package petshop.dominio;

import java.util.Objects;


public class Cliente {
    
    private int id;
    private String rg;
    private String nome;
    private String telefone;

   public Cliente(String rg, String nome,  String telefone){
    this.rg = rg;
    this.nome = nome;
    this.telefone = telefone;
   }
   
    public Cliente(int id,String rg, String nome,  String telefone){
    this.id = id;
    this.rg = rg;
    this.nome = nome;
    this.telefone = telefone;
    
       
   }
    public String getRg() {
        return rg;
    }
     public void setId(int id) {
        this.id = id;
    }

    
    public String getNome() {
        return nome;
    }

    
    public String getTelefone() {
        return telefone;
    }
    
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.rg, other.rg)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 10;
        hash = 59 * hash + Objects.hashCode(this.rg);
        return hash;
    }

    
    public String toString(){
        return rg + "-" + nome +"-" + telefone;
        
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone =  telefone;
    }
    
}
