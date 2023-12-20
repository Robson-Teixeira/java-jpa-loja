package br.com.alura.loja.teste;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.util.JPAUtil;

public class CadastroPedido {

	public static void main(String[] args) {

		CadastroProduto.cadastrarProduto();

		Cliente cliente = new Cliente("Robson Teixeira", "079.371.220-30");

		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, CadastroProduto.buscarProdutoPorId()));

		EntityManager entityManager = JPAUtil.getEntityManager();

		ClienteDao clienteDao = new ClienteDao(entityManager);
		PedidoDao pedidoDao = new PedidoDao(entityManager);

		entityManager.getTransaction().begin();
		clienteDao.cadastrar(cliente);
		pedidoDao.cadastrar(pedido);
		entityManager.getTransaction().commit();

		System.out.println("Total vendas: " + pedidoDao.valorTotalVendido());

		entityManager.close();		

	}

}
