package multiplication;

import java.util.Scanner;                //to input from the user


public class Multiplication {

    
    public static void main(String[] args)
     {
        int l1=0,l2=0;                   //length of two numbers are initialized
        int k =0,m=0;                        
        int arr1[]=new int[100];         
        int arr2[]=new int[100];
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter multiplicand");
        int num1=sc.nextInt();           //num1 is the multiplicand
        System.out.println("Enter multiplier");
        int num2=sc.nextInt();           //num2 is the multiplier
        
        //Loop to enter digits of the multiplicand in array one by one
        while(num1>0)
         {
            arr1[k]=num1%10;             
            num1=num1/10;
            l1++;
            k++;
         }
        
        //Loop to enter digits of the multiplier in array one by one
        while(num2>0)
         {
            arr2[m]=num2%10;             
            num2=num2/10;
            l2++;
            m++;
         }
        
        //Loop to reverse the array1 as number is entered in reverse order
        System.out.println("array1 is");
        for(int i = 0; i < l1 / 2; i++)   
        {   
            int temp = arr1[i];
           arr1[i] = arr1[l1 - i - 1];
           arr1[l1 - i - 1] = temp;
        }
        for(int i=0;i<l1;i++)
         {
             System.out.println(arr1[i]); 
         }
        
        //Loop to reverse the array2 as number is entered in reverse order
        System.out.println("array2 is");   
        for(int i = 0; i < l2 / 2; i++)
        {   
           int temp1 = arr2[i];
           arr2[i] = arr2[l2 - i - 1];
           arr2[l2 - i - 1] = temp1;
        }
        for(int i=0;i<l1;i++)
         {
             System.out.println(arr2[i]); 
         }
        
        int resultLength = l1+l2;
		int[] result = new int[resultLength];
		
    //Using these 2 loops we are creating the rectangular matrix of dim l1*l2 and then splitting every cell into 2 parts
                for (int i = 0; i < l1; i++) {
	           for (int j = 0; j < l2; j++) {
	              result[i + j + 1] += (arr1[i])*(arr2[j]) % 10;
		      result[i + j] += ((arr1[i]))*(arr2[j]) / 10;
	           }
		}
		int product=0;
		//loop where we are adding numbers along diagonals based on its index value
                for (int i = 0; i <resultLength; i++) {
			int index=resultLength-i-1;
			while(index>=1)
			{
				result[i]=result[i]*10;
				index--;
			}
			product+=result[i];
		}
		System.out.println("Final product is "+product);
    }
    
}
