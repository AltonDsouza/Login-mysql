package com.example.alton.loginphpmysql;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

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

public class BackgroundWorker extends AsyncTask<String, Void, String> {

    AlertDialog alertDialog;
    Context context;
    public BackgroundWorker(Context ctx)
    {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        //the (first) parameter you passed while calling BackgroundWorker class.
        String type = params[0];
        //(You can try 10.0.2.2) If that does not work , fill in your machine ip addr(where apache2 is active).
        String url = "http://your_ip_addr/login.php";
        if(type == "login")
        {
            //the (second) parameter you passed while calling BackgroundWorker class.
            String username = params[1];
            String password = params[2];//third parameter
            try {
                URL url1 = new URL(url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                //Key Value
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")
                        +"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String line, result = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
       alertDialog = new AlertDialog.Builder(context).create();
       alertDialog.setMessage(result);//string response that comes from the php file
       alertDialog.show();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // do something here if you want
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
