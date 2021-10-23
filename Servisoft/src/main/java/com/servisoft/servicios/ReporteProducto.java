/**
 * 
 */
package com.servisoft.servicios;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperRunManager;

import com.servisoft.impl.Conexion;

/**
 * @author chema
 * Método de generación de reporte a formato pdf.
 */
public class ReporteProducto extends Conexion{
	public void getReportePdf(String root, String numeroacta)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		try {
			File reportfile = new File(root);

			Map<String, Object> parameter = new HashMap<String, Object>();

			parameter.put("tv", new String(numeroacta));

			byte[] bytes = JasperRunManager.runReportToPdf(reportfile.getPath(), parameter, Conexion.conectar());
			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
					.getExternalContext().getResponse();
			httpServletResponse.setContentType("application/pdf");
			httpServletResponse.setContentLength(bytes.length);
			ServletOutputStream outputStream = httpServletResponse.getOutputStream();
			outputStream.write(bytes, 0, bytes.length);

			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}