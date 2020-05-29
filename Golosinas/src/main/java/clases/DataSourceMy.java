package clases;


import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;


public class DataSourceMy {
	 
	private String cUserName;
	private String cPassWord;
	private String cDataBase;
	private String cDriverName;
	private DataSource dataSource;
	
	public DataSourceMy(String basededatos, String usuario, String clave) {
		cUserName = usuario;
		cPassWord = clave;
		cDataBase = basededatos;
                cDriverName = "com.mysql.jdbc.Driver";
		
	}

	public DataSource GetDataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setUsername(cUserName);
		basicDataSource.setPassword(cPassWord);
		basicDataSource.setUrl(cDataBase);
                basicDataSource.setDriverClassName(cDriverName);
                basicDataSource.setMaxIdle(10);
                basicDataSource.setMaxIdle(15);
		
		
		// Opcional. Sentencia SQL que le puede servir a BasicDataSource
		// para comprobar que la conexion es correcta.
		//basicDataSource.setValidationQuery("select 1");
		dataSource = basicDataSource;
		return dataSource;
	}
	
	

}
