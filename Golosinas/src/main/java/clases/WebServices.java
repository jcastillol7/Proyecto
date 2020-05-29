/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;


import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import com.google.gson.Gson;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import wrapper.TokenModel;

/**
 *
 * @author pjarrecis
 */
public class WebServices {
//////////////Consumidor>ColodadorParametrosUrl>convertidorStringJson>ConvertidorJsonObjeto
    static String Accesstoken;

    public WebServices() {
    }

    ///////////////Metodo que consigue el objeto token que contiene el accesstoken......
    public Object consumidorDeToken(String[][] parametros, String url){
        TokenModel token = new TokenModel();
        try {
            
            String a = (String) colocadorParametrosUrl(parametros, url);
            a = (String) convertidorStringJson(a, "hola");
            
            token = (TokenModel) convertidorJsonObjeto(a, token);
            System.out.println("Access Token" + token.getAccessToken());
            Accesstoken = "Bearer " + token.getAccessToken();
        } catch (Exception e) {
            return "";
        }
        
        return "Bearer " + token.getAccessToken();
    }

    //////Metodo que le coloca los parametros a la url a buscar
    public Object colocadorParametrosUrl(String[][] parametros, String url) {
        try {
            url = url + "?";
            for (int i = 0; i < parametros.length; i++) {
                String[] parametro = parametros[i];
                if (i != 0) {
                    url = url + "&" + parametro[0] + "=" + parametro[1];
                } else {
                    url = url + parametro[0] + "=" + parametro[1];
                }
            }
            return url;
        } catch (Exception e) {
            return "";
        }
    }

    ////Recibe url y la convierte a un string json
    public Object convertidorStringJson(String str, String token)  {
        try {
            URL url = new URL(str);
            URLConnection urlc = url.openConnection();
            HttpURLConnection conn = (HttpURLConnection) urlc;
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", token);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            String line;
            final StringBuilder builder = new StringBuilder(2048);
            while ((line = bfr.readLine()) != null) {
                builder.append(line);
            }
            String a = builder.toString();
            conn.disconnect();
            return a;
        } catch (Exception e) {
            System.out.println("Error:   "+e);
            return "";
        }
    }
    
    
    
     ////Recibe url y la convierte a un string json
    public Object convertidorStringJsonPost(String str, String token) {
        try {
            URL url = new URL(str);
            URLConnection urlc = url.openConnection();
            HttpURLConnection conn = (HttpURLConnection) urlc;
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", token);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            String line;
            final StringBuilder builder = new StringBuilder(2048);
            while ((line = bfr.readLine()) != null) {
                builder.append(line);
            }
            String a = builder.toString();
            conn.disconnect();
            return a;
        } catch (Exception e) {
            System.out.println(e);
            return "";
        }
    }
    
    
    
    
    
    
    
    
    

    /////metodo que mapea el JSON a un objeto Java.
    public Object convertidorJsonObjeto(String url, Object prueba) {
        try {
            Gson gson = new Gson();
            
            prueba = gson.fromJson(url, prueba.getClass());
        } catch (Exception e) {
            System.out.println("Error///////////////                 "+e);
            return "";
        }
        
        return prueba;
    }
    
    public Object consumidorDerecursoPorTokenGet(String[][] parametros, String url,String token,Object almacenador){
        try {
            String a = (String) colocadorParametrosUrl(parametros,url);
            a =   (String)convertidorStringJson(a,token);
            almacenador =convertidorJsonObjeto(a,almacenador);   
        } catch (Exception e) {
            System.out.println("ERROR: "+e);
            return "";
        }
        
        return almacenador;
    }
    
    public Object consumidorDerecursoPorTokenPost(String[][] parametros, String url,String token,Object almacenador){
        try {
            String a = (String) colocadorParametrosUrl(parametros,url);
            a =   (String)convertidorStringJsonPost(a,token);
            almacenador =convertidorJsonObjeto(a,almacenador);   
        } catch (Exception e) {
            System.out.println("ERROR: "+e);
            return "";
        }
        
        return almacenador;
    }
    
    
    
    public Object consumidorDeRecurso(String[][] parametros, String url,Object almacenador){
        try {
            String a = (String) colocadorParametrosUrl(parametros,url);
            a =   (String)convertidorStringJson(a,"Hola");
            almacenador =convertidorJsonObjeto(a,almacenador);   
        } catch (Exception e) {
            System.out.println("ERROR: "+e);
            return "";
        }
        
        return almacenador;
    }

    public static String getAccesstoken() {
        return Accesstoken;
    }

    public static void setAccesstoken(String Accesstoken) {
        WebServices.Accesstoken = Accesstoken;
    }
    
    
    
    
}
