package com.example.asiaCountrylist;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface MyDao
{
    @Insert
    public void addCountry(Country country);

    @Query("select * from countries")
    public List<Country> getCountry();

    @Delete
    public void deleteCountry(List<Country> countryList);
}
