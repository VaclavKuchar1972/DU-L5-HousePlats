import com.domaciukollekce5.houseplants.Plant;
import com.domaciukollekce5.houseplants.PlantException;
import com.domaciukollekce5.houseplants.PlantManager;
import com.domaciukollekce5.houseplants.Settings;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HousePlants {
    public static void main(String[] args) throws PlantException {

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
        System.out.println("Surová data ze VSTUPNÍHO souboru DB-ListOfPlantsPrimary.txt:");
        for (Plant plant : plantListPM) {Settings.printPlantsComputerOutput(plant);}

        System.out.println();
        System.out.println("Informace o zálivce dle zadání domácího úkolu v bodě 13:");
        for (Plant plant : plantListPM) {Settings.printPlantsComputerOutput(plant);}


        // Ošetření zadávání frekvence zálivky dle bodu 6 domácího úkolu (3. část)
        // + Ošetření zadávání datumu zálivky dle bodu 7 domácího úkolu (3. část)
        // + část bodu 14 (přidání dvou květin)
        try {
            plantListPM.add(new Plant("Jahodník", "na zábradlí balkónu", LocalDate.now(),
                    LocalDate.now(),
                    3));
            plantListPM.add(new Plant("Mochíto Máta", "na balkóně", LocalDate.now(),
                    LocalDate.now(), 2));
        }
        catch (PlantException e) {
            System.err.print("Nastala chyba při vytváření nových rostlin" + e.getLocalizedMessage());
        }


        System.out.println();
        System.out.println("Aktualizovaný seznam rostlin po přidání dvou rostlin dle zadání domácího úkolu v bodě 14:");
        for (Plant plant : plantListPM) {Settings.printPlantsPeopleDateOutput(plant);}

        plantListPM.removeIf(plant -> plant.getPlantNameP().equals("Sukulent v koupelně"));
        System.out.println();
        System.out.println
                ("Aktualizovaný seznam rostlin po odebrání jedné rostliny dle zadání domácího úkolu v bodě 14:");
        for (Plant plant : plantListPM) {Settings.printPlantsPeopleDateOutput(plant);}

        // Uložení aktualizovaného seznamu rostlin do nového souboru dle bodu 15 domácího úkolu
        System.out.println();
        try {
            plantManager.saveDataPlantsToNewFilePM(Settings.fileNameAfterChangesS(), plantListPM);
            System.out.println("Aktualizovaný seznam rostlin byl uložen do souboru: "
                    + Settings.fileNameAfterChangesS());
        } catch (PlantException e) {
            System.err.println("Chyba při ukládání dat: " + e.getMessage());
            throw new RuntimeException(e);
        }

        // Naačtení ze souboru a vizuální ověření souboru aktualizovaného seznamu rostlin do nového souboru dle bodu
        // 16 domácího úkolu + vizuální ověření na výstupu na obrazovku
        try {plantManager.loadDataPlantsFromFilePM(Settings.fileNameAfterChangesS(), Settings.delimiterS());}
        catch (PlantException e) {
            System.err.println("Nepodařilo se načíst data ze souboru " + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
        System.out.println();
        System.out.println("Surová data z VÝSTUPNÍHO souboru DB-ListOfPlantsAfterChanges.txt:");
        for (Plant plant : plantListPM) {Settings.printPlantsComputerOutput(plant);}

    }

}
