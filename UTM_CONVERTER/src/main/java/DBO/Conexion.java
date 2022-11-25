package DBO;
/*Autor Leonardo Baini*/


import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

 
 
 
public class Conexion {
	static Logger logger = LogManager.getLogger(Conexion.class);
     
 
        private  Connection c;
        protected  Statement statemente;
        protected  ResultSet resulsete;
        public static String user=null;
        public static String pass=null;
        private static String cadena=null;
        private static String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    
     
    
        
        
       
    
        
        public static String getUser() {
			return user;
		}



		public void setUser(String user) {
			Conexion.user = user;
		}



		public static String getPass() {
			return pass;
		}



		public void setPass(String pass) {
			Conexion.pass = pass;
		}



		public Conexion(){
             
        }
         
         
        
        public boolean conectar(String server,String database,String usuario,String password){
        	boolean conecto;
        	try{
        		
            	
            	cadena="jdbc:sqlserver://"+server+";user="+usuario+";password="+password+";database="+database+"";
            	
                Class.forName(driver);
                c=DriverManager.getConnection(cadena);
                statemente=c.createStatement();       
                 
                conecto=true;
                
            }catch(ClassNotFoundException e1){
            	
           //  System.out.println("Error en los drivers");
             logger.error(e1.getMessage());
             conecto=false;
            }
            catch(SQLException e2){
            	// System.out.println(e2.getMessage());
            	 logger.error(e2.getMessage());
             
                
               
                conecto=false;
 
            }catch(Exception e3){
            	//System.out.println(e3.getMessage());
            	 logger.error(e3.getMessage());
            	
            	
                 conecto=false;
            	
            }
 return conecto;
    }
        
        public  void desconectar(){
            //estado=new JTextField();
             
                try {
                    if (c != null){
                        c.close();
                       
                         
                         
                         
                    //  System.out.println("Conexión liberada OK");
                        logger.info("Conexión liberada OK");
                    }
                    else{
                    	logger.error("Se envió comando de desconexión pero ya estaba desconectado");
                      //  System.out.println("Ya estaba desconectado");
                         
                    }
                     
                    //statemente.close();
                     
                     
                     
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                	
                    System.out.println("Desconectado incorrecto");
                    e.printStackTrace();
                }
                 
             
        }



		public Connection getC() {
			return c;
		}
}