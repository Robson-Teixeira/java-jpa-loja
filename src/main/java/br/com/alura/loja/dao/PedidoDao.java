package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Pedido;

public class PedidoDao {

	private EntityManager entityManager;

	public PedidoDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void cadastrar(Pedido pedido) {
		this.entityManager.persist(pedido);
	}

	public void atualizar(Pedido pedido) {
		this.entityManager.merge(pedido);
	}

	public void remover(Pedido pedido) {
		pedido = this.entityManager.merge(pedido);
		this.entityManager.remove(pedido);
	}

}
