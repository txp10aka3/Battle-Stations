package gui;


public class Board
{
	private int[][] board;
	private int size;
	private int ship1R;
	private int ship2R;
	private int ship1Scanners;
	private int ship2Scanners;
	private int ship1Stealth;
	private int ship2Stealth;
	private int[] ship1Shields;
	private int[] ship2Shields;
	private int[][] ship1Rkts;
	private int[][] ship2Rkts;
	private int ship1Strength;
	private int ship2Strength;
	
	
	
	
	
	public Board(int s)
	{
		size = s;
		board = new int[size][size];
		for(int y = 0; y<board.length; y++)
		{
			for(int x = 0; x<board[y].length; x++)
			{
				board[y][x] = 0;
				if(y==x*2)
				{
					//board[y][x]=3;
				}							
			}
		}
		board[0][size-1] = 2;
		board[size-1][0] = 1;
		ship1R = 0;
		ship2R = 2;
		ship1Scanners = 0;
		ship2Scanners = 0;
		ship1Stealth = 0;
		ship2Stealth = 0;
		ship1Shields = new int[]{0,0,0,0};
		ship2Shields = new int[]{0,0,0,0};
		ship1Rkts = new int[][] {{0,0,0,0},{0,0,0,0}};
		ship2Rkts = new int[][] {{0,0,0,0},{0,0,0,0}};
		ship1Strength = 0;
		ship2Strength = 0;
	}
	
	//ship commands
	public void moveShip(int ship, int[][] movement)
	{
		int shipR = 0;
		if(ship == 1) {shipR = ship1R;}
		if(ship == 2) {shipR = ship2R;}
		int[] initial = getShipPosition(ship); 
		int[] moved = getShipPosition(ship);
		for(int i = 0; i<movement.length; i++)
		{
			if(shipR == 0)
			{
				moved[0] -= movement[i][0];
			}
			if(shipR == 1)
			{
				moved[1] += movement[i][0];
			}
			if(shipR == 2)
			{
				moved[0] += movement[i][0];
				
			}
			if(shipR == 3)
			{
				moved[1] -= movement[i][0];
			}
			moved[1] = modulo(moved[1], size);
			moved[0] = modulo(moved[0], size);
			shipR += movement[i][1];
			shipR = modulo(shipR,4);
		}
		
		board[initial[0]][initial[1]] = 0;
		if(board[moved[0]][moved[1]] != 0)
		{
			if(board[moved[0]][moved[1]] == 1 || board[moved[0]][moved[1]] == 2)
			{
				setShipR(Math.abs(ship-3), getShipR(ship));   //just for testing
				moveShip(Math.abs(ship-3), new int[][] {{1,0}});
			}
			if(board[moved[0]][moved[1]] ==3)
			{
				System.out.println("Crystal");
			}
		}
		board[moved[0]][moved[1]] = ship;
		
		if(ship == 1) {ship1R = shipR;}
		if(ship == 2) {ship2R = shipR;}
	}
	
	//ship info
	public int[] getShipPosition(int ship)
	{
		for(int y = 0; y<board.length; y++)
		{
			for(int x = 0; x<board[y].length; x++)
			{
				if(board[y][x] == ship)
				{
					return(new int[]{y,x});
				}
			}
		}
		return null;
	}
	public int getShipR(int ship)
	{
		if(ship == 1)
		{
			return(ship1R);
		}
		if(ship == 2)
		{
			return(ship2R);
		}
		return(-1);
		
	}
	public void setShipR(int ship, int r)
	{
		if(ship == 1)
		{
			ship1R = r;
		}
		if(ship == 2)
		{
			ship2R = r;
		}
	}
	
	public int getShipScanners(int ship)
	{
		if(ship==1)
		{
			return(ship1Scanners);
		}
		if(ship==2)
		{
			return(ship2Scanners);
		}
		return(-1);
	}
	public void setShipScanners(int ship, int s)
	{
		if(ship==1)
		{
			ship1Scanners = s;
		}
		if(ship==2)
		{
			ship2Scanners = s;
		}
	}
	
	public int getShipStealth(int ship)
	{
		if(ship==1)
		{
			return(ship1Stealth);
		}
		if(ship==2)
		{
			return(ship2Stealth);
		}
		return(-1);
	}
	public void setShipStealth(int ship, int s)
	{
		if(ship==1)
		{
			ship1Stealth = s;
		}
		if(ship==2)
		{
			ship2Stealth = s;
		}
	}
	
	public int[] getShipShields(int ship)
	{
		if(ship==1)
		{
			return(ship1Shields);
		}
		if(ship==2)
		{
			return(ship2Shields);
		}
		return null;
	}
	
	public void setShipShields(int ship, int[] s)
	{
		if(ship==1)
		{
			ship1Shields = s;
		}
		if(ship==2)
		{
			ship2Shields =s;
		}
	}
	
	public boolean fire(int ship)
	{
		boolean hit = false;
		int[] ship1P = getShipPosition(1);
		int[] ship2P = getShipPosition(2);
		int[] distance = new int[2];
		distance[0] = ship1P[0]-ship2P[0];
		distance[1] = ship1P[1]-ship2P[1];
		
		if(ship == 1)
		{
			if(ship1Scanners>ship2Stealth)
			{
				hit = true;
			}
			else 
			{
				hit = false;
			}
			if(distance[0]>distance[1])
				
			if(ship1Strength>ship1Shields[0])
			{
				
			}
		}
		
		return(false);
	}
	
	
	
	//board
	public int getSize()
	{
		return(size);
	}
	public int getBoardAtCoordinates(int y, int x)
	{
		return(board[y][x]);
	}
	
	
	//math
	public int modulo(int n,int m)
	{
		return(((n%m)+m)%m);
	}
	

	
	
	
	
	
	
	
	
	//Redundant/old
	
	public void moveShip2(int[][] movement)
	{
		int[] initial = getShip2Position(); 
		int[] moved = getShip2Position();
		for(int i = 0; i<movement.length; i++)
		{
			if(ship2R == 0)
			{
				moved[0] -= movement[i][0];
			}
			if(ship2R == 1)
			{
				moved[1] += movement[i][0];
			}
			if(ship2R == 2)
			{
				moved[0] += movement[i][0];
				
			}
			if(ship2R == 3)
			{
				moved[1] -= movement[i][0];
			}
			moved[1] = modulo(moved[1], size);
			moved[0] = modulo(moved[0], size);
			ship2R += movement[i][1];
			ship2R = modulo(ship2R,4);
			
		}
		board[initial[0]][initial[1]] = 0;
		board[moved[0]][moved[1]] = 2;
	}
	public int[] getShip2Position()
	{
		for(int y = 0; y<board.length; y++)
		{
			for(int x = 0; x<board[y].length; x++)
			{
				if(board[y][x] == 2)
				{
					return(new int[]{y,x});
				}
			}
		}
		return null;
	}
	public int getShip2R()
	{
		return(ship2R);
	}
	
	public int getShip1xPosition()
	{
		for(int y = 0; y<board.length; y++)
		{
			for(int x = 0; x<board[y].length; x++)
			{
				if(board[y][x] == 1)
				{
					return(x);
				}
			}
		}
		return(-1);
	}
	public int getShip1YPosition()
	{
		for(int y = 0; y<board.length; y++)
		{
			for(int x = 0; x<board[y].length; x++)
			{
				if(board[y][x] == 1)
				{
					return(y);
				}
			}
		}
		return(-1);
	}
}
