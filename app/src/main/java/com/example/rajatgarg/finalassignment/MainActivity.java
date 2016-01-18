package com.example.rajatgarg.finalassignment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String JSON_URL = "http://agni.iitd.ernet.in/cop290/assign0/register/";
EditText et1,et2,et3,et4,et5,et6,et7;

    private Button buttonGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGet = (Button) findViewById(R.id.button);
        buttonGet.setOnClickListener(this);
        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
        et5 = (EditText) findViewById(R.id.editText5);
        et6 = (EditText) findViewById(R.id.editText6);
        et7 = (EditText) findViewById(R.id.editText7);

    }

    private void sendRequest(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject hello = new JSONObject(response);
                            int n = hello.getInt("RESPONSE_SUCCESS");
                            Log.e("helloo3", "" + n);
                            if(n==1) {
                                Dialog("Your Registration is Successfull");
                            }
                            else {
                                Dialog("Error While your Registration");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected HashMap<String,String> getParams(){
                HashMap<String,String> params = new HashMap<String, String>();
                params.put("teamname",et1.getText().toString());
                params.put("entry1",et2.getText().toString());
                params.put("name1",et3.getText().toString());
                params.put("entry2",et4.getText().toString());
                params.put("name2",et5.getText().toString());
                params.put("entry3",et6.getText().toString());
                params.put("name3",et7.getText().toString());
                return params;
            }

        };

        requestQueue.add(request);
}

    @Override
    public void onClick(View v) {
        sendRequest();
    }

    private void Dialog(String mymessage){

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(mymessage);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}