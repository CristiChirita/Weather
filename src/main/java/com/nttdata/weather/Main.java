package com.nttdata.weather;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Main {
    static String apiKey = "jKoDjpA4XoaueBd8j8LHVDfeJT72ACHR"; //Yes, I know this is awful
                                                               //but this is just a test
    public static void main(String args[]) {
        String string = new HttpClient().resolveRequest(apiKey, "currentconditions");
        String regionData = new HttpClient().resolveRequest(apiKey, "locations");
        List<WeatherBean> beans = null;
        List<RegionBean> regionBeans = null;
        List<String> continents = new ArrayList<String>();
        continents.add("Europe");
        continents.add("Asia");
        continents.add("Africa");
        continents.add("North America");
        continents.add("South America");
        continents.add("Antarctica");
        continents.add("Oceania");
        JSONParser parser = new JSONParser();
        if (string != null) {
            beans = parser.parseJSON(string);
            regionBeans = parser.parseRegionData(regionData);
        }
        if (beans != null) {
            TemperaturePrinter printer = new TemperaturePrinter();
            HashMap<String, ArrayList<WeatherBean>> groupedBeans;
            groupedBeans = parser.groupByContinent(beans, regionBeans);
            printer.getExtremesPerContinent(groupedBeans);
            System.out.println("--------------------------------------");
            printer.getGlobalExtremes(beans);
        }

    }
}
