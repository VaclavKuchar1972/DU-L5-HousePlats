import com.domaciukollekce5.houseplants.Plant;
import com.domaciukollekce5.houseplants.PlantException;
import com.domaciukollekce5.houseplants.PlantManager;
import com.domaciukollekce5.houseplants.Settings;

import java.time.format.DateTimeFormatter;
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

        System.out.println();
        System.out.println("Informace o zálivce dle zadání domácího úkolu v bodě 13:");
        for (Plant plant : plantListPM) {
            System.out.println("    Jméno rostliny: " + plant.getPlantNameP()
                    + "   Standardní frekvence zalévání ve dnech: " + plant.getPlantNormalWateringFrequencyP()
                    + "   Datum posledního zalítí rostlinky: "
                    + plant.getPlantLastWateringDateP().format(DateTimeFormatter.ofPattern("d.M.yyyy")));
        }

    }
}
