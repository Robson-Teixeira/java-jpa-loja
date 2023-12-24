package br.com.alura.loja.teste;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.CategoriaId;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.loja.vo.RelatorioVendasVo;

public class CadastroPedido {

	public static void main(String[] args) {

		CadastroProduto.cadastrarProduto();

		Cliente cliente = new Cliente("Robson Teixeira", "079.371.220-30");

		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, CadastroProduto.buscarProdutoPorId(1l)));
		pedido.adicionarItem(new ItemPedido(2, pedido, CadastroProduto.buscarProdutoPorId(2l)));
		pedido.adicionarItem(new ItemPedido(1, pedido, CadastroProduto.buscarProdutoPorId(3l)));

		EntityManager entityManager = JPAUtil.getEntityManager();

		ClienteDao clienteDao = new ClienteDao(entityManager);
		PedidoDao pedidoDao = new PedidoDao(entityManager);

		entityManager.getTransaction().begin();
		clienteDao.cadastrar(cliente);
		pedidoDao.cadastrar(pedido);
		entityManager.getTransaction().commit();

		System.out.println("Total vendas: " + pedidoDao.valorTotalVendido());

		List<RelatorioVendasVo> relatorioVendas = pedidoDao.relatorioVendas();
		relatorioVendas.forEach(System.out::println);
		
		System.out.println(entityManager.find(
				Categoria.class, new CategoriaId("CELULARES", "Tipo")));

		entityManager.close();

	}

}
