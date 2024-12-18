package practise5;

public class ex1
{
    public static void main(String[] args)
    {
	int     denominator, numerator, ratio = 0;


	numerator   = 5;
	denominator = 0;
	try {
		ratio = numerator / denominator;
		System.out.println("The answer is: "+ratio);
		System.out.println("Done."); // Don't move this line
	}catch(ArithmeticException e){
		System.out.println("Divide by 0");
		e.printStackTrace();
	}	
    }
}
