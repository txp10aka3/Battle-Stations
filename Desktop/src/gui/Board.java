package gui;


public class Board
{
	private int[][] board;
	private int ship1R;
	private int ship2R;
	
	
	public Board()
	{
		board = new int[10][10];
		for(int y = 0; y<board.length; y++)
		{
			for(int x = 0; x<board[y].length; x++)
			{
				board[y][x] = 0;
			}
		}
		board[0][9] = 2;
		board[9][0] = 1;
		ship1R = 0;
		ship2R = 0;
	}
	
	public void moveShip1(int[][] movement)
	{
		int[] initial = getShip1Position(); 
		int[] moved = getShip1Position();
		for(int i = 0; i<movement.length; i++)
		{
			if(ship1R == 0)
			{
				moved[0] -= movement[i][0];
			}
			if(ship1R == 1)
			{
				moved[1] += movement[i][0];
			}
			if(ship1R == 2)
			{
				moved[0] += movement[i][0];
				
			}
			if(ship1R == 3)
			{
				moved[1] -= movement[i][0];
			}
			moved[1] %=10;
			moved[0] %=10;
			ship1R %=4;
		}
		board[moved[1]][moved[0]] = 1;
		board[initial[1]][initial[0]] = 0;
	}
	public int[] getShip1Position()
	{
		for(int y = 0; y<board.length; y++)
		{
			for(int x = 0; x<board[y].length; x++)
			{
				if(board[y][x] == 1)
				{
					return(new int[]{y,x});
				}
			}
		}
		return null;
	}
	public int getShip1R()
	{
		return(ship1R);
	}
	
	public void moveShip2(int[][] movement)
	{
		int[] initial = getShip2Position(); 
		int[] moved = getShip2Position();
		for(int i = 0; i<movement.length; i++)
		{
			if(ship2R == 0)
			{
				moved[1] += movement[i][0];
			}
			if(ship2R == 1)
			{
				moved[0] += movement[i][0];
			}
			if(ship2R == 2)
			{
				moved[1] -= movement[i][0];
				
			}
			if(ship2R == 3)
			{
				moved[0] -= movement[i][0];
			}
			ship2R += movement[i][1];
			ship2R %= ship2R;
		}
		board[moved[1]][moved[0]] = 2;
		board[initial[1]][initial[0]] = 0;
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
