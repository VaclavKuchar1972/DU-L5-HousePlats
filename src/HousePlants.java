import com.domaciukollekce5.houseplants.Plant;
import com.domaciukollekce5.houseplants.PlantException;
import com.domaciukollekce5.houseplants.PlantManager;
import com.domaciukollekce5.houseplants.Settings;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HousePlants {
    public static void main(String[] args) {

        PlantManager plantManager = new PlantManager();

        try {plantManager.loadDataPlantsFromFilePM(Settings.fileNamePrimaryS(), Settings.delimiterS());}
        catch (PlantException e) {
            System.err.println("Nepodařilo se načíst data ze souboru " + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }

        // Toto musí být až po natažení dat, jinak je list prázdný,
        // narozdíl od ostatních tříd se Main evidentně chová striktně chronologicky!!! (to je pro mě)
        List<Plant> plantListPM = plantManager.getPlantListPM();

        System.out.println();
        System.out.println("Surová data ze vstupního souboru DB-ListOfPlantsPrimary.txt:");
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

        plantListPM.add(new Plant("Jahodník", "na zábradlí balkónu", LocalDate.now(), LocalDate.now(),
                3));
        plantListPM.add(new Plant("Mochíto Máta", "na balkóně", LocalDate.now(), LocalDate.now(),
                2));
        System.out.println();
        System.out.println("Aktualizovaný seznam rostlin po přidání dvou rostlin dle zadání domácího úkolu v bodě 14:");
        for (Plant plant : plantListPM) {Settings.printPlantDetails(plant);}

        plantListPM.removeIf(plant -> plant.getPlantNameP().equals("Sukulent v koupelně"));
        System.out.println();
        System.out.println
                ("Aktualizovaný seznam rostlin po odebrání jedné rostliny dle zadání domácího úkolu v bodě 14:");
        for (Plant plant : plantListPM) {Settings.printPlantDetails(plant);}

        // Uložení aktualizovaného seznamu rostlin do nového souboru dle bodu 15 domácího úkolu 1.část
        System.out.println();
        try {
            plantManager.saveDataPlantsToNewFilePM(Settings.fileNameAfterChangesS(), plantListPM);
            System.out.println("Aktualizovaný seznam rostlin byl uložen do souboru: " + Settings.fileNameAfterChangesS());
        } catch (PlantException e) {
            System.err.println("Chyba při ukládání dat: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

}
