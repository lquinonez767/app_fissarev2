package ec.edu.ups.app.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.app.model.Proveedor;

@Stateless
public class ProveedorDAO {
	@Inject
	private EntityManager em;
	
	public void insertar (Proveedor proveedor){
		em.persist(proveedor);
	}
	
	public void actualizar (Proveedor proveedor){
		em.merge(proveedor);
	}

	public void borrar (String cedula){
		em.remove(leer(cedula));
	}
	
	public Proveedor leer (String cedula){
		Proveedor p = em.find(Proveedor.class, cedula);
		return p;
	}
	
	public List<Proveedor> listadoProveedores(){
		String jpql = "SELECT p FROM Proveedor p";
		Query query = em.createQuery(jpql, Proveedor.class);
		List<Proveedor> listado = query.getResultList();
		return listado;
	} 
	
	public void guardar(Proveedor proveedor){
		Proveedor p = leer(proveedor.getCedula());
		if (p==null)
			insertar(proveedor);
		else
			actualizar(proveedor);
	}

}
