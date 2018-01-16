package ec.edu.ups.app.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.app.model.CategoriaServicio;



@Stateless
public class CategoriaSrvDAO {
	@Inject
	private EntityManager em;
	
	public void insertar (CategoriaServicio catservicio){
		em.persist(catservicio);
	}
	
	public void actualizar (CategoriaServicio catservicio){
		em.merge(catservicio);
	}

	public void borrar (String codigo){
		em.remove(leer(codigo));
	}
	
	public CategoriaServicio leer (String i){
		CategoriaServicio p = em.find(CategoriaServicio.class, i);
		return p;
	}
	
	public List<CategoriaServicio> listadoCatServicios(){
		String jpql = "SELECT p FROM CategoriaServicio p";
		Query query = em.createQuery(jpql, CategoriaServicio.class);
		List<CategoriaServicio> listado = query.getResultList();
		return listado;
	} 
	
	public void guardar(CategoriaServicio catservicio){
		CategoriaServicio p = leer(catservicio.getCodigo());
		if (p==null)
			insertar(catservicio);
		else
			actualizar(catservicio);
	}

}
