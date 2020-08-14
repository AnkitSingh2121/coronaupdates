package com.example.coronatracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
private  int positionCountry;
TextView country,cases,active,recovered,todaycases,todaydeaths,critical,deaths;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent=getIntent();
        positionCountry=intent.getIntExtra("position",0);
        getSupportActionBar().setTitle("Detail"+TrackCountries.countryModelList.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        country=findViewById(R.id.countryyy);
        cases=findViewById(R.id.cases);
        active=findViewById(R.id.active);
        recovered=findViewById(R.id.recovered);
        todaycases=findViewById(R.id.tdycase);
        todaydeaths=findViewById(R.id.tdydeaths);
        critical=findViewById(R.id.critical);
        deaths=findViewById(R.id.deaths);

        country.setText(TrackCountries.countryModelList.get(positionCountry).getCountry());
        cases.setText(TrackCountries.countryModelList.get(positionCountry).getCases());
        active.setText(TrackCountries.countryModelList.get(positionCountry).getActive());
        recovered.setText(TrackCountries.countryModelList.get(positionCountry).getRecovered());
        todaycases.setText(TrackCountries.countryModelList.get(positionCountry).getTodaycases());
        todaydeaths.setText(TrackCountries.countryModelList.get(positionCountry).getTodaydeaths());
        critical.setText(TrackCountries.countryModelList.get(positionCountry).getCritical());
        deaths.setText(TrackCountries.countryModelList.get(positionCountry).getDeaths());

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
