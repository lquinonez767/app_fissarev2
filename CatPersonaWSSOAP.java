package ec.edu.ups.app.service;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import ec.edu.ups.app.data.PersonaDAO;
import ec.edu.ups.app.model.Categoria;
import ec.edu.ups.app.model.Persona;


@WebService(serviceName="WSUsuario")
public class CatPersonaWSSOAP {
	PersonaDAO dao;
	
	@WebMethod(operationName="wsInsertaUsuario")
	public boolean wsGuardaUsuario(Persona persona){
		Persona p=new Persona();
		
		dao.insertar(p);
		return true;
		
	}
	
	

	

}
