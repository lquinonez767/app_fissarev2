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

	public void borrar (int codigo){
		em.remove(leer(codigo));
	}
	
	public CategoriaServicio leer (int codigo){
		CategoriaServicio p = em.find(CategoriaServicio.class, codigo);
		return p;
	}
	
	public List<CategoriaServicio> listadoCatServicios(){
		System.out.println("Metodo listadoCatServicios() de CategoriaSrvDAO oooooooooooooooooo");
		String jpql = "SELECT p FROM CategoriaServicio p";
		Query query = em.createQuery(jpql, CategoriaServicio.class);
		List<CategoriaServicio> listado = query.getResultList();
		return listado;
	} 
	
	public void guardar(CategoriaServicio catservicio){
		System.out.println("Metodo guardar() de CategoriaSrvDAO oooooooooooooooooo");
		CategoriaServicio p = leer(catservicio.getCodigo());
		if (p==null)
			insertar(catservicio);
		else
			actualizar(catservicio);
	}

}
