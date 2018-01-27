package ec.edu.ups.app.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.app.data.PedidoDAO;
import ec.edu.ups.app.data.PersonaDAO;
import ec.edu.ups.app.model.Pedido;
import ec.edu.ups.app.util.SessionUtils;

@ManagedBean
@ViewScoped	
public class PedidoControlador {
	
	 @Inject // inyectamos la dependencia
	 private SessionUtils session;
	 private String username; 
	
	private Pedido pedido;
	
	private List<Pedido> pedidos;
	private String id;
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private PedidoDAO pedidodao;
	
	@Inject
	private PersonaDAO pdao;
	
	
	@PostConstruct
	public void init() {
		pedido = new Pedido();
		System.out.println("usuarioLogueado");
		username=session.get("usuarioLogueado");
		System.out.println(username);
		loadPedidos();
	}

	//get and set
	
	
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public String getId() {
		return id;		
	}

	public void setId(String id) {
		this.id = id;
		loadDatosEditar(Integer.parseInt(id));
	}

	public PedidoDAO getPedidodao() {
		return pedidodao;
	}

	public void setPedidodao(PedidoDAO pedidodao) {
		this.pedidodao = pedidodao;
	}

	public PersonaDAO getPdao() {
		return pdao;
	}

	public void setPdao(PersonaDAO pdao) {
		this.pdao = pdao;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	//Funciones
	
	
	public String editar(){
		try{
			if(this.id!=null){
				System.out.println("Holaaaaaaaaapedido");
				//System.out.println(pedido);
				pedidodao.guardar(pedido);
				loadPedidos();
				return "listarPedido";
			}else{
				pedidodao.insertar(pedido);
				return "RegistroPedido";
			}
		}catch(Exception e){
			String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
            facesContext.addMessage(null, m);
            return null; 
		}
		
	}
	
	public String guardar(String itemPersona){
		try{ 
			System.out.println("Holaaaaaaaaa");
			System.out.println(pedido);
			System.out.println(itemPersona);
			pedido.setPersona(pdao.leer(itemPersona));
			System.out.println(pedido);
			if(pedido.getCodigo() ==  0){
				return "listarPedido";
			}else{
				pedidodao.guardar(pedido);
				loadPedidos();
				return "listarPedido";
			}
			
		}catch(Exception e){
			String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
            facesContext.addMessage(null, m);
            return null; 
		}
		
	}
	
	public String loadDatosBuscar(int codigo){
		System.out.println("holaaaaaa");
		pedido = pedidodao.leer(codigo);
		return "listarPedido";
	}
	
	public String loadDatosEditar(int codigo){
		pedido = pedidodao.leer(codigo);
		return "editarPedido";
	}
	
	public String loadDatosEliminar(int codigo){
		System.out.println("holaaaaaa");
		System.out.println("codigo");
		pedidodao.borrar(codigo);
		init();
		return "listarPedido";
	}
	
	
	private void loadPedidos() {
		System.out.println("holaaaaaapedidos");
		System.out.println(session);
		pedidos = pedidodao.listadoPedidos();
	}
	
	public String eliminar(int codigo){
    	pedidodao.borrar(codigo);
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
