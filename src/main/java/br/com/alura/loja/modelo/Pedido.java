package br.com.alura.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "valor_total")
	private BigDecimal valorTotal = BigDecimal.ZERO;
	private LocalDate data = LocalDate.now();
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	// Atributo do outro lado (ItemPedido) do relacionamento
	// Especifica relacionamento bidirecional e evita a criação de uma nova tabela de relacionamento
	private List<ItemPedido> itensPedidos = new ArrayList<ItemPedido>();

	public Pedido() {
	}

	public Pedido(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedido> getItensPedidos() {
		return itensPedidos;
	}

	public void adicionarItem(ItemPedido itemPedido) {
		itemPedido.setPedido(this);
		this.valorTotal = this.valorTotal.add(itemPedido.getValor());
		this.itensPedidos.add(itemPedido);
	}

}