package com.example.asiaCountrylist;
import androidx.room.Room;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Countries> countries;
    private static String JSON_URL="https://restcountries.eu/rest/v2/region/asia";
    CountryAdapter countryAdapter;
    public static MyAppDatabase myAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.countryList);
        countries=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        countryAdapter =new CountryAdapter(MainActivity.this,countries);
        recyclerView.setAdapter(countryAdapter);
        myAppDatabase= Room.databaseBuilder(MainActivity.this,MyAppDatabase.class,"countrydb")
                .fallbackToDestructiveMigration().allowMainThreadQueries().build();

        //Check Internet Connection
        if(Internet.connectionAvailable(MainActivity.this)==true)
        {
            Toast.makeText(this,"Internet Connection Available",Toast.LENGTH_SHORT).show();
            deleteDataFromRoom();
            ExtractCountry();
        }
        else {
            Toast.makeText(this,"Internet Connection Not Available",Toast.LENGTH_SHORT).show();
            getDataFromRoom();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mymenu = getMenuInflater();
        mymenu.inflate(R.menu.delete_room_databse, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                deleteDataFromRoom();
                Toast.makeText(MainActivity.this,"Room Database Deleted",Toast.LENGTH_LONG).show();
                break;
        }
        return false;
    }

    private void ExtractCountry(){
        RequestQueue queue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(countries!=null)
                countries.clear();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Countries country = new Countries();
                        country.setName(jsonObject.getString("name").toString());
                        country.setCapital(jsonObject.getString("capital").toString());
                        country.setRegion(jsonObject.getString("region").toString());
                        country.setFlag_URL(jsonObject.getString("flag").toString());
                        country.setSubRegion(jsonObject.getString("subregion").toString());
                        country.setPopulation(jsonObject.getString("population").toString());
                        String Language="";
                        JSONArray languageArray=new JSONArray(jsonObject.getString("languages"));
                        for(int j=0;j<languageArray.length();j++){
                            JSONObject jsonObject1=languageArray.getJSONObject(j);
                            Language+=jsonObject1.getString("name")+" , ";
                        }
                        country.setLanguages(Language);
                        String Border="";
                        JSONArray borderArray=new JSONArray(jsonObject.getString("borders"));
                        for(int j=0;j<borderArray.length();j++){
                            Border+=borderArray.getString(j)+" , ";
                        }
                        country.setBorders(Border);
                        countries.add(country);
                        countryAdapter.notifyDataSetChanged();
                        //Storing in Romm Database
                        Country countryDB=new Country();
                        countryDB.setName(jsonObject.getString("name"));
                        countryDB.setCapital(jsonObject.getString("capital"));
                        countryDB.setRegion(jsonObject.getString("region"));
                        countryDB.setSubRegion(jsonObject.getString("subregion"));
                        countryDB.setFlagURL(jsonObject.getString("flag"));
                        countryDB.setPopulation(jsonObject.getString("population"));
                        countryDB.setLanguage(Language);
                        countryDB.setBorder(Border);
                        myAppDatabase.myDao().addCountry(countryDB);
//                        Toast.makeText(MainActivity.this,"Add Successful",Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag","onErrorResponse : "+error.getMessage());
            }
        });
        queue.add(jsonArrayRequest);
    }

    private void getDataFromRoom(){
        if(countries!=null)
            countries.clear();
        List<Country> countryList=myAppDatabase.myDao().getCountry();
        for(Country C:countryList){
            Countries country = new Countries();
            country.setName(C.getName());
            countries.add(country);
            countryAdapter.notifyDataSetChanged();
        }
    }

    private void deleteDataFromRoom()
    {
        if(myAppDatabase.myDao().getCountry()!=null) {
            List<Country> countryListDelete = new ArrayList<>();
            List<Country> countryList = myAppDatabase.myDao().getCountry();
            countryListDelete.addAll(countryList);
            myAppDatabase.myDao().deleteCountry(countryListDelete);
        }
    }
}