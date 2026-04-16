
package co.ciencias.sortingalgorithms.model;

public class SortingAlgorithms {
    private int[] arr;
    
    public SortingAlgorithms(){
        this.arr =  new int[0];
    }
    
    public SortingAlgorithms(int[] arr){
        this.arr = arr;
    }
    
    public int[] bubbleSort(int[] arr){
        int n = arr.length;
        for(int i=0; i < n - 1; i++){
            for(int j = 0; j< n - i - 1; j++){
                if(arr[j] < arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }
    
    public int[] selectionSort(int[] arr){
        int n = arr.length;
        for(int i =0; i < n-1; i++){
            int min = i;
            int temp = 0;
            for(int j = i; j< n-1; j++){
                if(arr[j] < arr[min]){
                    min = j;
                }
            }
            if(arr[min] < arr[i]){
                temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }    
        }
        return arr;
    }
    
}
