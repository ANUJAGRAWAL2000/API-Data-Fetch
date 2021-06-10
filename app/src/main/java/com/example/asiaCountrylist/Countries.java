package com.example.asiaCountrylist;

public class Countries {
    private String Name;
    private String Capital;
    private String Flag_URL;
    private String SubRegion;
    private String Region;
    private String Borders;
    private String Population;
    private String Languages;

    public Countries(){

    }

    public Countries(String name, String capital, String flag_URL, String subRegion, String region, String borders, String languages, String population) {
        Name = name;
        Capital = capital;
        Flag_URL = flag_URL;
        SubRegion = subRegion;
        Region = region;
        Borders = borders;
        Languages = languages;
        Population = population;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCapital() {
        return Capital;
    }

    public void setCapital(String capital) {
        Capital = capital;
    }

    public String getFlag_URL() {
        return Flag_URL;
    }

    public void setFlag_URL(String flag_URL) {
        Flag_URL = flag_URL;
    }

    public String getSubRegion() {
        return SubRegion;
    }

    public void setSubRegion(String subRegion) {
        SubRegion = subRegion;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getBorders() {
        return Borders;
    }

    public void setBorders(String borders) {
        Borders = borders;
    }

    public String getLanguages() {
        return Languages;
    }

    public void setLanguages(String languages) {
        Languages = languages;
    }

    public String getPopulation() {
        return Population;
    }

    public void setPopulation(String population) {
        Population = population;
    }
}