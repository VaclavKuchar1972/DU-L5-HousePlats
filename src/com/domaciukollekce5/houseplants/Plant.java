package com.domaciukollekce5.houseplants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Plant {

    private String plantNameP = "";
    private String plantNoteP = "";
    private LocalDate plantPlantingDateP;
    private LocalDate plantLastWateringDateP;
    private int plantNormalWateringFrequencyP;

    // Konstruktor 1 - se všemi adtibuty
    public Plant(String plantNameP, String plantNoteP, LocalDate plantPlantingDateP, LocalDate plantLastWateringDateP,
                 int plantNormalWateringFrequencyP) throws PlantException {
        this.plantNameP = plantNameP;
        this.plantNoteP = plantNoteP;
        this.plantPlantingDateP = plantPlantingDateP;
        this.plantLastWateringDateP = plantLastWateringDateP;
        this.setPlantNormalWateringFrequencyP(plantNormalWateringFrequencyP);
    }
    // Konstruktor 2 - "plantNoteP" nastaví jako prázdný řetězec a "plantLastWateringDateP" nastaví jako aktuální datum
    public Plant(String plantNoteP, LocalDate plantLastWateringDateP) throws PlantException {
        this.plantNameP = plantNameP;
        this.plantNoteP = "";
        this.plantPlantingDateP = plantPlantingDateP;
        this.plantLastWateringDateP = LocalDate.now();
        this.setPlantNormalWateringFrequencyP(plantNormalWateringFrequencyP);
    }
    // Konstruktor 3 - "plantNoteP" nastaví jako prázdný řetězec, "plantLastWateringDateP" nastaví jako aktuální datum,
    // "normalWateringFrequencyP" nastaví na hodnotu 7 a "plantPlantingDateP" nastaví jako aktuální datum
    public Plant(String plantNoteP, LocalDate plantPlantingDateP, LocalDate plantLastWateringDateP,
                 int plantNormalWateringFrequencyP) throws PlantException {
        this.plantNameP = plantNameP;
        this.plantNoteP = "";
        this.plantPlantingDateP = LocalDate.now();
        this.plantLastWateringDateP = LocalDate.now();
        this.plantNormalWateringFrequencyP = 7;
    }

    // Přístupová metoda z bodu č.4 domácího úkolu lekce 5
    public String getWateringInfo () {
        return "   Název rostliny: " + plantNameP + "   Datum poslední zálivky: "
                + plantLastWateringDateP.format(DateTimeFormatter.ofPattern("d.M.yyyy"))
                + "   Datum doporučené další zálivky: "
                + plantLastWateringDateP.plusDays(plantNormalWateringFrequencyP)
                .format(DateTimeFormatter.ofPattern("d.M.yyyy"));
    }

    // Všechny přístupové metody
    public String getPlantNameP() {return plantNameP;}
    public void setPlantNameP(String plantNameP) {this.plantNameP = plantNameP;}
    public String getPlantNoteP() {return plantNoteP;}
    public void setPlantNoteP(String plantNoteP) {this.plantNoteP = plantNoteP;}
    public LocalDate getPlantPlantingDateP() {return plantPlantingDateP;}
    public void setPlantPlantingDateP(LocalDate plantPlantingDateP) {this.plantPlantingDateP = plantPlantingDateP;}
    public LocalDate getPlantLastWateringDateP() {return plantLastWateringDateP;}
    public void setPlantLastWateringDateP(LocalDate plantLastWateringDateP) {
        this.plantLastWateringDateP = plantLastWateringDateP;
    }
    public int getPlantNormalWateringFrequencyP() {return plantNormalWateringFrequencyP;}
    public void setPlantNormalWateringFrequencyP(int plantNormalWateringFrequencyP) throws PlantException {
        // Ošetření zadávání frekvence zálivky dle bodu 6 domácího úkolu - 1. část řešení, 2. část je v konstrukotorech
        // a třetí část je v Mainu
        if (plantNormalWateringFrequencyP < 1) {
            throw new PlantException("Chyba - frekvence zálivky je menší než jeden den, její hodnota je nyní: "
                    + plantNormalWateringFrequencyP);
        }
        this.plantNormalWateringFrequencyP = plantNormalWateringFrequencyP;
    }

}