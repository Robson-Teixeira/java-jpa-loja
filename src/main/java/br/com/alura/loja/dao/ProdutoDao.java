package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.alura.loja.modelo.Produto;

public class ProdutoDao {

	private EntityManager entityManager;

	public ProdutoDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void cadastrar(Produto produto) {
		this.entityManager.persist(produto);
	}

	public void atualizar(Produto produto) {
		this.entityManager.merge(produto);
	}

	public void remover(Produto produto) {
		produto = this.entityManager.merge(produto);
		this.entityManager.remove(produto);
	}

	public Produto buscarPorId(Long id) {
		return this.entityManager.find(Produto.class, id);
	}

	public List<Produto> buscarTodos() {
		return this.entityManager
				.createNamedQuery("Produto.buscarTodos", Produto.class)
				.getResultList();
	}
	
	public List<Produto> buscarPorNome(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome"; 
		// Ou inserir ? no lugar de :nome e em setParameter definir índice x valor 
		return this.entityManager
				.createQuery(jpql, Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	}
	
	public List<Produto> buscarPorNomeCategoria(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome"; 
		return this.entityManager
				.createQuery(jpql, Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	}

	public BigDecimal buscarPrecoPorNome(String nome) {
		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
		return this.entityManager
				.createQuery(jpql, BigDecimal.class)
				.setParameter("nome", nome)
				.getSingleResult();
	}
	
	public List<Produto> buscarComParametros(String nome, BigDecimal preco, LocalDate dataCadastro) {
		String jpql = "SELECT p FROM Produto p WHERE 1 = 1 ";

		if (nome != null && !nome.trim().isEmpty())
			jpql += "AND p.nome = :nome ";

		if (preco != null)
			jpql += "AND p.preco = :preco ";

		if (dataCadastro != null)
			jpql += "AND p.dataCadastro = :dataCadastro";

		TypedQuery<Produto> typedQuery = this.entityManager.createQuery(jpql, Produto.class);

		if (nome != null && !nome.trim().isEmpty())
			typedQuery.setParameter("nome", nome);

		if (preco != null)
			typedQuery.setParameter("preco", preco);

		if (dataCadastro != null)
			typedQuery.setParameter("dataCadastro", dataCadastro);

		return typedQuery.getResultList();
	}

	public List<Produto> buscarComParametrosCriteria(String nome, BigDecimal preco, LocalDate dataCadastro) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
		Root<Produto> from = criteriaQuery.from(Produto.class);
		Predicate and = criteriaBuilder.and();

		if (nome != null && !nome.trim().isEmpty())
			and = criteriaBuilder.and(and, criteriaBuilder.equal(from.get("nome"), nome));

		if (preco != null)
			and = criteriaBuilder.and(and, criteriaBuilder.equal(from.get("preco"), preco));

		if (dataCadastro != null)
			and = criteriaBuilder.and(and, criteriaBuilder.equal(from.get("dataCadastro"), dataCadastro));

		criteriaQuery.where(and);

		return this.entityManager.createQuery(criteriaQuery).getResultList();
	}

}
