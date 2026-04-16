package co.ciencias.sortingalgorithms.controller;

import co.ciencias.sortingalgorithms.model.SortingAlgorithms;

public class Controller {
    
    private SortingAlgorithms sortAlg;

    public Controller(){
        sortAlg = new SortingAlgorithms();
    }
    
    public void run(){
        int[] nigga = new int[]{1,2,3};
        int[] sorted = sortAlg.bubbleSort(nigga);
    }
    
}
