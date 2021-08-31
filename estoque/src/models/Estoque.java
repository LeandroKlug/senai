package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Estoque {
	
	//Id, nomeItem, quantidade
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String nomeItem;
	private String quantidade;
	
	@OneToOne
	private StatusDoItem status;
	
	public StatusDoItem getstatus() {
		return status;
	}

	public void setstatus(StatusDoItem status) {
		this.status = status;
	}

	@OneToOne
	private Pessoa pessoa;
	
	public Pessoa getpessoa() {
		return pessoa;
	}

	public void setpessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNomeItem() {
		return nomeItem;
	}
	public void setNomeItem(String nomeItem) {
		this.nomeItem = nomeItem;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	
	

}
