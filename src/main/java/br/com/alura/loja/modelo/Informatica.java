package br.com.alura.loja.modelo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "informatica")
public class Informatica extends Produto {

	private String marca;
	private String modelo;

	public Informatica() {
	}

	public Informatica(String nome, String descricao, BigDecimal preco, Categoria categoria, String autor,
			String modelo) {
		super(nome, descricao, preco, categoria);
		this.marca = autor;
		this.modelo = modelo;
	}

	public String getAutor() {
		return marca;
	}

	public void setAutor(String autor) {
		this.marca = autor;
	}

	public String getNumeroPaginas() {
		return modelo;
	}

	public void setNumeroPaginas(String modelo) {
		this.modelo = modelo;
	}

}
