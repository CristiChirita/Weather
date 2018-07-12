package com.nttdata.weather;

import java.util.*;

public class TemperaturePrinter {
    public void getExtremesPerCountry(List<WeatherBean> beans, String country) {
        WeatherBean minTemp = null;
        WeatherBean maxTemp = null;
        System.out.println("--------------------------------------");
        int count = 0;

        for (WeatherBean bean : beans) {
            if (!bean.getCountry().getEnglishName().equals(country)){
                continue;
            }
            count++;
            if (minTemp == null && maxTemp == null) {
                minTemp = bean; //Initialise variables with first value in country
                maxTemp = bean;
                continue;
            }
            double beanTemperature = Double.parseDouble(bean.getTemperature().getMetric().get("Value"));
            double minTemperature = Double.parseDouble(minTemp.getTemperature().getMetric().get("Value"));
            double maxTemperature = Double.parseDouble(maxTemp.getTemperature().getMetric().get("Value"));
            if (minTemperature > beanTemperature) {
                minTemp = bean;
            }
            if (maxTemperature < beanTemperature) {
                maxTemp = bean;
            }
        }
        System.out.println("Number of entries for " + country + " is " + count);
        if (count == 1) {
            System.out.println("Temperature in " + minTemp.getEnglishName() + ", " + minTemp.getCountry().getEnglishName() + " is " + minTemp.getTemperature().getMetric().get("Value"));
            return;
        }
        System.out.println("Minimum temperature in " + country + " is " + minTemp.getTemperature().getMetric().get("Value") + " in " + minTemp.getEnglishName());
        System.out.println("Maximum temperature in " + country + " is " + maxTemp.getTemperature().getMetric().get("Value") + " in " + maxTemp.getEnglishName());
    }

    public void getGlobalExtremes(List<WeatherBean> beans) {
        WeatherBean minTemp = beans.get(0);
        WeatherBean maxTemp = minTemp;

        for (WeatherBean bean : beans) {
            double beanTemperature = Double.parseDouble(bean.getTemperature().getMetric().get("Value"));
            double minTemperature = Double.parseDouble(minTemp.getTemperature().getMetric().get("Value"));
            double maxTemperature = Double.parseDouble(maxTemp.getTemperature().getMetric().get("Value"));
            if (minTemperature > beanTemperature) {
                minTemp = bean;
            }
            if (maxTemperature < beanTemperature) {
                maxTemp = bean;
            }
        }
        System.out.println("Lowest temperature globally is " + minTemp.getTemperature().getMetric().get("Value") + " in " + minTemp.getEnglishName() + ", " + minTemp.getCountry().getEnglishName());
        System.out.println("Highest temperature globally is " + maxTemp.getTemperature().getMetric().get("Value") + " in " + maxTemp.getEnglishName() + ", " + maxTemp.getCountry().getEnglishName());
    }

    public void getExtremesPerContinent(HashMap<String, ArrayList<WeatherBean>> groupedBeans){

        Iterator it = groupedBeans.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            String continent = pair.getKey().toString();
            ArrayList<WeatherBean> unpackedBeans = (ArrayList) pair.getValue();
            if (unpackedBeans.size() < 1) continue;
            WeatherBean minTemp = unpackedBeans.get(0);
            WeatherBean maxTemp = minTemp;
            System.out.println("--------------------------------------");
            int count = 0;
            for (WeatherBean bean : unpackedBeans) {
                double beanTemperature = Double.parseDouble(bean.getTemperature().getMetric().get("Value"));
                double minTemperature = Double.parseDouble(minTemp.getTemperature().getMetric().get("Value"));
                double maxTemperature = Double.parseDouble(maxTemp.getTemperature().getMetric().get("Value"));
                count++;
                if (minTemperature > beanTemperature) {
                    minTemp = bean;
                }
                if (maxTemperature < beanTemperature) {
                    maxTemp = bean;
                }
            }

            System.out.println("Number of entries for " + continent + " is " + count);
            if (count == 1) {
                System.out.println("Temperature in " + minTemp.getEnglishName() + ", " + minTemp.getCountry().getEnglishName() + ", " + continent + " is " + minTemp.getTemperature().getMetric().get("Value"));
                return;
            }
            System.out.println("Lowest temperature is " + minTemp.getTemperature().getMetric().get("Value") + " in " + minTemp.getEnglishName() + ", " + minTemp.getCountry().getEnglishName());
            System.out.println("Highest temperature is " + maxTemp.getTemperature().getMetric().get("Value") + " in " + maxTemp.getEnglishName() + ", " + maxTemp.getCountry().getEnglishName());
        }
    }
}
