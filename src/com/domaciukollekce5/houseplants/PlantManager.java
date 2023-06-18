package com.domaciukollekce5.houseplants;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlantManager {
    // int indexPM;
    // String fileName;

    // pomocné proměnné
    String[] itemsPM = new String[0]; int helpLineNumberPM = 0; String linePM = "";

    String plantNamePM = ""; String plantNotePM = "";
    int plantNormalWateringFrequencyPM = 0;
    LocalDate plantLastWateringDatePM = null; LocalDate plantPlantingDatePM = null;

    private List<Plant> plantListPM = new ArrayList<>();

    // Předání kopie seznamu tak, aby ho nikdo zvenčí nemohl nikdo měnit (přikázal lektor Martin)
    public List<Plant> getPlantListPM() {return new ArrayList<>(plantListPM);}

    public void addPlantPM(Plant plant) {plantListPM.add(plant);}
    public void removePlantPM(Plant plant) {plantListPM.remove(plant);}

    // Proč, když jsem si inicializoval proměnnou (atribut?) indexPM na začátku třídy tedy: "int indexPM"
    // a odmažu to int ze závorek níže, proč to nefachá? Co to má být?
    public Plant getPlantFromIdexPM(int indexPM) {return plantListPM.get(indexPM);}

    public void loadDataPlantsFromFilePM (String fileNamePM, String delimiterPM) throws PlantException {
        helpLineNumberPM = 0; linePM = "";
        try (Scanner scannerLoadDataPM = new Scanner(new BufferedReader(new FileReader(fileNamePM)))) {
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
                plantLastWateringDatePM = LocalDate.parse(itemsPM[3]);
                plantPlantingDatePM = LocalDate.parse(itemsPM[4]);
                Plant newPlantPM = new Plant(plantNamePM, plantNotePM, plantPlantingDatePM, plantLastWateringDatePM,
                        plantNormalWateringFrequencyPM);
                plantListPM.add(newPlantPM);
            }
        } catch (FileNotFoundException e) {
            throw new PlantException("Soubor " + fileNamePM + "nebyl nalezen! " + e.getLocalizedMessage());
        } catch (NumberFormatException e) {
            throw new PlantException("Chyba - v databázi není číslo: " + itemsPM[2]
                    + " na řádku: " + helpLineNumberPM + ": " + linePM);
        } catch (DateTimeParseException e) {
            throw new PlantException("Chyba - v databázi není datum: " + itemsPM[3] + " nebo " + itemsPM[4]
                    + " na řádku: " + helpLineNumberPM + ": " + linePM);
        }
    }

}