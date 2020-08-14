package com.example.coronatracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
TextView cases,critical,active,recovered,todaycase,totaldeath,todaydeath,affectedcountries;
ScrollView scroll;
SimpleArcLoader simpleArcLoader;
PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cases=findViewById(R.id.cases);
        critical=findViewById(R.id.critical);
        active=findViewById(R.id.active);
        recovered=findViewById(R.id.recovered);
        todaycase=findViewById(R.id.todaycase);
        totaldeath=findViewById(R.id.totaldeaths);
        todaydeath=findViewById(R.id.todaydeaths);
        affectedcountries=findViewById(R.id.affectedcountries);

        scroll=findViewById(R.id.scroll);
        simpleArcLoader=findViewById(R.id.loader);
        pieChart=findViewById(R.id.piechart);

        fetchdata();
    }
    private void fetchdata(){
        String url="https://corona.lmao.ninja/v2/all/";
        simpleArcLoader.start();
        StringRequest request=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject=new JSONObject(response.toString());

                             cases.setText(jsonObject.getString("cases"));
                             critical.setText(jsonObject.getString("critical"));
                             active.setText(jsonObject.getString("active"));
                             recovered.setText(jsonObject.getString("recovered"));
                             todaycase.setText(jsonObject.getString("todayCases"));
                             totaldeath.setText(jsonObject.getString("deaths"));
                             todaydeath.setText(jsonObject.getString("todayDeaths"));
                             affectedcountries.setText(jsonObject.getString("affectedCountries"));


                             pieChart.addPieSlice(new PieModel("cases",Integer.parseInt(cases.getText().toString()),Color.parseColor("#FDD835")));
                         pieChart.addPieSlice(new PieModel("recovered",Integer.parseInt(recovered.getText().toString()),Color.parseColor("#85792121")));
                         pieChart.addPieSlice(new PieModel("deaths",Integer.parseInt(totaldeath.getText().toString()),Color.parseColor("#F2EE44A1")));
                         pieChart.addPieSlice(new PieModel("active",Integer.parseInt(active.getText().toString()),Color.parseColor("#039BE5")));

       pieChart.startAnimation();
       simpleArcLoader.stop();
       simpleArcLoader.setVisibility(View.GONE);
       scroll.setVisibility(View.VISIBLE);


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void goOnclk(View view) {
        startActivity(new Intent(getApplicationContext(),TrackCountries.class));
    }
}
