package MASTERMIND;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import MERCATOR.CoordinateConverter;
import MERCATOR.UTMPoint;

public class Cliente {
	private static final Logger logger = LogManager.getLogger(Cliente.class);
	
	int site_no;
	double latitud_geodesica;
	double longitud_geodesica;
	double latitud_utm;
	double longitud_utm;
	int zona_utm;
	UTMPoint punto;
	
	
	
	public Cliente(int site_no,double latitud_geodesica,double longitud_geodesica) {
		this.site_no=site_no;
		this.latitud_geodesica=latitud_geodesica;
		this.longitud_geodesica=longitud_geodesica;
		logger.info("Creando cliente virtual con site_no="+site_no+" latitud="+latitud_geodesica+" longitud="+longitud_geodesica);
		calcular_coordenadas_utm(latitud_geodesica,longitud_geodesica);
		
	}



	private void calcular_coordenadas_utm(double latitud_geodesica, double longitud_geodesica) {
		punto = CoordinateConverter.fromGeodeticToUTM(longitud_geodesica,latitud_geodesica);
		this.latitud_utm=punto.getX();
		this.longitud_utm=punto.getY();
		this.zona_utm=punto.getZone();
		logger.info("Calculando coordenadas UTM con algoritmo de Coticchia-Surace");
		logger.info("Latitud_geodesica:"+this.latitud_geodesica+" igual a latitud_utm:"+this.latitud_utm);
		logger.info("Longitud_geodesica:"+this.longitud_geodesica+" igual a longitud_utm:"+this.longitud_utm);
		logger.info("Zona:"+this.getZona_utm());
		
	}



	public int getSite_no() {
		return site_no;
	}



	public void setSite_no(int site_no) {
		this.site_no = site_no;
	}



	public double getLatitud_geodesica() {
		return latitud_geodesica;
	}



	public void setLatitud_geodesica(double latitud_geodesica) {
		this.latitud_geodesica = latitud_geodesica;
	}



	public double getLongitud_geodesica() {
		return longitud_geodesica;
	}



	public void setLongitud_geodesica(double longitud_geodesica) {
		this.longitud_geodesica = longitud_geodesica;
	}



	public double getLatitud_utm() {
		return latitud_utm;
	}



	public void setLatitud_utm(double latitud_utm) {
		this.latitud_utm = latitud_utm;
	}



	public double getLongitud_utm() {
		return longitud_utm;
	}



	public void setLongitud_utm(double longitud_utm) {
		this.longitud_utm = longitud_utm;
	}



	public int getZona_utm() {
		return zona_utm;
	}



	public void setZona_utm(int zona_utm) {
		this.zona_utm = zona_utm;
	}



	public UTMPoint getPunto() {
		return punto;
	}



	public void setPunto(UTMPoint punto) {
		this.punto = punto;
	}



	public String getQueryUpdate() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
