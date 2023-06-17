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

    // To samý PROČ? Jeko níže


    public void loadDataPlatsFromFilePM (String fileName) {
        try (Scanner loadDataPM = new Scanner(new BufferedReader(new FileReader(fileName)))) {
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }




    public void addPlantPM(Plant newPlant) {plantListPM.add(newPlant);}

    public void removePlantPM(Plant plant) {plantListPM.remove(plant);}

    // Proč, když jsem si inicializoval proměnnou (atribut?) indexPM na začátku třídy tedy: "int indexPM"
    // a odmažu to int ze závorek níže, proč to nefachá? Co to má být?
    public Plant getPlantFromIdexPM(int indexPM) {return plantListPM.get(indexPM);}









}