/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umg.edu.jersey.clases;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mmavin
 */
public class LogOauth implements Serializable {

    private String Archivo;

    public LogOauth() {

    }

    public void logConsultas(String nomWebservice, String facultad, String anio, String carnet) {
        Padl padl = new Padl();
        Date fecha = new Date();
        BufferedWriter bw = null;
        FileWriter fw = null;
        PrintWriter out = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat horaFormat = new SimpleDateFormat("hh:mm:ss");
        String hora = horaFormat.format(fecha);
        Archivo = "/logConsultas/" + nomWebservice + "-" + dateFormat.format(fecha).trim() + ".txt";
        File fileLog = new File(Archivo);
        try {
            fw = new FileWriter(fileLog, true);
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
            if (fileLog.exists()) {
                out.println(padl.Radl(facultad, " ", 4) + " " + anio + " " + padl.Padl(carnet, " ", 5)+" "+hora);
            } else if (fileLog.createNewFile()) {
                out.println(padl.Radl(facultad, " ", 4) + " " + anio + " " + padl.Padl(carnet, " ", 5)+" "+hora);
            } else {
                System.out.println("Archivo  no se Ha podido crear 1");
            }

        } catch (IOException ex) {
            System.out.println("Archivo  no se Ha podido crear 2");
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                    if (fw != null) {
                        fw.close();
                    }
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException ex) {
                    System.out.println("Archivo  no se Ha podido cerrar");
                }
            }

        }

    }

}
