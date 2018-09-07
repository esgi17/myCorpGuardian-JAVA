package pa.Models;


import java.io.*;
import java.net.*;

import javafx.scene.control.Alert;
import org.json.*;


public class Api {

    private static String api_url = "http://127.0.0.1:3000/";

    private static String charset = "UTF-8";

    private static String token = "";

    public static void main(String[] args) throws IOException {

    }

    public static String callAPI(String method, String route, JSONObject body) throws Exception {
        String url = api_url + route;

        String res ="";

        switch( method ) {
            case "GET" :
                res = Api.get(url);
                break;
            case "POST" :
                res = Api.post(url, body);
                break;
            case "PUT" :
                res = Api.put(url, body);
                break;
            case "DELETE" :
                res = Api.delete(url, body);
                break;
            default : System.out.println("ERROR");
        }
        return res;
    }

    public static String get(String url) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization",getToken());

        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader( con.getInputStream() ) );
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append( inputLine );
            }
            in.close();

            String res = response.toString();
            return res;
        }catch (Exception e){
            /*Alert alert = new Alert( Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("API Get Error \n" + e);
            alert.showAndWait();*/
            return "";
        }

    }

    // HTTP POST request
    public static String post(String url, JSONObject body) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        con.setRequestProperty("Authorization", getToken() );
        try {
            // Send post request
            con.setDoOutput( true );
            DataOutputStream wr = new DataOutputStream( con.getOutputStream() );
            wr.writeBytes( body.toString() );
            wr.flush();
            wr.close();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader( con.getInputStream() ) );
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append( inputLine );
            }
            in.close();
            String res = response.toString();
            return res;
        }
        catch(Exception e){
            /*Alert alert = new Alert( Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("API Post Error \n" + e);
            alert.showAndWait();*/
            return "";
        }

    }

// HTTP PUT request
  public static String put(String url, JSONObject body) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
      con.setRequestProperty("Authorization",getToken());

        try {
            // Send put request
            con.setDoOutput( true );
            DataOutputStream wr = new DataOutputStream( con.getOutputStream() );
            wr.writeBytes( body.toString() );
            wr.flush();
            wr.close();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader( con.getInputStream() ) );
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append( inputLine );
            }
            in.close();

            String res = response.toString();
            return res;
        }
        catch (Exception e){
            /*Alert alert = new Alert( Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("API Put Error\n" + e);
            alert.showAndWait();*/
            return "";
        }
    }

    // HTTP DELETE request
    public static String delete(String url, JSONObject body) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("DELETE");
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        con.setRequestProperty("Authorization",getToken());

        try {
            // Send put request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(body.toString());
            wr.flush();
            wr.close();
            BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String res = response.toString();
            return res;
        }catch (Exception e){
            Alert alert = new Alert( Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("API Delete Error\n" + e);
            alert.showAndWait();
            return "";
        }
    }


    public static Boolean checkToken() {
        // Verification Token API
        JSONObject empty = new JSONObject();

        try{
            String res = callAPI("GET", "",empty);
            if(res.equalsIgnoreCase("")){
                Alert alert = new Alert( Alert.AlertType.ERROR);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Token Error");
                alert.showAndWait();
                return false;
            }
            else {
                return true;
            }
        }catch (Exception e) {
            return false;
        }
    }

    public static String getApi_url() {
        return api_url;
    }

    public static void setApi_url(String api_url) {
        Api.api_url = api_url;
    }

    public static String getToken() {
        return token;
    }


    public static void setToken(String token) {
        Api.token = token;
    }
}
