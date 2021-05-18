package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView memeRv;
    ArrayList<Meme> memes=new ArrayList<>();
    MemeAdaptor memeAdaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        memeRv=findViewById(R.id.memeRV);
        loadMemes();
    }



    public void loadMemes(){


        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://meme-api.herokuapp.com/gimme/50";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray jsonArray=response.getJSONArray("memes");
                    for (int i=0;i<jsonArray.length();i++){

                        Meme meme=new Meme();
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        meme.setTitle(jsonObject.getString("title"));
                        meme.setUrl(jsonObject.getString("url"));
                        memes.add(meme);

                    }
                    memeAdaptor=new MemeAdaptor(MainActivity.this,memes);
                    memeRv.setAdapter(memeAdaptor);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
    }
}