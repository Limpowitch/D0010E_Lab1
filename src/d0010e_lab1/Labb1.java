package d0010e_lab1;

import java.util.Scanner;

public class Labb1{
	
	public static void main(String[] args){
		int a0;
		int n;
	    Scanner scan = new Scanner(System.in);
	    while(true) {
	    	try {
	        	String newline = System.lineSeparator();
	            System.out.print(newline + "Skriv in ett heltal större än noll: ");
	            a0 = scan.nextInt();
	            if (a0 <= 0) {
	                throw new IllegalArgumentException("Fel: Ej ett heltal > 0");
	            }
	            System.out.println("Du skrev in " + a0);
	            System.out.print(newline + "Vilken task vill du köra?: ");
	            n = scan.nextInt();
	            switch(n) {
	            case(1):
	            	task1(a0);
	            	break;
	            case(2):
	            	task2(a0);
            		break;
	            case(3):
	            	task3(a0);
	        		break;
	            case(4):
	            	task4();
	        		break;
	            case(6):
	            	task6(a0);
	        		break;
	            case(8):
	            	task8(1.5, a0);
	        		break;
	            case(9):
	            	task9(1.5, a0);
	        		break;
        		default:
        			System.out.print("Ej giltigt alternativ");
	            }
	        } catch (IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	            scan.close();
	        } catch (Exception e) {
	            System.out.println("Något gick fel: " + e.getMessage());
	        }
	    }
	}
	
	//task 1
	public static void task1(int a0) {
		while(a0 != 1) {
	        a0 = Lifelength.f1(a0);
	        System.out.print(a0 + ", ");
		}
	}
	
	//task 2
	public static void task2(int a0) {
        System.out.println("f1 =" + Lifelength.f1(a0) + 
				"\nf2 = " + Lifelength.f2(a0) +
				"\nf4 = " + Lifelength.f4(a0) +
				"\nf8 = " + Lifelength.f8(a0) +
				"\nf16 = " + Lifelength.f16(a0) +
				"\nf32 = " + Lifelength.f32(a0));
	}
		
	//task 3
	public static void task3(int a0) {
        System.out.println(Lifelength.iterateF(a0, 3));
        System.out.println(Lifelength.iterateF(a0, 42));
        System.out.println(Lifelength.iterateF(a0, 7));
	}
	
	//task 4
	public static void task4() {
        	String newline = System.lineSeparator();
        	for (int i = 1; i <= 15; i++) {
	            System.out.println(newline + Lifelength.intsToString(i, Lifelength.iterLifeLength(i)));
    	}
	}
	
	//task 6
	public static void task6(int a0) {
    	String newline = System.lineSeparator();
    	for (int i = 1; i <= 15; i++) {
            System.out.println(newline + "Lifelength of " + i + " = " + Lifelength.recLifeLength(i));
            a0++;
    	}

	}
	
	//task 8
	public static void task8(double x, int k) {
		System.out.println(Raise.recRaiseHalf(x, k));
	}
	
	//task 9
	public static void task9(double x, int k) {
    	String newline = System.lineSeparator();
		for (int i = 1; i <= 10000; i++) {
			System.out.println("recRaiseHalf result = " + Raise.recRaiseHalf(x, i) + " which took " + Raise.halfcounter + " recurssions");
			System.out.println("recRaiseOne result = " + Raise.recRaiseOne(x, i) + " which took " + Raise.fullcounter + " recurssions");
			System.out.println(newline);
			Raise.halfcounter = 0;
			Raise.fullcounter = 0;
		}
	}
}


class Lifelength {
	
	//task 1
	public static int f1(int a0) {
			if (a0 == 1) {
				return a0;
			} else if (a0 % 2 == 0) {
				return a0/2;
			} else {
				return 3*a0 + 1;
			}
		}
		
	
	//task 2
	public static int f2(int a0)
	{
		return f1(f1(a0));
	}
	public static int f4(int a0)
	{
		return f2(f2(a0));
	}
	public static int f8(int a0)
	{
		return f4(f4(a0));
	}
	public static int f16(int a0)
	{
		return f8(f8(a0));
	}
	public static int f32(int a0)
	{
		return f16(f16(a0));
	}

	
	//task 3
	//should return after x amounts of steps determined by length
	public static int iterateF(int a0, int n) {
		int result = a0;
		for(var i = 0; i<n; i++) {
			result = f1(result);
		}
		return result;
	}
	
	//task 4
	public static int iterLifeLength(int a0) {
		int counter = 0;
		while (a0 > 1) {
			a0 = f1(a0);
			counter++;
		}
		return counter;
	}
	
	public static String intsToString(int X, int Y)
	{
		return "The life length of " + X + " is " + Y + ".";

	}
	
	
	//task 6
	public static int recLifeLength(int a0) {
		if (a0 == 1) {
			return 0;
		}
		return 1 + recLifeLength(f1(a0));
	}

	
}

class Raise{
	static int halfcounter; 
	static int fullcounter;
	//task 8
	public static double recRaiseHalf(double x, int k) {
			halfcounter++;
			if(k == 0)
			{
				return 1.0;
			}
			else if (k%2==0)
			{
				double rekt = recRaiseHalf(x,(k/2));
				x =  rekt * rekt;
			}
			else
			{
				double rekt = recRaiseHalf(x,(k/2));
				x = rekt * x * rekt;
			}
			return x;
	}
	
	public static double recRaiseOne(double x, int k) {
		fullcounter++;
		if (k == 0) {
			x = 1.0;
		}
		else {
			x = x * recRaiseOne(x,k-1);
		}
		return x;
	}
	
}


//Teoretiska frågor
//task 5
/*1 => 0
 * 2 => 1
 * 3 => 7
 * 4 => 2
 * 5 => 5
 * 6 => 8
 * 7 => 16
 * 8 => 3
 * 9 => 19*/
//By looking at the results, we should get a function like this:
//l(1) = 0
//l(x) = l(f1(x)) + 1
//When using the function, we get the same results as when we get in task 4

//task 7
//If we were to enter such a number, we would get in a infinate loop until the program breaks

//task 10
//Since x is static, the runtime will be most impacted by the value K which is dynamic.
//Likewise, since k is the raise value of a exponential function, it will scale faster then a increase in x.

//halfcounter(k) = Math.floor(Math.log10(k)/Math.log10(2) + 2)
//fullcounter(k) = k+1 

//By using the equation above, we get that halfcounter(75000) = FLOOR(LOG(75000, 2) + 1, 1) = 17

	
	



	

