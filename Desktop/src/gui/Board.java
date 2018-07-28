package gui;


public class Board
{
	private int[][] board;
	private int ship1R;
	private int ship2R;
	private int size;
	
	public Board(int s)
	{
		size = s;
		board = new int[size][size];
		for(int y = 0; y<board.length; y++)
		{
			for(int x = 0; x<board[y].length; x++)
			{
				board[y][x] = 0;
			}
		}
		board[0][size-1] = 2;
		board[size-1][0] = 1;
		ship1R = 0;
		ship2R = 2;
	}
	
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
		board[moved[0]][moved[1]] = ship;
		
		if(ship == 1) {ship1R = shipR;}
		if(ship == 2) {ship2R = shipR;}
	}
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
	
	public int getSize()
	{
		return(size);
	}
	public int getBoardAtCoordinates(int y, int x)
	{
		return(board[y][x]);
	}
	public int modulo(int n,int m)
	{
		return(((n%m)+m)%m);
	}
}
