package br.com.alura.loja.modelo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {

	@EmbeddedId
	private CategoriaId categoriaId;

	public Categoria() {
	}

	public Categoria(String nome) {
		this.categoriaId = new CategoriaId(nome, "Tipo");
	}

	public String getNome() {
		return this.categoriaId.getNome();
	}

	public void setNome(String nome) {
		this.categoriaId.setNome(nome);
	}

	@Override
	public String toString() {
		return "Categoria: " + this.getNome();
	}
	
}
