import com.domaciukollekce5.houseplants.PlantException;
import com.domaciukollekce5.houseplants.PlantManager;
import com.domaciukollekce5.houseplants.Settings;

public class HousePlants {
    public static void main(String[] args) {

        PlantManager plantManager = new PlantManager();
        try {plantManager.loadDataPlantsFromFilePM(Settings.fileNameS(), Settings.delimiterS());}
        catch (PlantException e) {
            System.err.println("Nepodařilo se načíst data ze souboru " + e.getLocalizedMessage());

            throw new RuntimeException(e);
        }


    }
}