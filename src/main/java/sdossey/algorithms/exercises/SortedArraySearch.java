package sdossey.algorithms.exercises;

import sdossey.algorithms.datasets.Words;

public class SortedArraySearch 
{
    // public static boolean contains(String[] data, String searchString)
    // {
    //     //Reimplement this method to be faster. 
    //     //It is okay to create other methods that are called,
    //     //and it is okay to use methods of String (such as equals)
    //     for(String value:data)
    //     {
    //         if(searchString.equals(value))
    //         {
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    public static boolean contains(String[] data,
                                   String searchString,
                                   int start,
                                   int end)
    {
        while(start <= end)
        {            
            int middle = (start+end)/2;
            String middleString = data[middle];
            int value=searchString.compareTo(middleString);
            if(value == 0)
            {
                return true;
            }
            else if(value < 0)
            {
                end = middle -1;
            }
            else
            {
                start = middle + 1;                
            }
        }
        return false;
    }

    public static boolean contains(String[] data, String searchString)
    {
        //Implement this!
         int start=0;
         int end=data.length-1;
         return contains(data, searchString, start, end);
    }     

    public static final void main(String[] args)
    {
        String[] data = Words.ENGLISH;   
        String[] checkData = Words.SPANISH;
        
        int counter=0;

        long start = System.currentTimeMillis();
        for( String value : checkData )
        {
            if(contains(data, value))
            {                
                counter += 1;
            }
        }        
        long end = System.currentTimeMillis();        
        System.out.println("There are "+counter+" words shared between our Spanish and English dictionary.");
        System.out.println("It took "+(end-start)+ " milliseconds to do this calculation.");
    }
}
