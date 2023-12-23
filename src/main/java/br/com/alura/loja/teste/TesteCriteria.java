package br.com.alura.loja.teste;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class TesteCriteria {

	public static void main(String[] args) {

		CadastroProduto.cadastrarProduto();

		EntityManager entityManager = JPAUtil.getEntityManager();

		ProdutoDao produtoDao = new ProdutoDao(entityManager);

		List<Produto> produtos = produtoDao.buscarComParametros("Console PlayStation® 5", null, null);

		produtos.forEach(produto -> CadastroProduto.imprimirProduto(produto));

		produtos = produtoDao.buscarComParametrosCriteria(null, new BigDecimal(800), LocalDate.now());

		produtos.forEach(produto -> CadastroProduto.imprimirProduto(produto));

	}

}
