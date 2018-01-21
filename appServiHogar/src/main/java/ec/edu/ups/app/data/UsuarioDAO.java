package ec.edu.ups.app.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.app.model.Usuario;

@Stateless
public class UsuarioDAO {

	@Inject
	private EntityManager em;
	
	public void insertar (Usuario usuario){
		em.persist(usuario);
	}
	
	public void actualizar (Usuario usuario){
		em.merge(usuario);
	}

	public void borrar (String nombre){
		em.remove(leer(nombre));
	}
	
	public Usuario leer (String nombre){
		Usuario p = em.find(Usuario.class, nombre);
		return p;
	}
	
	public List<Usuario> listadoUsuarios(){
		String jpql = "SELECT p FROM Proveedor p";
		Query query = em.createQuery(jpql, Usuario.class);
		List<Usuario> listado = query.getResultList();
		return listado;
	} 
	
	public List<Usuario> getUsuario(String Username, String Password){
		System.out.println("holaaaaaa2");
        String slq= "SELECT u FROM Usuario u WHERE u.username= :username and u.password= :password";
		Query q = em.createQuery(slq);
	    q.setParameter("username", Username);
	    q.setParameter("password", Password);
	    return q.getResultList();
     }
	
	public void guardar(Usuario usuario){
		Usuario p = leer(usuario.getUsername());
		if (p==null)
			insertar(usuario);
		else
			actualizar(usuario);
	}
		 
}
