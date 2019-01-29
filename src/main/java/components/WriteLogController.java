/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteLogController {
    
    File log = new File("src"+File.separator+"main"+File.separator+"resources"+File.separator+"log.txt");
        
    public WriteLogController() {}
    
    public void writeInLog(String text){
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(log, true);
            bw = new BufferedWriter(fw);
            
//            bw.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
            bw.write(text);
            bw.newLine();
            
            fw.flush();
            bw.flush();
        } catch (IOException ex) {
            System.out.println("Error al escribir en el fichero log: "+text);
            ex.printStackTrace();
        }finally {
        	try {
				fw.close();
				bw.close();
			} catch (IOException e) {
	            System.out.println("Error al cerrar el fichero log");
				e.printStackTrace();
			}
        }
    }
    
    //de momento no sirve para nada
    /*public String getFinalError(){
        try {
            int lines = Files.readAllLines(Paths.get("log.txt")).size();
            //si no pone "REALIZANDO UN SEGUNDO INTENTO" envia el log, sino dice que es error de tickets (los tiquets no pueden esccribir en el log)
            return (Files.readAllLines(Paths.get("log.txt")).get(lines-2).substring(31).equals("REALIZANDO UN SEGUNDO INTENTO")
                    || Files.readAllLines(Paths.get("log.txt")).get(lines-2).substring(31).equals("Ticket cargado correctamente")
                    ?" - Error en los tickets":Files.readAllLines(Paths.get("log.txt")).get(lines-2).substring(29));//le quitamos los 29 caracteres de delante (21/11/2018 10:07:17 - (ERROR))               
        }
        catch (IOException e){
            System.out.println("Error al intentar recoger el ultimo error (comprobar que es UTF-8): "+e);
            return "No se ha podido recoger el ultimo error, mirar el log (y comprobar que es UTF-8)";
        }
    }*/
}
