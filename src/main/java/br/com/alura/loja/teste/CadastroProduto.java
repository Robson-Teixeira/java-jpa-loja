package br.com.alura.loja.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroProduto {

	public static void main(String[] args) {

		cadastrarProduto();
		
		buscarProdutoPorId(1l);
		
		buscarProdutos();
		
		buscarPorNome();
		
		buscarPorNomeCategoria();
		
		buscarPrecoPorNome();

	}

	public static void cadastrarProduto() {
		
		Categoria celulares = new Categoria("CELULARES");
		Categoria gamesConsoles = new Categoria("GAMES & CONSOLES");
		Categoria eletronicos = new Categoria("ELETRÔNICOS");

		Produto celular = new Produto("Xiaomi Redmi 10",
				"Smartphone Android de 6.5 polegadas e de 2400x1080 pixels. "
						+ "Conectividade lte 4G, Wi-Fi e gps, leitor multimídia, rádio, "
						+ "videoconferência e Bluetooth. Memória interna de 128 gb, "
						+ "câmera traseira de 50 megapixels. Com apenas 8.9 milímetros de espessura.",
						new BigDecimal("800"), celulares);
		
		Produto ps5 = new Produto("Console PlayStation® 5",
				"SSD ultrarrápido, E/S integrada, \"Ray Tracing\" (Rastreamento de raios)"
				+ "Jogos para TVs 4K, Até 120 qps com saída em 120 Hz, Tecnologia HDR"
				+ "Saída em 8K, Tempest 3D AudioTech, Resposta tátil, Gatilhos adaptáveis",
				new BigDecimal("4000"), gamesConsoles);
		
		Produto razerBlade = new Produto("Razer Blade 16",
				"Intel 13ª geração i9-13950HX RTX 4090 tela 16' UHD SSD 2Tb NVMe RAM 32Gb",
				new BigDecimal("42000"), eletronicos);

		EntityManager entityManager = JPAUtil.getEntityManager();

		CategoriaDao categoriaDao = new CategoriaDao(entityManager);
		ProdutoDao produtoDao = new ProdutoDao(entityManager);

		entityManager.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(gamesConsoles);
		categoriaDao.cadastrar(eletronicos);
		
		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(ps5);
		produtoDao.cadastrar(razerBlade);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}

	private static void buscarProdutos() {

		EntityManager entityManager = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(entityManager);

		System.out.println("buscarTodos");

		List<Produto> produtos = produtoDao.buscarTodos();

		produtos.forEach(produto -> imprimirProduto(produto));

	}

	public static Produto buscarProdutoPorId(Long id) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(entityManager);

		System.out.println("buscarPorId");

		Produto produto = produtoDao.buscarPorId(id);

		imprimirProduto(produto);
		
		return produto;

	}

	private static void buscarPorNome() {

		EntityManager entityManager = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(entityManager);

		System.out.println("buscarPorNome");

		List<Produto> produtos = produtoDao.buscarPorNome("Xiaomi Redmi 10");

		produtos.forEach(produto -> imprimirProduto(produto));

	}

	private static void buscarPorNomeCategoria() {

		EntityManager entityManager = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(entityManager);

		System.out.println("buscarPorNomeCategoria");

		List<Produto> produtos = produtoDao.buscarPorNomeCategoria("CELULARES");

		produtos.forEach(produto -> imprimirProduto(produto));

	}
	
	private static void buscarPrecoPorNome() {

		EntityManager entityManager = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(entityManager);

		System.out.println("buscarPrecoPorNome");

		System.out.println(produtoDao.buscarPrecoPorNome("Xiaomi Redmi 10"));

	}

	private static void imprimirProduto(Produto produto) {

		System.out.println("Id: " + produto.getId() + " | Nome: " + produto.getNome() + " | Descrição: "
				+ produto.getDescricao() + " | Preço: " + produto.getPreco() + " | Data Cadastro: "
				+ produto.getDataCadastro() + " | Categoria: " + produto.getCategoria().getNome());

	}

}
