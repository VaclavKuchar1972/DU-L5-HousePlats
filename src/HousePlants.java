import com.domaciukollekce5.houseplants.Plant;
import com.domaciukollekce5.houseplants.PlantException;
import com.domaciukollekce5.houseplants.PlantManager;
import com.domaciukollekce5.houseplants.Settings;

import java.util.List;

public class HousePlants {
    public static void main(String[] args) {

        PlantManager plantManager = new PlantManager();

        try {plantManager.loadDataPlantsFromFilePM(Settings.fileNameS(), Settings.delimiterS());}
        catch (PlantException e) {
            System.err.println("Nepodařilo se načíst data ze souboru " + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }

        // Toto musí být až po natažení dat, jinak je list prázdný,
        // narozdíl od ostatních tříd se Main evidentně chová striktně chronologicky!!! (to je pro mě)
        List<Plant> plantListPM = plantManager.getPlantListPM();

        System.out.println();
        System.out.println("Surová data ze vstupního souboru DB-ListOfPlants.txt:");
        for (Plant plant : plantListPM) {
            System.out.println("\t" + plant.getPlantNameP() + "\t" + plant.getPlantNoteP() + "\t"
                    + plant.getPlantNormalWateringFrequencyP() + "\t" + plant.getPlantLastWateringDateP() + "\t"
                    + plant.getPlantPlantingDateP());
        }

    }
}
