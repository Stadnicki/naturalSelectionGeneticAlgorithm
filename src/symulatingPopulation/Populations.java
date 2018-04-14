package symulatingPopulation;
import java.util.Random;

public class Populations
{
    Person[] currPopulation = new Person[6];        // selected parents
    Person[] prevPopulation = new Person[6];        // input population
    Person[] childrenPopulation = new Person[6];    // children
    int prevSum;
    
    
    public Populations()
    {
         for(int i = 0; i < 6; i++)
         {
            prevPopulation[i] = new Person();
            currPopulation[i] = new Person(); 
            childrenPopulation[i] = new Person(); 
         }
    }
    
    public int prevGenSum()
    {
        int tempSum = 0;
        for(int j = 0; j < 6; j++)
            tempSum += prevPopulation[j].squareVal;
        
        prevSum = tempSum;
        return tempSum;
    }
    
    public void rouletteParents() // choosing parents
    {
        int tempSum, rouletteIndex;
        prevGenSum();
        Random genRand = new Random();
        int roulette;
        for(int j = 0; j < 6; j++)
        {
            rouletteIndex = 0;
            tempSum = 0;
            roulette = genRand.nextInt(prevSum);
            for(int k = 0; k < 6 && tempSum <= roulette; k++)
            {
                tempSum += prevPopulation[k].squareVal;
                rouletteIndex = k;
            }
            currPopulation[j] = prevPopulation[rouletteIndex];
            System.out.println("parent choosed by roulette: "+prevPopulation[rouletteIndex].getBin());
        }
    }
    
    public void crossing()
    {
        Random genRand = new Random();
        int crossIndex;
        String a, b, c;
        for(int j = 0; j < 6; j = j + 2)
        {
            crossIndex = genRand.nextInt(5);
            a = currPopulation[j].getBin().substring(0, crossIndex);
            b = currPopulation[j+1].getBin().substring(crossIndex, currPopulation[j+1].getBin().length());
            c = a + b;
            childrenPopulation[j].setBin(c);
            
            a = currPopulation[j+1].getBin().substring(0, crossIndex);
            b = currPopulation[j].getBin().substring(crossIndex);
            c = a + b;
            childrenPopulation[j+1].setBin(c);
        }
    }
    
    public void childToPrev(int population)
    {
        System.out.println("\nPopulation number: "+population);
        for(int i=0 ; i<6; i++)
        {
            System.out.println(childrenPopulation[i].getBin()+" "+childrenPopulation[i].squareVal);
            prevPopulation [i] = childrenPopulation[i];
        }  
        System.out.println();
    }
    
   
    public void mutation(int gen)
    {
        Random genRand = new Random();
        int mut = genRand.nextInt(6);
        int ind = genRand.nextInt(5);
        char num;
        String temp;
        if(gen % 20 == 0)
        {
            temp = childrenPopulation[mut].getBin();
            num = temp.charAt(ind);
            if(num == '1')
                num = '0';
            else
                num = '1';
            
            temp = temp.substring(0, ind) + num + temp.substring(ind+1, temp.length());
            childrenPopulation[mut].setBin(temp);
        }
    }
    
    public void startTime(int gen)
    {
        for(int i=0; i<gen; i++)
        {
            rouletteParents();
            crossing();
            mutation(gen);
            childToPrev(i+1);
        }
    }
}