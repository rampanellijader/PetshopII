
package petshop.dominio;


public class Pet {
  private int id;  
  private String nome; 
  private String tp_animal;
  private Cliente cliente;

  
   public Pet(int id, String nome, String tp_animal, Cliente cliente){
      this.id = id;
      this.nome = nome;
      this.tp_animal = tp_animal;
      this.cliente =  cliente;
      
 }
   
  public Pet(String nome, String tp_animal, Cliente cliente){
      this.nome = nome;
      this.tp_animal = tp_animal;
      this.cliente =  cliente;
      
 }
    public String getNome() {
        return nome;
    }

   
    public String getTp_animal() {
        return tp_animal;
    }

 
    public Cliente getCliente() {
        return cliente;
    }
  
  @Override
    public String toString(){
    return nome +" "+ tp_animal+ " "+ cliente.getNome();
  }


   
    public int getId() {
        return id;
    }

  
    public void setId(int id) {
        this.id = id;
    }
}
