import com.domaciukollekce5.houseplants.Plant;
import com.domaciukollekce5.houseplants.PlantException;
import com.domaciukollekce5.houseplants.PlantManager;
import com.domaciukollekce5.houseplants.Settings;

import java.util.List;

public class HousePlants {
    public static void main(String[] args) {

        PlantManager plantManager = new PlantManager();
        List<String> listPlantPrimaryLoadedData = plantManager.getListPlantPrimaryLoadedDataPM();


        try {plantManager.loadDataPlantsFromFilePM(Settings.fileNameS(), Settings.delimiterS());}
        catch (PlantException e) {
            System.err.println("Nepodařilo se načíst data ze souboru " + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }

        System.out.println();
        for (String listPlantPrimaryData : listPlantPrimaryLoadedData) {System.out.println(listPlantPrimaryData);}

    }
}
