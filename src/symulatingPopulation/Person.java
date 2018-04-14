package symulatingPopulation;
import java.util.Random;

public class Person 
{
    private String bin = new String();
    public int dec;
    public int squareVal;
    public Person()
    {
        Random generator = new Random();
        dec = generator.nextInt(31);
        bin = Integer.toBinaryString(dec);
        int i = 5 - bin.length();
        if( i<5 )
        {
            for(int j=0; j<i; j++)
                bin = "0"+bin;
        }
        squareVal = dec*dec;   
    }
    
    public String getBin()
    {
        return bin;
    }
    
    public void setBin(String x)
    {
        bin = x;
        binaryToDec();
    }

       
    public void binaryToDec() 
    {
        char[] numbers = bin.toCharArray();
        Integer result = 0;
        int count = 0;
        for(int i=numbers.length-1;i>=0;i--)
        {
         if(numbers[i]=='1')
             result += (int)Math.pow(2, count);
         
         count++;
        }
        dec = result;
        squareVal = result*result;
    }
}