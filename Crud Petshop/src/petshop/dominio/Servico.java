
package petshop.dominio;


public class Servico {
  private static int cd_servico = 1;
  private int nr_servico;
  private String nm_servico;
  private String tp_atendimento;
  private double preco;

  
   public Servico(String nm_servico, String tp_atendimento, double preco  ) {
       this.nr_servico = generateCodigo();
       this.nm_servico = nm_servico;
       this.tp_atendimento = tp_atendimento;
       this.preco = preco;
   }
    public int getNr_servico() {
        return nr_servico;
    }

    public String getNm_servico() {
        return nm_servico;
    }

  
    public String getTp_atendimento() {
        return tp_atendimento;
    }

 
    public double getPreco() {
        return preco;
    }
    
    public String toString(){
      return nr_servico +" - "+ nm_servico +" - " +tp_atendimento+ " R$ "+preco;   
    }
  
      public int generateCodigo() {
        return (cd_servico++);
    }

   

    
}
