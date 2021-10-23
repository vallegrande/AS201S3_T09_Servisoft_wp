/**
 * 
 */
package com.servisoft.servicios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.servisoft.impl.Conexion;

/**
 * @author chema
 * Validaddor de registros únicos de DNI.
 */
@FacesValidator
public class ValidDNI extends Conexion implements Validator {
	
	public static boolean cliDNI(String dniCli) {
        try {
            PreparedStatement ps1 = Conexion.conectar().prepareStatement("SELECT DNICLI FROM CLIENTE WHERE DNICLI = '" +dniCli+ "'");
            ResultSet rs = ps1.executeQuery();
            while (rs.next()) {
                return true;
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error en dni repetido" + e.getMessage());
        }
        return false;
    }
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String dniCli = (String) value;
		if(cliDNI(dniCli) == true) {
			FacesMessage msg = new FacesMessage("Este DNI ya existe");
			throw new ValidatorException(msg);
		}
	}

}