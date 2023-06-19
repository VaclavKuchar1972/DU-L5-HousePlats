package com.domaciukollekce5.houseplants;

import java.time.format.DateTimeFormatter;

public class Settings {
    public static String fileNamePrimaryS() {return "DB-ListOfPlantsPrimary.txt";}
    public static String fileNameAfterChangesS() {return "DB-ListOfPlantsAfterChanges.txt";}
    public static String delimiterS () {return "\t";}

    public static void printPlantsPeopleDateOutput(Plant plant) {
        System.out.println("\t" + plant.getPlantNameP() + "\t" + plant.getPlantNoteP() + "\t"
                + plant.getPlantNormalWateringFrequencyP() + "\t"
                + plant.getPlantLastWateringDateP().format(DateTimeFormatter.ofPattern("d.M.yyyy")) + "\t"
                + plant.getPlantPlantingDateP().format(DateTimeFormatter.ofPattern("d.M.yyyy")));
    }

    public static void printPlantsComputerOutput(Plant plant) {
        System.out.println("\t" + plant.getPlantNameP() + "\t" + plant.getPlantNoteP() + "\t"
                + plant.getPlantNormalWateringFrequencyP() + "\t" + plant.getPlantLastWateringDateP() + "\t"
                + plant.getPlantPlantingDateP());
    }

}
