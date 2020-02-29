package util;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

public final class Utils 
{
	public static final int BOARD_SIZE = 10;
	
	@XmlRootElement(name = "BoardLocation")
	public static class BoardLocation implements Serializable
	{
		/**
		 * BoardLocation Serializable Implementation
		 */
		private static final long serialVersionUID = -8633355774970772848L;
		
		
		
		public int x;
		public int y;
		
		public BoardLocation(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		
		@Override
		public boolean equals(Object obj)
		{
			if(this == obj)
				return true;
			if(!(obj instanceof BoardLocation))
				return false;

			BoardLocation other = (BoardLocation) obj;
			return (other.x == this.x) && (other.y == this.y);
		}
		
		@Override
	    public String toString() 
	    { 
	        return String.format("(%d,%d)", x, y);
	    } 
		
		@Override
		public int hashCode() 
		{
			//Possible Alt Hashing Idea: https://www.codexpedia.com/java/java-set-and-hashset-with-custom-class/
			return this.toString().hashCode();
		}
	}

//	public final static class Roles
//	{
//		public final static String CAPTAIN = "Captain";
//		public final static String 
//		public final static String 
//		public final static String 
//		public final static String 
//		public final static String 
//	}
}
