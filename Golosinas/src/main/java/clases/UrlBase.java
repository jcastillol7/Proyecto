/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import static javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author mmarvin
 * For the glory of God
 */
//Clase que devueleve el nombre y puerto del servidor*******
public class UrlBase {

    public UrlBase() {

    }

    public static String getURLBase(FacesContext fc) {
        StringBuffer retVal = new StringBuffer();
        HttpServletRequest req = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
        try {
            URL url = new URL(req.getRequestURL().toString());
            // System.out.println("puerto " + url.getPort());
            if (url.getPort() == -1) {
                try {
                    subscribe();
                } catch (Exception ex) {
                    Logger.getLogger(UrlBase.class.getName()).log(Level.SEVERE, null, ex);
                }
                retVal.append(req.getProtocol().substring(0, 4).toLowerCase()).append("s");
                retVal.append("://");
                retVal.append(url.getHost());
                //retVal.append(":");
                //retVal.append(url.getPort());

            } else {
                retVal.append(req.getProtocol().substring(0, 4).toLowerCase());
                retVal.append("://");
                retVal.append(url.getHost());
                retVal.append(":");
                retVal.append(url.getPort());
                //retVal.append(req.getContextPath());
                //retVal.append("/"); 
            }

        } catch (MalformedURLException ex) {
            // Do something smart here
        }
        return retVal.toString();
    }

    private static void trustAllHttpsCertificates() throws Exception {

        //  Create a trust manager that does not validate certificate chains:
        javax.net.ssl.TrustManager[] trustAllCerts
                = new javax.net.ssl.TrustManager[1];

        javax.net.ssl.TrustManager tm = new miTM();

        trustAllCerts[0] = tm;

        javax.net.ssl.SSLContext sc
                = javax.net.ssl.SSLContext.getInstance("SSL");

        sc.init(null, trustAllCerts, null);

        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(
                sc.getSocketFactory());

    }

    static public String subscribe() throws Exception {
        System.out.println("entro");
        String resp = "";
        String urlString = "https://<secureserver>/";
        URL url;
        URLConnection urlConn;
        DataOutputStream printout;
        DataInputStream input;
        String str = "";
        int flag = 1;

        try {
            Properties sysProperties = System.getProperties();

            // change proxy settings if required and enable the below lines
            //sysProperties.put("proxyHost", "10.1.2.200");
            //sysProperties.put("proxyPort", "8181");
            //sysProperties.put("proxySet", "true");

// Now you are telling the JRE to ignore the hostname
            HostnameVerifier hv = new HostnameVerifier() {

                public boolean verify(String urlHostName, SSLSession session) {
                   // System.out.println("Warning: URL Host: " + urlHostName + " vs. "
                     //       + session.getPeerHost());
                    return true;
                }
            };
            trustAllHttpsCertificates();
            /* setDefaultHostnameVerifier(
                    new javax.net.ssl.HostnameVerifier() {

                public boolean verify(String hostname,
                        javax.net.ssl.SSLSession sslSession) {
                    System.out.println("hostname "+hostname);
                    if (hostname.equals("10.1.2.200")) {
                        return true;
                    }
                    return false;
                }
            });*/

            // Now you are telling the JRE to trust any https server. 
            // If you know the URL that you are connecting to then this should not be a problem
           // System.out.println("hv" + hv);

            HttpsURLConnection.setDefaultHostnameVerifier(hv);

            url = new URL(urlString);
            urlConn = url.openConnection();
            urlConn.setDoInput(true);
            Object object;
            urlConn.setUseCaches(false);

            /* urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            input = new DataInputStream(urlConn.getInputStream());
            
            while (null != ((str = input.readLine()))){
                if (str.length() >0){
                    str = str.trim();
                    if(!str.equals("")){
                        //System.out.println(str);
                        resp += str;
                    }
                }
            }
            input.close();*/
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return resp;
    }

    //Method used for bypassing SSL verification
    public static void disableSSLVerification() {

        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }

        }};

        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }

}
