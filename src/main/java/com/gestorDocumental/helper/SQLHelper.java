package com.gestorDocumental.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class SQLHelper {
	static final Logger LOG = LoggerFactory.getLogger(SQLHelper.class);

	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.driverClassName}")
	private String driverClassName;
	
	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(driverClassName);
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			LOG.error("Error al conectarse a BD. " + e.getMessage());
		}
		return con;
	}
	
	public String guardarDocumento(String descripcionDoc,Integer solicitud,Integer tipo) throws SQLException {

		Connection con = this.getConnection();
		String queryTest = "INSERT INTO GestorDocumental.DOCUMENTO(DESCRIPCION,SOLICITUD,TIPODOCUMENTO,RUTA) VALUES ('"+descripcionDoc+"',"+solicitud+","+tipo+",'rutadeldocumento')";
		String queryFila = "SELECT LAST_INSERT_ID() as 'IDDOCUMENTO' FROM GestorDocumental.DOCUMENTO ORDER by IDDOCUMENTO DESC LIMIT 1;";
		String idDocumento = null;
		
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(queryTest);
			ResultSet rs = stmt.executeQuery(queryFila);
			while (rs.next()) {
				  Integer numero = rs.getInt("IDDOCUMENTO");
				  System.out.println(numero + "\n");
				  idDocumento = numero.toString();
			}
			return idDocumento;
			
		}  catch (Exception e) {
			LOG.error(e.getMessage());
			try {
				if (con != null) {
					con.rollback();
				}
			} catch (SQLException e2) {
				LOG.error(e2.getMessage());
			}
		} finally {
			try {
				if (con != null)
					con.close();

			} catch (Exception e) {
				LOG.error(e.getMessage());
			}
		}
		
		return idDocumento;	
	}

	public List<String> obtenerProceso() throws SQLException {

		Connection con = this.getConnection();
		String queryProceso = "SELECT DESCRIPCION FROM GestorDocumental.PROCESO;";
		List<String> procesos=new ArrayList<String>();
		
		try {
			Statement stmt = con.createStatement();
			//stmt.executeUpdate(queryTest);
			ResultSet rs = stmt.executeQuery(queryProceso);
			while (rs.next()) {
				  procesos.add(rs.getString("DESCRIPCION"));
			}
			return procesos;
			
		}  catch (Exception e) {
			LOG.error(e.getMessage());
			try {
				if (con != null) {
					con.rollback();
				}
			} catch (SQLException e2) {
				LOG.error(e2.getMessage());
			}
		} finally {
			try {
				if (con != null)
					con.close();

			} catch (Exception e) {
				LOG.error(e.getMessage());
			}
		}
		
		return procesos;
		
	}
	public List<String> obtenerSubproceso(String proceso) throws SQLException {

		Connection con = this.getConnection();
		String querySubproceso = "SELECT DESCRIPCION FROM GestorDocumental.SUBPROCESO WHERE PROCESODESCRIPCION = "+"'"+proceso+"';";
		List<String> subprocesos=new ArrayList<String>();
		
		try {
			Statement stmt = con.createStatement();
			//stmt.executeUpdate(queryTest);
			ResultSet rs = stmt.executeQuery(querySubproceso);
			while (rs.next()) {
				  subprocesos.add(rs.getString("DESCRIPCION"));
			}
			return subprocesos;
			
		}  catch (Exception e) {
			LOG.error(e.getMessage());
			try {
				if (con != null) {
					con.rollback();
				}
			} catch (SQLException e2) {
				LOG.error(e2.getMessage());
			}
		} finally {
			try {
				if (con != null)
					con.close();

			} catch (Exception e) {
				LOG.error(e.getMessage());
			}
		}
		
		return subprocesos;
		
	}
	
	public List<String> obtenerDocumentosDeSubproceso(String subproceso) throws SQLException {

		Connection con = this.getConnection();
		String querySubproceso = "SELECT DESCRIPCION FROM GestorDocumental.DOCUMENTO;";
		List<String> subprocesos=new ArrayList<String>();
		
		try {
			Statement stmt = con.createStatement();
			//stmt.executeUpdate(queryTest);
			ResultSet rs = stmt.executeQuery(querySubproceso);
			while (rs.next()) {
				  subprocesos.add(rs.getString("DESCRIPCION"));
			}
			return subprocesos;
			
		}  catch (Exception e) {
			LOG.error(e.getMessage());
			try {
				if (con != null) {
					con.rollback();
				}
			} catch (SQLException e2) {
				LOG.error(e2.getMessage());
			}
		} finally {
			try {
				if (con != null)
					con.close();

			} catch (Exception e) {
				LOG.error(e.getMessage());
			}
		}
		
		return subprocesos;
		
	}
}