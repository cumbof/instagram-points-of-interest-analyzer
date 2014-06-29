package instagram.point.db;

import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.logging.Logger;
import com.mchange.v2.c3p0.*;

public class C3P0DataSource {

	private static Logger logger = Logger.getLogger("C3P0DataSource");
	private ComboPooledDataSource cpds;
	private static C3P0DataSource instance;
	
	private boolean postgres_driver = true;

	private String driver = (postgres_driver == true) ? "org.postgresql.Driver" : "com.mysql.jdbc.Driver";
	private String uri_base = (postgres_driver == true) ? "jdbc:postgresql://" : "jdbc:mysql://";
	
	private C3P0DataSource(String _uri, String username, String password){
		
		String uri = uri_base + _uri;
				
		this.cpds = new ComboPooledDataSource();
		try {
			cpds.setDriverClass(driver);
		} catch (PropertyVetoException e) {
			logger.severe("Error Loading Class Driver " + e.getMessage());
		}
		this.cpds.setJdbcUrl(uri);
		this.cpds.setUser(username);
		this.cpds.setPassword(password);

		cpds.setMinPoolSize(5);
		cpds.setAcquireIncrement(5);
		cpds.setMaxPoolSize(20);
	}

	public static C3P0DataSource getInstance(String addr, String usr, String pwd){
		if (instance==null)
			instance = new C3P0DataSource(addr, usr, pwd);
		return instance;
	}

	public Connection getConnection()  {
		Connection connection = null;
		try {
			connection = cpds.getConnection();
		} catch (SQLException e) {
			logger.severe("Connessione non creata "+ e.getMessage());
		}
		return connection;
	}
	
	public void destroy() throws SQLException {
		DataSources.destroy(this.cpds);
	}
}