package ec.edu.ups.app.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.app.model.Persona;

@Stateless
public class PersonaDAO {
	
	@Inject
	private EntityManager em;
	
	
	public void insertar (Persona persona){
		em.persist(persona);
	}
	
	public void actualizar (Persona persona){
		em.merge(persona);
	}

	public void borrar (String cedula){
		em.remove(leer(cedula));
	}
	
	public Persona leer (String cedula){
		Persona p = em.find(Persona.class, cedula);
		return p;
	}
	
	public List<Persona> listadoPersonas(){
		String jpql = "SELECT p FROM Persona p";
		Query query = em.createQuery(jpql, Persona.class);
		List<Persona> listado = query.getResultList();
		return listado;
	} 
	
	public void guardar(Persona persona){
		Persona p = leer(persona.getCedula());
		if (p==null)
			insertar(persona);
		else
			actualizar(persona);
	}

}
