package utility;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utils {
	public static String getInputFromUser()
	{
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
	public static int getInputFromConsole()
	{
		Scanner scanner = new Scanner(System.in);
		int number = -1;
		try
		{
			number = scanner.nextInt();
		}
		catch(InputMismatchException e)
		{
			
		}
		return number;
	}

}
