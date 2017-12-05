package ec.edu.ups.app.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.app.model.CategoriaProveedor;

@Stateless
public class CategoriaProvDAO {
	@Inject
	private EntityManager em;
	
	public void insertar (CategoriaProveedor catproveedor){
		em.persist(catproveedor);
	}
	
	public void actualizar (CategoriaProveedor catproveedor){
		em.merge(catproveedor);
	}

	public void borrar (String codigo){
		em.remove(leer(codigo));
	}
	
	public CategoriaProveedor leer (String codigo){
		CategoriaProveedor p = em.find(CategoriaProveedor.class, codigo);
		return p;
	}
	
	public List<CategoriaProveedor> listadoCatProveedores(){
		String jpql = "SELECT p FROM CategoriaProveedor p";
		Query query = em.createQuery(jpql, CategoriaProveedor.class);
		List<CategoriaProveedor> listado = query.getResultList();
		return listado;
	} 
	
	public void guardar(CategoriaProveedor catproveedor){
		CategoriaProveedor p = leer(catproveedor.getCodigo());
		if (p==null)
			insertar(catproveedor);
		else
			actualizar(catproveedor);
	}


}
