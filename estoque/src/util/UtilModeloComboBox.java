package util;

public class UtilModeloComboBox {
	
	public UtilModeloComboBox(String nome, long id) {
		this.setId(id);
		this.setNome(nome);
	}
	
	private String nome;
	private long id;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	// impressão -> construir uma linha do nosso combobox
	public String toString() {
		//return this.nome;
		//return this.id+" - "+this.nome;
		return this.nome+" ("+this.id+")";
	}
}