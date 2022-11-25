

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import MASTERMIND.Cliente;
import DBO.MetodosSql;
import CONFIGS.ReadPropertyFile;

public class Utilidades {

	private static final Logger logger = LogManager.getLogger(Utilidades.class);

	public static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	private static String ipDeBase;
	private static String usuarioBase;
	private static String passwordBase;
	private static String queryExtractora;
	private static String queryActualizadora;
	private static String baseDefault;
	
	private static String leerFichero(String ruta_archivo) {

	File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    String lecturaCompleta="";

    try {
       // Apertura del fichero y creacion de BufferedReader para poder
       // hacer una lectura comoda (disponer del metodo readLine()).
       archivo = new File (ruta_archivo);
       fr = new FileReader (archivo);
       br = new BufferedReader(fr);

       // Lectura del fichero
       String linea;
       while((linea=br.readLine())!=null) {
    	   lecturaCompleta=lecturaCompleta+linea+"\n";
       }
    }
    catch(Exception e){
    	logger.error(e.getLocalizedMessage());
      // e.printStackTrace();
       
    }finally{
       // En el finally cerramos el fichero, para asegurarnos
       // que se cierra tanto si todo va bien como si salta 
       // una excepcion.
       try{                    
          if( null != fr ){   
             fr.close();     
          }                  
       }catch (Exception e2){ 
    	   logger.error(e2.getLocalizedMessage());
          //e2.printStackTrace();
       }
    }
    return lecturaCompleta;
}
	

	/**
	 * Se conecta a MasterMind obtiene los datos especificados en la query
	 * extractora, crea los objetos clientes en un array de objetos clientes para
	 * que luego corra el proceso de iteración de objetos actualizando la base de
	 * datos. Estos clientes creados ya cuentan en este proceso con la seccional que
	 * le corresponde.
	 */
	public static void cargarClientesDeMasterMind() {
		leerArchivoDeConfiguraciones();
		logger.info("Iniciando creación de clientes virtuales");

		Cliente cliente;
		MetodosSql metodos = new MetodosSql(ipDeBase, usuarioBase, passwordBase);
		metodos.setDatabase(baseDefault);
		queryExtractora=leerFichero(queryExtractora);
	
		
		
		ArrayList<ArrayList<String>> _clientes = metodos.consultar(queryExtractora);
		

		for (int i = 0; i < _clientes.size(); i++) {
		try {	
		cliente = new Cliente(
				 Integer.parseInt(_clientes.get(i).get(0)),//site_no
				 Double.parseDouble(_clientes.get(i).get(1)),//latitude
				 Double.parseDouble(_clientes.get(i).get(2))//longitude
				);
		clientes.add(cliente);
		}catch(Exception e) {
		logger.error(e.getLocalizedMessage());
		}
			
		}
		
		logger.info("Fin de creación de clientes virtuales en el array Utilidades.clientes");

	}

	/**
	 * Lee las configuraciones de conectividad de ip, base de datos, usuario,
	 * password, etc.. del archivo configs/Configs.properties
	 */
	public static void leerArchivoDeConfiguraciones() {
		Properties props = ReadPropertyFile.getInstance().obtenerPropiedades();
		ipDeBase = props.getProperty("ipDeBase");
		usuarioBase = props.getProperty("usuarioBase");
		passwordBase = props.getProperty("passwordBase");
		queryExtractora = props.getProperty("queryExtractora");
		queryActualizadora = props.getProperty("queryActualizadora");
		baseDefault = props.getProperty("baseDefault");

		logger.info("Hosts de Mastermind a usar : " + ipDeBase);
		logger.info("Base de datos : " + baseDefault);
		// logger.info("seguirServidorActivo : "+seguirServidorActivo);
		logger.info("usuarioBase : " + usuarioBase);
		// logger.info("passwordBase : "+passwordBase);
		logger.info("queryExtractora : " + queryExtractora);
		logger.info("queryActualizadora : " + queryActualizadora);

	}

	public static String getQueryActualizadora() {
		return queryActualizadora;
	}

	public static void actualizarDatosEnBBDD() {
		MetodosSql metodos = new MetodosSql(ipDeBase, usuarioBase, passwordBase);
		metodos.setDatabase(baseDefault);

		metodos.insertarOmodifClientes(clientes,queryActualizadora);

	}

}
