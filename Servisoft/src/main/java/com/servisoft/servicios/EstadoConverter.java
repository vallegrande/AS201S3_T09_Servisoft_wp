package com.servisoft.servicios;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("tipoVal")
public class EstadoConverter implements Converter {
	@Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
        return value;
    }
    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
        String tipo="";
        tipo = (String) value;
        
        if(value != null){
            switch(tipo){
                case "V":
                	tipo = "Vinos";
                    break;
                case "P":
                	tipo = "Piscos";
                    break;
            }
        }else{
        	tipo = "Ninguno";
        }
        
        return tipo;
    }
}