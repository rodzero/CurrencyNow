package com.currencynowsneer.currencynow;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getJsonString();
    }

    private void getJsonString() {

        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {

                String jsonString = "";
                try {

                    URLConnection urlConnection = new URL("http://developers.agenciaideias.com.br/cotacoes/json").openConnection();

                    InputStream inputStream = urlConnection.getInputStream();

                    BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder total = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        total.append(line);
                    }

                    jsonString = total.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return jsonString;
            }

            @Override
            protected void onPostExecute(String s) {

                try {

                    JSONObject jObj = new JSONObject(s);
                    JSONObject dolarJson = (JSONObject) jObj.get("dolar");
                    String dataAtualizacao = (String) jObj.get("atualizacao");

                    Cotacao cotacaoDolar = new Cotacao(Float.parseFloat(dolarJson.get("cotacao").toString()), Float.parseFloat(dolarJson.get("variacao").toString()));

                    String message = "Dollar to Brazilian Real" +
                            "\nCurrency Quote: " + cotacaoDolar.getCotacao() +
                            "\nVariation: " + cotacaoDolar.getVariacao() +
                            "\nUpdated: " + dataAtualizacao;

                    startService(getIntent().<Intent>getParcelableExtra("SEND_MESSAGE").setAction(message));
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute();


    }
}
