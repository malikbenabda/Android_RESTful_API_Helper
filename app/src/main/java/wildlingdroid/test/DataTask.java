package wildlingdroid.test;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by malik on 31-Dec-16.
 */


public class DataTask extends AsyncTask<String, Void, String> {
    private String MY_SERVERNAME = "localhost:8000";
    Context ctx;
    String jsonString;
    String response = "";
    String line = "";

    public static final String USERS_TABLE = "users";
    public static final String PLACES_TABLE = "places";
// add same to all otgher routes



    public static final String USERS_FIND_ALL = "find/all";
    public static final String PLACES_FIND_ALL = "find/all";
// add same to all otgher methods

    /**
     *
     * @param context
     * @param serverAdress
     */
    public DataTask(Context context, String serverAdress) {
        this.ctx = context;
        this.MY_SERVERNAME = serverAdress;
    }

    @Override
    protected void onPreExecute() {

    }

    /**
     *
     * @param params
     *  String tablename = params[0];
     *  String method = params[1];
     * @return
     */
    @Override
    protected String doInBackground(String... params) {
        String tablename = params[0];
        String method = params[1];
        String myURL = MY_SERVERNAME.concat("/").concat(tablename).concat("/").concat(method);


        if (method.equals(USERS_FIND_ALL)) {
//            String email = params[1];
            //          String password = params[2];
            try {
                Log.e("url : ", myURL);
                URL url = new URL(myURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                Log.e("http :  ","connection established");
                    httpURLConnection.setRequestMethod("GET");

                    httpURLConnection.setDoInput(true);

        /*
                OutputStream outputStream = httpURLConnection.getOutputStream();


                //if there r data to pass
                 //  httpURLConnection.setDoOutput(true);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                  outputStream.close();
        */
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();


                return response;



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return response;
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {

    }


}