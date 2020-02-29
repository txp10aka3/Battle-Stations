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
			if(board[moved[0]][moved[1]] == 3)
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
	public int getShipStrength(int ship)
	{
		if(ship==1)
		{
			return(ship1Strength);
		}
		if(ship==2)
		{
			return(ship2Strength);
		}
		return -1;
	}
	public void setShipStrength(int ship, int s)
	{
		if(ship==1)
		{
			ship1Strength = s;
		}
		if(ship==2)
		{
			ship2Strength =s;
		}
	}
	public int[][] getShipRkts(int ship)
	{
		if(ship==1)
		{
			return(ship1Rkts);
		}
		if(ship==2)
		{
			return(ship2Rkts);
		}
		return(null);
	}
	public void setShipRkts(int ship, int[][] rkts)
	{
		if(ship==1)
		{
			ship1Rkts = rkts;
		}
		if(ship==2)
		{
			ship2Rkts = rkts;
		}
	}
	
	public boolean fire(int ship)
	{
		System.out.print("firing");
		boolean hit = true;
		int[] ship1P = getShipPosition(1);
		int[] ship2P = getShipPosition(2);
		int[] distance = new int[2];
		int rkt = 0;
		int directionFired = 0;
		int sidehit = 10;
		
		if(ship == 1)
		{
			System.out.print("firing1");
			distance[0] = ship1P[0]-ship2P[0];
			distance[1] = ship1P[1]-ship2P[1];
			
			if(Math.abs(distance[0])>Math.abs(distance[1]))
			{
				if(distance[0]>0)
				{
					directionFired = 0;
				}
				else
				{
					directionFired = 2;
				}
				
			}
			if(Math.abs(distance[0])==Math.abs(distance[1]))
			{
				directionFired = ship1R;
			}
			else
			{
				if(distance[1]>0)
				{
					directionFired = 3;
				}
				else
				{
					directionFired = 1;
				}
			}
			if(modulo(ship1R+2,4) == directionFired) //ship facing wrong way
			{
				if(ship1Rkts[0][0] == 2 || ship1Rkts[1][0] == 2)
				{
					rkt = 2;
				}
				else
				{
					System.out.print("Wrong way");
					distance[0]+=4;
					rkt = 0;
				}
			}
			int i;
			for(i = 0; i<2; i++)
			{
				if(ship1Rkts[i][0]==rkt)
				{
					ship1Rkts[i] = new int[]{10,0,0,0};
					i+=10;
				}
			}
			if(i<=3)
			{
				System.out.println("Nothing Fired");
				return(false);
			}
			
			if(ship1Scanners>=ship2Stealth+Math.abs(distance[0])+Math.abs(distance[1]))
			{
				//good
				System.out.print("Locked on to ship2");
				System.out.print(""+(ship2Stealth+Math.abs(distance[0])+Math.abs(distance[1])));
				
				if(ship2R == directionFired)
				{
					sidehit = 2;
				}
				else if(modulo(ship2R+2, 4) == directionFired)
				{
					sidehit = 0;
				}
				else if(ship2R%2 != 0)
				{
					sidehit = modulo(ship2R+directionFired,4);
				}
				else if(ship2R%2 == 0)
				{
					sidehit = modulo(ship2R-directionFired,4);
				}
				
					
				if(ship1Strength>ship2Shields[sidehit])
				{
					//good
					System.out.println("Ship2 hit!. Side:"+sidehit);
				}
				else
				{
					hit = false;
				}
			}
			else 
			{
				hit = false;
				System.out.print("Failed to lock on");
			}
			
	
			
		}
		
		if(ship == 2) //needs to be updated to match ship 1
		{
			distance[0] = ship2P[0]-ship1P[0];
			distance[1] = ship2P[1]-ship1P[1];
			
			if(Math.abs(distance[0])>Math.abs(distance[1]))
			{
				if(distance[0]>0)
				{
					directionFired = 0;
				}
				else
				{
					directionFired = 2;
				}
				
			}
			if(Math.abs(distance[0])==Math.abs(distance[1]))
			{
				directionFired = ship2R;
			}
			else
			{
				if(distance[1]>0)
				{
					directionFired = 3;
				}
				else
				{
					directionFired = 1;
				}
			}
			if(modulo(ship2R+2,4) == directionFired)
			{
				distance[0]+=4;
			}
			
			if(ship2Scanners>ship1Stealth)
			{
				//good
				System.out.print("Locked on to ship1");
			}
			else 
			{
				hit = false;
			}
			
	
			if(ship1R == directionFired)
			{
				sidehit = 2;
			}
			else if(modulo(ship1R+2, 4) == directionFired)
			{
				sidehit = 0;
			}
			else if(ship1R%2 != 0)
			{
				sidehit = modulo(ship1R+directionFired,4);
			}
			else if(ship1R%2 == 0)
			{
				sidehit = modulo(ship1R-directionFired,4);
			}
			
				
			if(ship2Strength>ship1Shields[sidehit])
			{
				//good
				System.out.println("Ship1 hit! Side:"+sidehit);
			}
			else
			{
				hit = false;
			}
		}
		
		if(hit == false)
		{
			System.out.println("You missed");
		}
		return(hit);
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
