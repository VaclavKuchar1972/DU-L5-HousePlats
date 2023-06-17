package com.domaciukollekce5.houseplants;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingFormatWidthException;
import java.util.Scanner;

public class PlantManager {
    // int indexPM;
    // String fileName;
    String plantNamePM = ""; String plantNotePM = "";
    int normalWateringFrequencyPM = 0;
    LocalDate plantLastWateringDatePM = null; LocalDate plantPlantingDatePM = null;

    private List<Plant> plantListPM = new ArrayList<>();

    public void loadDataPlantsFromFilePM (String fileNamePM, String delimiterPM) throws PlantException {
        try (Scanner scannerLoadDataPM = new Scanner(new BufferedReader(new FileReader(fileNamePM)))) {
            while (scannerLoadDataPM.hasNextLine()) {
                String linePM = scannerLoadDataPM.nextLine();
                // System.out.println(linePM);
                // Oddělení jednotlivých dat stažených ze souboru (teď máme tabulátor, kterej se mi vůbec nelíbí)
                String[] itemsPM = linePM.split(delimiterPM);
                if (itemsPM.length < 5) {
                    throw new PlantException("Chyba - na řádku v databázi není dostatečný počet položek. ");
                }
                plantNamePM = itemsPM[0]; plantNotePM = itemsPM[1];
                try {
                    normalWateringFrequencyPM = Integer.parseInt(itemsPM[2]);
                } catch (NumberFormatException e) {
                    new PlantException("Chyba - v databázi není číslo: " + itemsPM[2]);
                }
                try {
                    plantLastWateringDatePM = LocalDate.parse(itemsPM[3]);
                } catch (NumberFormatException e) {
                    new PlantException("Chyba - v databázi není datum: " + itemsPM[3]);
                }
                try {
                    plantPlantingDatePM = LocalDate.parse(itemsPM[4]);
                } catch (NumberFormatException e) {
                    new PlantException("Chyba - v databázi není datum: " + itemsPM[4]);
                }
                Plant newPlantPM = new Plant(plantNamePM, plantNotePM, plantPlantingDatePM, plantLastWateringDatePM,
                        normalWateringFrequencyPM);
            }
        } catch (FileNotFoundException e) {
            throw new PlantException("Soubor " + fileNamePM + "nebyl nalezen! " + e.getLocalizedMessage());
        }
    }




    public void addPlantPM(Plant newPlant) {plantListPM.add(newPlant);}

    public void removePlantPM(Plant plant) {plantListPM.remove(plant);}

    // Proč, když jsem si inicializoval proměnnou (atribut?) indexPM na začátku třídy tedy: "int indexPM"
    // a odmažu to int ze závorek níže, proč to nefachá? Co to má být?
    public Plant getPlantFromIdexPM(int indexPM) {return plantListPM.get(indexPM);}









}

//    if (itemsPM.length < 5) {try {} catch (IllegalArgumentException e) {
//            System.out.println("Chyba - na řádku v databázi není dostatečný počet položek. "
//                   + e.getLocalizedMessage());
//       }
//   }