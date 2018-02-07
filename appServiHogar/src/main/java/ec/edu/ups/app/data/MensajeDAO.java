package ec.edu.ups.app.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.app.model.Mensaje;

@Stateless
public class MensajeDAO {
	@Inject
	private EntityManager em;
	
	public void insertar (Mensaje mensaje){
		em.persist(mensaje);
	}
	
	public void actualizar (Mensaje mensaje){
		em.merge(mensaje);
	}

	public void borrar (int codigo){
		em.remove(leer(codigo));
	}
	
	public Mensaje leer (int codigo){
		Mensaje p = em.find(Mensaje.class, codigo);
		return p;
	}
	
	public List<Mensaje> listadoMensajes(){
		String jpql = "SELECT s FROM Mensaje s";
		Query query = em.createQuery(jpql, Mensaje.class);
		List<Mensaje> listado = query.getResultList();
		return listado;
	} 
	
	public void guardar(Mensaje mensaje){
		Mensaje p = leer(mensaje.getCodigo());
		if (p==null) {
			insertar(mensaje);
		}
		else
			actualizar(mensaje);
	}

}
