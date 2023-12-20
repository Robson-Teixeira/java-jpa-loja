package br.com.alura.loja.dao;

import java.math.BigDecimal;

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

	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return this.entityManager
				.createQuery(jpql, BigDecimal.class)
				.getSingleResult();
	}

}
