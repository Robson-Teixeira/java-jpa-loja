package br.com.alura.loja.teste;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.util.JPAUtil;

public class PerformanceConsultas {

	public static void main(String[] args) {

		CadastroPedido.main(args);

		EntityManager entityManager = JPAUtil.getEntityManager();

		Pedido pedido = entityManager.find(Pedido.class, 1l);

		// Relacionamentos ToOne são carregados por padrão na JPA (Eager)
		System.out.println("Data pedido: " + pedido.getData());

		// Relacionamentos ToMany são consultados/carregados apenas quando acessados (Lazy)
		System.out.println("Quantidade de itens: " + pedido.getItensPedidos().size());

	}

}
