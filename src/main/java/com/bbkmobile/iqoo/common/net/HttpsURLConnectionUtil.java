package com.bbkmobile.iqoo.common.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

public class HttpsURLConnectionUtil {


    private MyX509TrustManager xtm = new MyX509TrustManager();

    private MyHostnameVerifier hnv = new MyHostnameVerifier();
    private static HttpsURLConnectionUtil connection = null;
    
    public static HttpsURLConnectionUtil getInstance(){
        if(connection == null){
            connection = new HttpsURLConnectionUtil();
        }
        return connection;
    }
    
    private HttpsURLConnectionUtil() {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS"); //或SSL
            X509TrustManager[] xtmArray = new X509TrustManager[] {xtm};
            sslContext.init(null, xtmArray, new java.security.SecureRandom());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        if (sslContext != null) {
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        }
        HttpsURLConnection.setDefaultHostnameVerifier(hnv);
    }

    public String responseHttps(String url) {
        HttpsURLConnection urlCon = null;
        try {
            urlCon = (HttpsURLConnection)(new URL(url)).openConnection();
            urlCon.setDoOutput(true);
            //urlCon.setRequestMethod("POST");
            //urlCon.setRequestProperty("Content-Length", "1024");
            urlCon.setUseCaches(false);
            urlCon.setDoInput(true);
            urlCon.getOutputStream().write("request content".getBytes("utf-8"));
            urlCon.getOutputStream().flush();
            urlCon.getOutputStream().close();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
            String line;
            StringBuffer sb = new StringBuffer();
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String responseHttp(String url) {
        HttpURLConnection urlCon = null;
        try {
            urlCon = (HttpURLConnection)(new URL(url)).openConnection();
            urlCon.setDoOutput(true);
            //urlCon.setRequestMethod("POST");
            //urlCon.setRequestProperty("Content-Length", "1024");
            urlCon.setUseCaches(false);
            urlCon.setDoInput(true);
            urlCon.getOutputStream().write("request content".getBytes("utf-8"));
            urlCon.getOutputStream().flush();
            urlCon.getOutputStream().close();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
            String line;
            StringBuffer sb = new StringBuffer();
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
