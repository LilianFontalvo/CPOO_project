package core.mains;

import core.models.SolarPlant;
import core.models.WindPlant;
import core.weatherModels.CloudCover;
import core.weatherModels.Sunlight;
import core.weatherModels.Wind;

/**
 * MainWeather
 * 
 * Cette classe à vocation à vérifier la bonne compilation unitaire des classes, à tester plusieurs scénarios, 
 * à la combiner entre eux...
 */
public class MainWeather {
    public static void main(String[] args) {
        
        Sunlight sunlight = new Sunlight(1678, 12, 12000);
        System.out.println("L'énergie solaire de la journée est : " + sunlight.getAveragePowerProductionOfTheDay());
        System.out.println("L'énergie solaire de la minute est : " + sunlight.getAveragePowerProductionOfTheMinute());

        System.out.println();

        Wind wind = new Wind(1234, 256, 3000) ; 
        System.out.println("L'énergie du vent de la journée est : " +wind.getAveragePowerProductionOfTheDay());
        System.out.println("L'énergie du vent de la minute est : " +wind.getAveragePowerProductionOfTheMinute());

        System.out.println();

        CloudCover cloud = new CloudCover(1678, 12) ;
        System.out.println("Le coefficient de couverture nuageuse de la journée est : " + cloud.getAverageRatioOfCloudCoverOfTheMinute());
        System.out.println("Le coefficient de couverture nuageuse de la minute est : " + cloud.getRateOfCloudCoverInTheDay());

        System.out.println();

        SolarPlant solarPlant = new SolarPlant() ;
        System.out.println("L'énergie produite par la centrale solaire sur la journée est : " + solarPlant.getPowerProdMin(1678, 12));
        System.out.println("L'énergie produite par la centrale solaire sur l'année est : " + solarPlant.getProdDay(12));

        System.out.println();

        WindPlant windPlant = new WindPlant() ;
        System.out.println("L'énergie produite par la centrale éolienne sur la journée est : " + windPlant.getPowerProdMin(1234, 256));
        System.out.println("L'énergie produite par la centrale éolienne sur l'année est : " + windPlant.getProdDay(256));
    }
}
