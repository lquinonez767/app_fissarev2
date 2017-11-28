package ec.edu.ups.app.data;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.edu.ups.app.model.Persona;

@Stateless
public class PersonaDAO {
	
	@Inject
	private EntityManager em;
	@Inject
	private PersonaDAO pdao;
	
	public void insertar (Persona persona){
		em.persist(persona);
	}
	
	public void actualizar (Persona persona){
		em.merge(persona);
	}

	public void borrar (String  cedula){
		em.remove(leer(cedula));
	}
	public Persona leer (String cedula){
		em.find(Persona.class, cedula);
		
		return null;
}
}
