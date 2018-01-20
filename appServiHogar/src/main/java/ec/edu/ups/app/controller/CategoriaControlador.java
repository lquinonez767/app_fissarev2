package ec.edu.ups.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import ec.edu.ups.app.data.CategoriaPerDAO;
import ec.edu.ups.app.model.Categoria;

@ManagedBean
@ViewScoped	
public class CategoriaControlador {
	
	private Categoria categoria;
	private List<Categoria> categorias;
	private List<SelectItem> listcategorias;
	private String id;
	
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private CategoriaPerDAO catperdao;
	
	@PostConstruct
	public void init(){
		categoria=new Categoria();
		loadCategorias();
	}
	
	//getters and setters

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
		loadDatosEditar(Integer.parseInt(id));
	}

	public FacesContext getFacesContext() {
		return facesContext;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public CategoriaPerDAO getCatperdao() {
		return catperdao;
	}

	public void setCatperdao(CategoriaPerDAO catperdao) {
		this.catperdao = catperdao;
	}
	
	
	public List<SelectItem> getListcategorias() {
		this.listcategorias = new ArrayList<SelectItem>();
		listcategorias.clear();
		for (Categoria cat : categorias){
			SelectItem catItem = new SelectItem(cat.getCodigo(), cat.getDescripcion());
			this.listcategorias.add(catItem);
		}
		return listcategorias;
	}

	public void setListcategorias(List<SelectItem> listcategorias) {
		this.listcategorias = listcategorias;
	}

		
	//funciones
	
	public String editar(){
			try{
				if(this.id!=null){
					System.out.println(categoria);
					catperdao.guardar(categoria);
					loadCategorias();
					return "listarCategoriaProv";
				}else{
					catperdao.insertar(categoria);
					return "listarCategoriaProv";
				}
			}catch(Exception e){
				String errorMessage = getRootErrorMessage(e);
	            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
	            facesContext.addMessage(null, m);
	            return null; 
			}
			
		}
			

			public String guardar(){
				try{ 
					System.out.println("Holaaaaaaaaa");
					System.out.println(categoria);
					//if(categoria.getCodigo().equals("")){
					//	return "listarCategoriaProv";
					//}else{
					catperdao.guardar(categoria);
					loadCategorias();
					return "listarCategoriaProv";
					//}
					
				}catch(Exception e){
					String errorMessage = getRootErrorMessage(e);
		            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
		            facesContext.addMessage(null, m);
		            return null; 
				}
				
			}
			
			public String loadDatosBuscar(int codigo){
				System.out.println("holaaaaaa");
				categoria = catperdao.leer(codigo);
				return "listarCategoriaProv";
			}
			
			public String loadDatosEditar(int codigo){
				categoria = catperdao.leer(codigo);
				return "editarCategoriaProv";
			}
			
			public String loadDatosEliminar(int codigo){
				catperdao.borrar(codigo);
				init();
				return "listarCategoriaProv";
			}
			
			
			private void loadCategorias() {
				categorias = catperdao.listadoCategorias(); 
			}
			
			public String eliminar(int codigo){
		    	catperdao.borrar(codigo);
		    	init();
		    	return null;
		    }

			
			private String getRootErrorMessage(Exception e) {
		        // Default to general error message that registration failed.
		        String errorMessage = "Registration failed. See server log for more information";
		        if (e == null) {
		            // This shouldn't happen, but return the default messages
		            return errorMessage;
		        }

		        // Start with the exception and recurse to find the root cause
		        Throwable t = e;
		        while (t != null) {
		            // Get the message from the Throwable class instance
		            errorMessage = t.getLocalizedMessage();
		            t = t.getCause();
		        }
		        // This is the root cause message
		        return errorMessage;
		    }


	
	

}
