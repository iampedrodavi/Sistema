package Programa;

public class User {
	private int iduser;
	private String nomeuser;
	private String senhauser;
	private String tipo;
	
	public User() {
        this.iduser = 0;
        this.nomeuser = "";
        this.senhauser = "";
        this.tipo = "";
        
    }
	public User(int iduser, String nomeuser, String senhauser, String tipo) {
		this.iduser = iduser ;
		this.nomeuser = nomeuser;
		this.senhauser = senhauser;
		this.tipo = tipo;
	}
	public int getIduser() {
		return iduser;
	}
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
	public String getNomeuser() {
		return nomeuser;
	}
	public void setNomeuser(String nomeuser) {
		this.nomeuser = nomeuser;
	}
	public String getSenhauser() {
		return senhauser;
	}
	public void setSenhauser(String senhauser) {
		this.senhauser = senhauser;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "User [iduser=" + iduser + ", nomeuser=" + nomeuser + ", senhauser=" + senhauser + ", tipo=" + tipo
				+ "]";
	}
	
}
