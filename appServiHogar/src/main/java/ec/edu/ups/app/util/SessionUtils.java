package ec.edu.ups.app.util;

import java.io.Serializable;

import javax.faces.context.FacesContext;

public class SessionUtils implements Serializable {
	
	 private static final long serialVersionUID = 1L;

    public void add(String key, Object value) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
    }

    public String get(String key) {
      return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key).toString();
    }
}