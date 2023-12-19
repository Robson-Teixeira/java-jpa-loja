package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Cliente;

public class ClienteDao {

	private EntityManager entityManager;

	public ClienteDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void cadastrar(Cliente cliente) {
		this.entityManager.persist(cliente);
	}

	public void atualizar(Cliente cliente) {
		this.entityManager.merge(cliente);
	}

	public void remover(Cliente cliente) {
		cliente = this.entityManager.merge(cliente);
		this.entityManager.remove(cliente);
	}

}
