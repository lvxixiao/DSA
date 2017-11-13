package nqueen;

public class Queen {
	private int x,y;

	public Queen(int x,int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public boolean equals(Queen q) {
		return (this.x == q.getX()) //行冲突
				|| (this.y == q.getY()) //列冲突
				|| ((this.x + this.y) == (q.getX()) + q.getY()) //正对角线冲突
				|| ((this.x - this.y) == (q.getX() - q.getY()));//反对角线冲突
	}

	@Override
	public String toString() {
		return "[x=" + this.x + ", y=" + this.y + "]"+",";
	}
}
