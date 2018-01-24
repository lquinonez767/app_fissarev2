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
		System.out.println("holaaaaaa3");
		System.out.println(usuario);
		em.persist(usuario);
	}
	
	public void actualizar (Usuario usuario){
		em.merge(usuario);
	}

	public void borrar (int id){
		em.remove(leer(id));
	}
	
	public Usuario leer (int id){
		System.out.println("holaaaaaa2.1");
		System.out.println(id);
		Usuario p = em.find(Usuario.class, id);
		System.out.println("holaaaaaa2.2");
		System.out.println(p);
		return p;
	}
	
	public List<Usuario> listadoUsuarios(){
		String jpql = "SELECT p FROM Usuario p";
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
		Usuario p = leer(usuario.getId());
		System.out.println("holaaaaaa5");
		if (p==null)
			insertar(usuario);
		else
			actualizar(usuario);
	}
	
	public boolean guardar_rest(Usuario usuario){
		Usuario p = leer(usuario.getId());
		System.out.println("holaaaaaa2");
		System.out.println(p);
		if (p==null){
			System.out.println("holaaaaaa4");
			System.out.println(usuario);
			insertar(usuario);
			return true;
		}else
			return false;
	}
		 
}
