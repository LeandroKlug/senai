package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Roupas {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	public String titulo;
	public String dataHoraCadastro;
	public String tipo;
	public String tamanho;
	public String categoria;
	public String classifiPublico;
	public String estadoConvervacao;
	public String descricao;
	
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
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDataHoraCadastro() {
		return dataHoraCadastro;
	}
	public void setDataHoraCadastro(String dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getClassifiPublico() {
		return classifiPublico;
	}
	public void setClassifiPublico(String classifiPublico) {
		this.classifiPublico = classifiPublico;
	}
	public String getEstadoConvervacao() {
		return estadoConvervacao;
	}
	public void setEstadoConvervacao(String estadoConvervacao) {
		this.estadoConvervacao = estadoConvervacao;
	}
	public String getdescricao() {
		return descricao;
	}
	public void setdescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}

/*(A) Título
(B) Data/hora de cadastro
(C) Tipo (Camiseta, calça, shorts etc)
(D) Tamanho (PP, M, G, GG, XG, XGG)
(E) Categoria (Infantil, Adulto etc)
(F) Classificação do público (Masculino, Feminino ou Unissex)
(G) Estado de conservação (Novo, Bom, Regular e Ruim)
(H) Campo para descritivo*/