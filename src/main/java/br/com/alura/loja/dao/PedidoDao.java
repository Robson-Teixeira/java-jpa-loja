package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.vo.RelatorioVendasVo;

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
	
	public List<RelatorioVendasVo> relatorioVendas() {
		String jpql = "SELECT new br.com.alura.loja.vo.RelatorioVendasVo(" +
				"produto.nome, SUM(itemPedido.quantidade) AS quantidadeTotal, MAX(pedido.data)) " + 
				"FROM Pedido pedido " +
				"JOIN pedido.itensPedidos itemPedido " +
				"JOIN itemPedido.produto produto " + 
				"GROUP BY produto.id " +
				"ORDER BY quantidadeTotal DESC";
		return this.entityManager
				.createQuery(jpql, RelatorioVendasVo.class)
				.getResultList();
	}

}
