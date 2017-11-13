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
		return (this.x == q.getX()) //�г�ͻ
				|| (this.y == q.getY()) //�г�ͻ
				|| ((this.x + this.y) == (q.getX()) + q.getY()) //���Խ��߳�ͻ
				|| ((this.x - this.y) == (q.getX() - q.getY()));//���Խ��߳�ͻ
	}

	@Override
	public String toString() {
		return "[x=" + this.x + ", y=" + this.y + "]"+",";
	}
}
