import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import DBO.MetodosSql;



//Test en https://www.apsalin.com/utm-to-geodetic-on-ellipsoid/
/**
 * Gracias especiales a https://github.com/ctt-gob-es/allocalgis
	Coticchia-Surace
	
 	https://es.wikipedia.org/wiki/WGS84

	https://www.academia.edu/9291952/Aprende_a_convertir_coordenadas_geograficas_en_UTM_y_UTM_en_geograficas

	https://bibdigital.epn.edu.ec/bitstream/15000/19364/1/CD-8745.pdf
 * 
 *
 */

public class UtmConverter {
	private static final Logger logger = LogManager.getLogger(UtmConverter.class);
	
	public static void main(String[] args) {
		/*logger.info("Iniciando sistema de conversión de coordenadas geodésicas a UTM");
		*/
		Utilidades.cargarClientesDeMasterMind();
		Utilidades.actualizarDatosEnBBDD();
	
		

	}

}
