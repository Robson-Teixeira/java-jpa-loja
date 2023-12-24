package br.com.alura.loja.teste;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.util.JPAUtil;

public class PerformanceConsultas {

	public static void main(String[] args) {

		CadastroPedido.main(args);

		EntityManager entityManager = JPAUtil.getEntityManager();

		PedidoDao pedidoDao = new PedidoDao(entityManager);

		Pedido pedido = pedidoDao.buscarPedidoCliente(1l);

		// Relacionamentos ToOne são carregados por padrão na JPA (Eager)
		System.out.println("Data pedido: " + pedido.getData());

		// Relacionamentos ToMany são consultados/carregados apenas quando acessados (Lazy)
		System.out.println("Quantidade de itens: " + pedido.getItensPedidos().size());

		entityManager.close();

		// Executa normalmente após fechamento do EntityManager
		System.out.println("Data pedido: " + pedido.getData());

		// LazyInitializationException, pois Cliente (agora definido como Lazy) não foi carregado
		// antes do fechamento do EntityManager. A solução é definir a query e usar JOIN FETCH
		System.out.println("Cliente: " + pedido.getCliente().getDadosPessoais().getNome());

	}

}
