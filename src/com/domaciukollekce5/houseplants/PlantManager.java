package com.domaciukollekce5.houseplants;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlantManager {
    // POMOCNÝ PROMĚNNÝ JIŽ NIKDY NEAKTIVOVAT NA ZAĆÁTKU PROGRAMU!!!
    // Jejich aktivace (alokace v paměti) patří přímo do dané metody, kde je budu potřebovat, protože po skončení metody
    // se z paměti ihned uvolní.

    private List<Plant> plantListPM = new ArrayList<>();

    // Předání kopie seznamu tak, aby ho nikdo zvenčí nemohl nikdo měnit (přikázal lektor Martin)
    public List<Plant> getPlantListPM() {return new ArrayList<>(plantListPM);}

    public void addPlantPM(Plant plant) {plantListPM.add(plant);}
    public void removePlantPM(Plant plant) {plantListPM.remove(plant);}

    public Plant getPlantFromIdexPM(int indexPM) {return plantListPM.get(indexPM);}

    public void loadDataPlantsFromFilePM (String fileNamePrimaryPM, String delimiterPM) throws PlantException {
        String[] itemsPM = new String[0]; int helpLineNumberPM = 0; String linePM = "";
        String plantNamePM = ""; String plantNotePM = "";
        int plantNormalWateringFrequencyPM = 0;
        LocalDate plantLastWateringDatePM = null; LocalDate plantPlantingDatePM = null;
        try (Scanner scannerLoadDataPM = new Scanner(new BufferedReader(new FileReader(fileNamePrimaryPM)))) {
            while (scannerLoadDataPM.hasNextLine()) {
                helpLineNumberPM = helpLineNumberPM + 1;
                linePM = scannerLoadDataPM.nextLine();
                // Oddělení jednotlivých dat stažených ze souboru (teď máme tabulátor, kterej se mi vůbec nelíbí)
                itemsPM = linePM.split(delimiterPM);
                if (itemsPM.length != 5) {
                    throw new PlantException(
                            "Chyba - špatný počet položek na řádku: " + helpLineNumberPM + ": " + linePM);
                }
                plantNamePM = itemsPM[0];
                plantNotePM = itemsPM[1];
                plantNormalWateringFrequencyPM = Integer.parseInt(itemsPM[2]);
                // Ošetření zadávání frekvence zálivky dle bodu 6 domácího úkolu
                if (plantNormalWateringFrequencyPM < 1) {
                    throw new PlantException("Chyba - frekvence zálivky je menší než jeden: " + itemsPM[2]
                            + " na řádku: " + helpLineNumberPM + ": " + linePM);
                }
                plantLastWateringDatePM = LocalDate.parse(itemsPM[3]);
                plantPlantingDatePM = LocalDate.parse(itemsPM[4]);

                // Ošetření zadávání data poslední zálivky dle bodu 7 domácího úkolu
                if (plantLastWateringDatePM.isBefore(plantPlantingDatePM)) {
                    throw new PlantException("Chyba - datum poslední zálivky je před vysazením rostliny: "
                            + itemsPM[3] + " a " + itemsPM[4] + " na řádku: " + helpLineNumberPM + ": " + linePM);
                }

                Plant newPlantPM = new Plant(plantNamePM, plantNotePM, plantPlantingDatePM, plantLastWateringDatePM,
                        plantNormalWateringFrequencyPM);
                plantListPM.add(newPlantPM);
            }
        } catch (FileNotFoundException e) {
            throw new PlantException("Soubor " + fileNamePrimaryPM + "nebyl nalezen! " + e.getLocalizedMessage());
        } catch (NumberFormatException e) {
            throw new PlantException("Chyba - v databázi není číslo: " + itemsPM[2]
                    + " na řádku: " + helpLineNumberPM + ": " + linePM);
        } catch (DateTimeParseException e) {
            throw new PlantException("Chyba - v databázi není datum: " + itemsPM[3] + " nebo " + itemsPM[4]
                    + " na řádku: " + helpLineNumberPM + ": " + linePM);
        }
    }
    public void saveDataPlantsToNewFilePM(String fileName, List<Plant> plantList) throws PlantException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Plant plant : plantList) {
                writer.write(plant.getPlantNameP() + "\t" + plant.getPlantNoteP() + "\t"
                        + plant.getPlantNormalWateringFrequencyP() + "\t"
                        + plant.getPlantLastWateringDateP() + "\t"
                        + plant.getPlantPlantingDateP());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new PlantException("Chyba při ukládání dat do souboru: " + e.getMessage());
        }
    }

}