package symulatingPopulation;

import java.util.Scanner;

public class GeneticAlgorithm 
{
    public static void main(String[] args) 
    {
       Scanner input = new Scanner(System.in);
       Populations example = new Populations();
       int numOfPopulations;
       
       System.out.println("Enter number of populations to simulate");
       numOfPopulations = input.nextInt();
       example.startTime(numOfPopulations);
    }
}
