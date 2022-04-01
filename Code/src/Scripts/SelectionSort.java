package Scripts;

public class SelectionSort
{
    public static void selectionSort(String[] arr){
        for (int i = 0; i < arr.length - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < arr.length; j++){

                index = j;//searching for lowest index
            }
            String smallerNumber = arr[index];
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }
    }

    public static void main(String a[]){
        String[] arr1 = {};
        System.out.println("Before Selection Sort");
        for(String i:arr1){
            System.out.print(i+" ");
        }
        System.out.println();

        selectionSort(arr1);//sorting array using selection sort

        System.out.println("After Selection Sort");
        /*for(int i:arr1){
            System.out.print(i+" ");
        }

         */
    }
}