
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
    
    public int[] insertionSort(int[] arr){
        int n = arr.length;
        for(int i = 1; i < n; i++){
            int temp = arr[i];
            int j = i - 1;
            while(j >= 0 && arr[j] > temp){
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
        return arr;
    }
    
    public void quickSort(int[] arr, int start, int end){
        if(end <= start) return;
        int pivot = partition(arr, start, end);
        quickSort(arr, start, pivot -1);
        quickSort(arr, pivot+1, pivot -1);

    }
    
    public int partition(int[] arr, int start, int end){
        int pivot = arr[end];
        int i = start - 1;
        for(int j = start; j <= end -1; j++){
            if(arr[j] < pivot){
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        i++;
        int temp = arr[i];
        arr[i] = arr[end];
        arr[end] = temp;
        
        return i;
    }
}
