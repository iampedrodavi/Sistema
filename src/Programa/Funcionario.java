package Programa;


public class Funcionario {
	 private int idFun;
     private String nomeFun;
     private int idadeFun;
     private String cpfFun;
     private String emailFun;
     private String cargoFun;
     private int salarioFun;
 
     public Funcionario() {
         this.idFun = 0;
         this.nomeFun = "";
         this.idadeFun = 0;
         this.cpfFun = "";
         this.emailFun = "Desconhecido";
         this.cargoFun = "Desconhecido";
     }

     public Funcionario(int idFun, String nomeFun, int idadeFun, String cpfFun, String emailFun, String cargoFun, int salarioFun) {
         this.idFun = idFun;
         this.nomeFun = nomeFun;
         this.idadeFun = idadeFun;
         this.cpfFun = cpfFun;
         this.emailFun = emailFun;
         this.cargoFun = cargoFun;
         this.salarioFun = salarioFun;
     }

     public int getIdFun() {
         return idFun;
     }
 
     public void setIdFun(int idFun) {
         this.idFun = idFun;
     }
 
     public String getNomeFun() {
         return nomeFun;
     }
 
     public void setNomeFun(String nomeFun) {
         this.nomeFun = nomeFun;
     }
 
     public int getIdadeFun() {
         return idadeFun;
     }
 
     public void setIdadeFun(int idadeFun) {
         this.idadeFun = idadeFun;
     }
 
     public String getCpfFun() {
         return cpfFun;
     }
 
     public void setCpfFun(String cpfFun) {
         this.cpfFun = cpfFun;
     }
 
     public String getEmailFun() {
         return emailFun;
     }
 
     public void setEmailFun(String emailFun) {
         if (emailFun == null || emailFun.isEmpty()) {
             this.emailFun = "Desconhecido";
         } else {
             this.emailFun = emailFun;
         }
     }
 
     public String getCargoFun() {
         return cargoFun;
     }
 
     public void setCargoFun(String cargoFun) {
         if (cargoFun == null || cargoFun.isEmpty()) {
             this.cargoFun = "Desconhecido";
         } else {
             this.cargoFun = cargoFun;
         }
     }
     public int getSalarioFun() {
         return salarioFun;
     }
 
     public void setSalarioFun(int salarioFun) {
         this.salarioFun = salarioFun;
     }
 
     @Override
     public String toString() {
         return "Funcionario{" +
                 "Id =" + idFun +
                 ", Nome ='" + nomeFun + '\'' +
                 ", Idade =" + idadeFun +
                 ", CPF ='" + cpfFun + '\'' +
                 ", E-mail ='" + emailFun + '\'' +
                 ", Cargo ='" + cargoFun + '\'' +
                 ", Salário =" + salarioFun+
                 '}';
     }
}
