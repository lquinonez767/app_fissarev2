package ec.edu.ups.app.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import ec.edu.ups.app.data.PedidoDAO;
import ec.edu.ups.app.model.Pedido;

@ManagedBean
public class PedidoControlador {
	
	private Pedido pedido;
	
	@Inject
	private PedidoDAO pedidodao;
	
	@PostConstruct
	public void init() {
		pedido = new Pedido();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public String guardar() {
		System.out.println(pedido);
		pedidodao.insertar(pedido);
		return null;
	}
}
