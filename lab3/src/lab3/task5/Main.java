package lab3.task5;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        Chocolate[] chocolates = {
            new Chocolate("Twix", 50),
            new Chocolate("Mars", 45),
            new Chocolate("Snickers", 60)
        };

        Time[] times = {
            new Time(14, 30),
            new Time(9, 15),
            new Time(12, 0)
        };
        
        Sort.bubbleSort(chocolates);
        System.out.println("After Bubble Sort (Chocolates): " + Arrays.toString(chocolates));
        
        Sort.mergeSort(times);
        System.out.println("After Merge Sort (Times): " + Arrays.toString(times));
    }
}

