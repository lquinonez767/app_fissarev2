package ec.edu.ups.app.data;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.edu.ups.app.model.Pedido;

@Stateless
public class PedidoDAO {
	
	@Inject
	private EntityManager em;
	
	public void insertar(Pedido pedido) {
		em.persist(pedido);
	}
	
	public void actualizar(Pedido pedido) {
		em.merge(pedido);
	}

	public void borrar(String codigo) {
		em.remove(leer(codigo));
	}

	public Pedido leer(String codigo) {
		em.find(Pedido.class, codigo);
		return null;
	}
	
}
