package ec.edu.ups.app.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.app.model.Categoria;

@Stateless
public class CategoriaPerDAO {
	@Inject
	private EntityManager em;
	
	public void insertar (Categoria catpersona){
		em.persist(catpersona);
	}
	
	public void actualizar (Categoria catpersona){
		em.merge(catpersona);
	}

	public void borrar (int codigo){
		em.remove(leer(codigo));
	}
	
	public Categoria leer (int i){
		Categoria p = em.find(Categoria.class, i);
		return p;
	}
	
	public List<Categoria> listadoCategorias(){
		String jpql = "SELECT p FROM Categoria p";
		Query query = em.createQuery(jpql, Categoria.class);
		List<Categoria> listado = query.getResultList();
		return listado;
	} 
	
	public void guardar(Categoria catpersona){
		Categoria p = leer(catpersona.getCodigo());
		if (p==null)
			insertar(catpersona);
		else
			actualizar(catpersona);
	}
}
