package ec.edu.ups.app.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.app.model.Servicio;

@Stateless
public class ServicioDAO {
	@Inject
	private EntityManager em;
	
	public void insertar (Servicio servicio){
		em.persist(servicio);
	}
	
	public void actualizar (Servicio servicio){
		em.merge(servicio);
	}

	public void borrar (String codigo){
		em.remove(leer(codigo));
	}
	
	public Servicio leer (String codigo){
		Servicio p = em.find(Servicio.class, codigo);
		return p;
	}
	
	public List<Servicio> listadoServicios(){
		String jpql = "SELECT p FROM Servicio p";
		Query query = em.createQuery(jpql, Servicio.class);
		List<Servicio> listado = query.getResultList();
		return listado;
	} 
	
	public void guardar(Servicio servicio){
		Servicio p = leer(servicio.getCodigo());
		if (p==null)
			insertar(servicio);
		else
			actualizar(servicio);
	}
}
