/**
 * 
 */
package com.servisoft.impl.dao;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.util.SystemOutLogger;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.servisoft.impl.ClienteImpl;
import com.servisoft.impl.Conexion;
import com.servisoft.model.ClienteModelo;

/**
 * @author chema
 * Dao de cliente.
 */
public class ClienteDao extends Conexion implements ClienteImpl {

	private static String hook = "https://hooks.slack.com/services/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";

	private static String oAuthToken = "xoxb-2575501028644-2558669366679-H2wv4LUqso5YAddadcyGggRF";

	private static String slackChannel = "slackjavachannel";

	@Override
	public void registrar(ClienteModelo cli) throws Exception {
		String sql = "INSERT INTO CLIENTE "
				+ "(CODCLI, NOMCLI, APECLI, USERCLI, PSWCLI, CELCLI, DNICLI, MAILCLI, DIRCLI, CODUBI, ESTCLI) "
				+ "VALUES " + "(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'A')";
		
		try {
			PreparedStatement ps = Conexion.conectar().prepareStatement(sql);
			ps.setString(1, cli.getNombre());
			ps.setString(2, cli.getApellido());
			ps.setString(3, cli.getUsuario());
			ps.setString(4, cli.getPassword());
			ps.setString(5, cli.getCelular());
			ps.setString(6, cli.getDni());
			ps.setString(7, cli.getCorreo());
			ps.setString(8, cli.getDirec());
			ps.setString(9, cli.getCodubi());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			System.out.println("ClienteDao-Error en registrar: " + e.getMessage());
		} finally {
			Conexion.cerrarCnx();
		}
	}

	@Override
	public void modificar(ClienteModelo cli) throws Exception {
		String sql = "UPDATE CLIENTE SET NOMCLI = ?, APECLI = ?, CELCLI = ?, DNICLI = ?, MAILCLI = ?, DIRCLI = ?, CODUBI = ? WHERE CODCLI = ?";

		try {
			PreparedStatement ps = Conexion.conectar().prepareStatement(sql);

			ps.setString(1, cli.getNombre());
			ps.setString(2, cli.getApellido());
			ps.setString(3, cli.getCelular());
			ps.setString(4, cli.getDni());
			ps.setString(5, cli.getCorreo());
			ps.setString(6, cli.getDirec());
			ps.setString(7, cli.getCodubi());
			ps.setInt(8, cli.getCodigo());

			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			System.out.println("ClienteDao-Error en modificar: " + e.getMessage());
		} finally {
			Conexion.cerrarCnx();
		}
	}

	@Override
	public void eliminar(ClienteModelo cli) throws Exception {
		String sql = "UPDATE CLIENTE SET ESTCLI = 'I' WHERE CODCLI = ?";
		try {
			PreparedStatement ps = Conexion.conectar().prepareStatement(sql);
			ps.setInt(1, cli.getCodigo());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			System.out.println("ClienteDao-Error en eliminar: " + e.getMessage());
		} finally {
			Conexion.cerrarCnx();
		}
	}

	@Override
	public List<ClienteModelo> listar() throws Exception {
		List<ClienteModelo> lista = new ArrayList<>();
		ClienteModelo cli;
		String sql = "SELECT * FROM CLIENTE";
		try {
			Statement stmt = Conexion.conectar().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				cli = new ClienteModelo();
				cli.setCodigo(rs.getInt("CODCLI"));
				cli.setNombre(rs.getString("NOMCLI"));
				cli.setApellido(rs.getString("APECLI"));
				cli.setUsuario(rs.getString("USERCLI"));
				cli.setPassword(rs.getString("PSWCLI"));
				cli.setCelular(rs.getString("CELCLI"));
				cli.setDni(rs.getString("DNICLI"));
				cli.setEstado(rs.getString("ESTCLI"));
				cli.setCorreo(rs.getString("MAILCLI"));
				cli.setDirec(rs.getString("DIRCLI"));
				cli.setCodubi(rs.getString("CODUBI"));
				lista.add(cli);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("Cliente - Error en ListarDAO: " + e.getMessage());
		} finally {
			Conexion.cerrarCnx();
		}
		return lista;
	}

	@Override
	public void buscarDNI(ClienteModelo cli) throws Exception {
		String dni = cli.getDni();
		String token = "?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImZlcm5hbmRvLmNhbmFsZXNAdmFsbGVncmFuZGUuZWR1LnBlIn0.fDsKf5LqP1kRf8U3bC_ZQK9D7xxcInfq5IpBjDva160";
		String enlace = "https://dniruc.apisperu.com/api/v1/dni/" + dni + token;
		try {
			URL url = new URL(enlace);
			URLConnection request = url.openConnection();
			request.connect();
			JsonParser jp = new JsonParser();
			JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
			if (root.isJsonObject()) {
				JsonObject rootobj = root.getAsJsonObject();
				String apellido_paterno = rootobj.get("apellidoPaterno").getAsString();
				String apellido_materno = rootobj.get("apellidoMaterno").getAsString();
				String nombres = rootobj.get("nombres").getAsString();
				String apellidos = apellido_paterno + " " + apellido_materno;

				StringBuffer nombreOutput = new StringBuffer();
				StringBuffer apellidoOutput = new StringBuffer();

				Matcher nombreMatch = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(nombres);
				Matcher apellidoMatch = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(apellidos);

				while (nombreMatch.find()) {
					nombreMatch.appendReplacement(nombreOutput,
							nombreMatch.group(1).toUpperCase() + nombreMatch.group(2).toLowerCase());
				}

				while (apellidoMatch.find()) {
					apellidoMatch.appendReplacement(apellidoOutput,
							apellidoMatch.group(1).toUpperCase() + apellidoMatch.group(2).toLowerCase());
				}

				String nombreFinal = nombreMatch.appendTail(nombreOutput).toString();
				String apellidoFinal = apellidoMatch.appendTail(apellidoOutput).toString();

				cli.setNombre(nombreFinal);
				cli.setApellido(apellidoFinal);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Debes ingresar 8 números"));
		}
	}

	@Override
	public void buscarRUC(ClienteModelo cli) throws Exception {
		String ruc = cli.getRuc();
		String token = "?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImZlcm5hbmRvLmNhbmFsZXNAdmFsbGVncmFuZGUuZWR1LnBlIn0.fDsKf5LqP1kRf8U3bC_ZQK9D7xxcInfq5IpBjDva160";
		String enlace = "https://dniruc.apisperu.com/api/v1/ruc/" + ruc + token;
		try {
			URL url = new URL(enlace);
			URLConnection request = url.openConnection();
			request.connect();
			JsonParser jp = new JsonParser();
			JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
			if (root.isJsonObject()) {
				JsonObject rootobj = root.getAsJsonObject();
				String razon = rootobj.get("razonSocial").getAsString();
				String direc = rootobj.get("direccion").getAsString();
				String ubigeo = rootobj.get("ubigeo").getAsString();

				System.out.println("Resultado:\n");
				System.out.println("    Razón social: " + razon + "\n" + "    Dirección: " + direc + "\n"
						+ "    Ubigeo: " + ubigeo + "\n");

				cli.setRazsoc(razon);
				cli.setDirec(direc);
				cli.setCodubi(ubigeo);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Busqueda", "RUC no encontrado"));
		}
	}

	public static void sendMsgSlack(String message) {
		try {
			StringBuilder msgBuild = new StringBuilder();

			msgBuild.append(message);

			Payload pl = Payload.builder().channel(slackChannel).text(msgBuild.toString()).build();

			WebhookResponse wbResp = Slack.getInstance().send(hook, pl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}