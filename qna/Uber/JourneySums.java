// https://docs.google.com/document/d/1blsxLR_L08nVpUZtTc4JNrMnHfPYpLgi6ze9a_ZDejE/edit?tab=t.0
package Uber;
import java.util.*;

public class JourneySums {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        scanner.close();

        // Call the method to calculate the number of even and odd sum journeys
        int[] result = calculateJourneySums(n, a, b);
        System.out.println("Max Number of even sum journeys: " + result[0]);
        System.out.println("Max Number of odd sum journeys: " + result[1]);
    }

    // Method to calculate the number of even and odd sum journeys
    public static int[] calculateJourneySums(int n, int[] a, int[] b) {
        // Write your logic here
        int[][] dpEven = new int[n][2];
        int[][] dpOdd = new int[n][2];
        dpEven[0][0] = a[0]%2==0? 1 : 0;
        dpOdd[0][0] = a[0]%2!=0? 1 : 0;
        dpEven[0][1] = b[0]%2==0? 1 : 0;
        dpOdd[0][1] = b[0]%2!=0? 1 : 0;
        for(int i=1;i<n;i++){
            if(a[i]%2==0){
                dpEven[i][0] = dpEven[i-1][0] + dpEven[i-1][1];
                dpOdd[i][0] = dpOdd[i-1][0] + dpOdd[i-1][1];
            }
            else{
                dpEven[i][0] = dpOdd[i-1][0] + dpOdd[i-1][1];
                dpOdd[i][0] = dpEven[i-1][0] + dpEven[i-1][1];
            }
            if(b[i]%2==0){
                dpEven[i][1] = dpEven[i-1][0] + dpEven[i-1][1];
                dpOdd[i][1] = dpOdd[i-1][0] + dpOdd[i-1][1];
            }
            else{
                dpEven[i][1] = dpOdd[i-1][0] + dpOdd[i-1][1];
                dpOdd[i][1] = dpEven[i-1][0] + dpEven[i-1][1];
            }
        }
        int totalEven = dpEven[n-1][0] + dpEven[n-1][1];
        int totalOdd = dpOdd[n-1][0] + dpOdd[n-1][1];

        return new int[]{totalEven, totalOdd}; // Placeholder return value
    }
}
