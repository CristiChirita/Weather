package com.nttdata.weather;


import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {
    static String apiKey = "jKoDjpA4XoaueBd8j8LHVDfeJT72ACHR"; //Yes, I know this is awful
                                                               //but this is just a test
    public static void main(String args[]) {
        String string = new HttpClient().resolveRequest(apiKey);
        List<WeatherBean> beans = null;
        List<String> continents = new ArrayList<String>();
        continents.add("Europe");
        continents.add("Asia");
        continents.add("Africa");
        continents.add("America");
        continents.add("Australia");
        continents.add("Pacific");
        continents.add("Indian");
        continents.add("Atlantic");
        if (string != null) {
            beans = new JSONParser().parseJSON(string);
        }
        if (beans != null) {
            TemperaturePrinter printer = new TemperaturePrinter();
            HashSet<String> countryList = new HashSet<String>();
            for (WeatherBean bean : beans) {
                String country = bean.getCountry().getEnglishName();
                countryList.add(country);
            }
            for (String country : countryList) {
                printer.getExtremesPerCountry(beans, country);
            }
            for (String continent : continents) {
                printer.getExtremesPerContinent(beans, continent);
            }
            System.out.println("--------------------------------------");
            printer.getGlobalExtremes(beans);
        }

    }
}
