package com.domaciukollekce5.houseplants;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlantManager {
    // int indexPM;
    // String fileName;

    private List<Plant> plantListPM = new ArrayList<>();

    // To samý PROČ? Jako níže
    public void loadDataPlantsFromFilePM (String fileNamePM) throws PlantException {
        try (Scanner scannerLoadDataPM = new Scanner(new BufferedReader(new FileReader(fileNamePM)))) {
            while (scannerLoadDataPM.hasNextLine()) {
                String linePM = scannerLoadDataPM.nextLine();
                System.out.println(linePM);


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