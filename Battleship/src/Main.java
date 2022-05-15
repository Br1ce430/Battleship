import java.util.Random;
import javax.swing.JOptionPane;
//Main class which houses all the code, including the main method and other methods to compute various actions, as well as the two map variables.
public class Main {
	
	static int[][] map1 = {
			{1,1,2,2,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,2,1},
			{1,1,1,2,2,1,1,1,2,1},
			{1,1,1,1,1,1,1,1,2,1},
			{1,1,2,1,1,1,1,1,2,1},
			{1,1,2,1,1,1,1,1,1,1},
			{1,1,2,1,1,1,1,1,1,1},
			{1,1,1,1,2,2,2,2,2,1},
			{1,1,1,1,1,1,1,1,1,1},
	};
	
	static int[][] map2 = {
			{1,1,1,1,1,1,1,1,1,1},
			{1,2,2,2,2,2,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,2,1,1,1,1,1},
			{1,1,1,1,2,1,1,1,1,1},
			{1,2,2,1,2,1,1,2,2,1},
			{1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1},
			{1,1,2,2,2,2,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1},
	};
	//This is the main method which runs the game based off of different selections made by the user.
	public static void main(String[] args)
	{
		boolean rico1 = false;
		boolean rico2 = false;
		
		JOptionPane.showMessageDialog(null, "Welcome to Bootleg Battleship!");
		JOptionPane.showMessageDialog(null, "Rows and cols start at 0,0");
		JOptionPane.showMessageDialog(null, "□ = water, ■ = ship, # = hit");
		JOptionPane.showMessageDialog(null, "Enter \"Rico\" as player name to view enemy's board");
		
		String[] options = {"player v player", "player v computer"};
		Object selection = JOptionPane.showInputDialog(null, "Choose an option", "Bootleg Battleship", 3, null, options, null);
		
		if(selection == "player v player")
		{
			String name1 = JOptionPane.showInputDialog(null, "Please enter a name to represent P1");
			String name2 = JOptionPane.showInputDialog(null, "Please enter a name to represent P2");
			if(name1.equalsIgnoreCase("rico"))
				rico1 = true;
			if(name2.equalsIgnoreCase("rico"))
				rico2 = true;
			
			boolean run = true;
			
			while(run)
			{
				if(run)
				{
					fire1(rico1);
					if(!hasShipsLeft2())
					{
						JOptionPane.showMessageDialog(null, "P1 wins!");
						run = false;
					}
				}
				if(run)
				{
					fire2(rico2);
					if(!hasShipsLeft1())
					{
						JOptionPane.showMessageDialog(null, "P2 wins!");
						run = false;
					}
				}
			}
			
		}
		else if(selection == "player v computer")
		{
			String name1 = JOptionPane.showInputDialog(null, "Please enter a name to represent P1");
			if(name1.equalsIgnoreCase("rico"))
				rico1 = true;
			boolean run = true;
			
			while(run)
			{
				if(run)
				{
					fire1(rico1);
					if(!hasShipsLeft2())
					{
						JOptionPane.showMessageDialog(null, "P1 wins!");
						run = false;
					}
				}
				if(run)
				{
					CPUFire();
					if(!hasShipsLeft1())
					{
						JOptionPane.showMessageDialog(null, "P1 has lost");
						run = false;
					}
				}
			}
		}
		else {}
	}
	//This method creates the first map but will show ships if "rico" is imputed.
	public static String printMap1(boolean rico)
	{
		String print = "";
		for(int i = 0; i < map1.length; i++)
		{
			for(int j = 0; j < map1[i].length; j++)
			{
				if(map1[i][j] == 1)
				{
					print += "□ ";
				}
				else if(map1[i][j] == 2 && rico)
				{
					print += "■ ";
				}
				else if(map1[i][j] == 2)
				{
					print += "□ ";
				}
				else if(map1[i][j] == 3)
				{
					print += "# ";
				}
			}
			print += "\n";
		}
		return print;
	}
	//This method creates the second map and functions the same as previous method.
	public static String printMap2(boolean rico)
	{
		String print = "";
		for(int i = 0; i < map2.length; i++)
		{
			for(int j = 0; j < map2[i].length; j++)
			{
				if(map2[i][j] == 1)
				{
					print += "□ ";
				}
				else if(map2[i][j] == 2 && rico)
				{
					print += "■ ";
				}
				else if(map2[i][j] == 2)
				{
					print += "□ ";
				}
				else if(map2[i][j] == 3)
				{
					print += "# ";
				}
			}
			print += "\n";
		}
		return print;
	}
	//This method is executed when the player fires at a ship, which effects map 2.
	public static void fire1(boolean rico)
	{
		JOptionPane.showMessageDialog(null, "P1 board:\n" + printMap1(true));
		JOptionPane.showMessageDialog(null, "P1 turn to fire");
		int row = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the row to fire\nEnemy Board:\n" + printMap2(rico)));
		int col = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the column to fire\nEnemy board:\n" + printMap2(rico)));

		if(map2[row][col] == 1)
			JOptionPane.showMessageDialog(null, "miss!");
		else if(map2[row][col] == 2)
		{
			map2[row][col] = 3;
			JOptionPane.showMessageDialog(null, "hit!\n" + printMap2(rico));
		}
		else if(map2[row][col] == 3)
			JOptionPane.showMessageDialog(null, "you already hit there\n" + printMap2(rico));
	}
	//This method functions the same as the previous, but will effect map 1 instead.
	public static void fire2(boolean rico)
	{
		JOptionPane.showMessageDialog(null, "P2 turn");
		JOptionPane.showMessageDialog(null, "P2 board:\n" + printMap2(true));
		JOptionPane.showMessageDialog(null, "P2 turn to fire");
		int row = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the row to fire\nEnemy board:\n" + printMap1(rico)));
		int col = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the column to fire\nEnemy board:\n" + printMap1(rico)));

		if(map1[row][col] == 1)
			JOptionPane.showMessageDialog(null, "miss!");
		else if(map1[row][col] == 2)
		{
			map1[row][col] = 3;
			JOptionPane.showMessageDialog(null, "hit!\n"  + printMap1(rico));
		}
		else if(map1[row][col] == 3)
			JOptionPane.showMessageDialog(null, "you already hit there");
	}
	//This method simulates a CPU by making a random set of coordinates to fire at a ship if player vs. CPU is selected.
	public static void CPUFire()
	{
		Random rand = new Random();
		
		int row = rand.nextInt(10);
		int col = rand.nextInt(10);
		
		if(map1[row][col] == 1)
			JOptionPane.showMessageDialog(null, "your ships are have not been hit!");
		else if(map1[row][col] == 2)
		{
			map1[row][col] = 3;
			JOptionPane.showMessageDialog(null, "one of your ships has been hit!");
		}
		else if(map1[row][col] == 3)
			JOptionPane.showMessageDialog(null, "the computer launched another attack at the same position!");
	}
	//This method checks if there are ships left on map 1.
	public static boolean hasShipsLeft1()
	{
		boolean toReturn = false;
		for(int i = 0; i < map1.length; i++)
		{
			for(int j = 0; j < map1[i].length; j++)
			{
				if(map1[i][j] == 2)
					toReturn = true;
			}
		}
		return toReturn;
	}
	//This method functions the same as the previous but for map 2 instead.
	public static boolean hasShipsLeft2()
	{
		boolean toReturn = false;
		for(int i = 0; i < map2.length; i++)
		{
			for(int j = 0; j < map2[i].length; j++)
			{
				if(map2[i][j] == 2)
					toReturn = true;
			}
		}
		return toReturn;
	}
}