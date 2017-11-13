package nqueen;

public class QueenAlgorithm {
	/**
	 * N皇后算法
	 * 此版本只能找出一个解，但也因此拥有更快的效率
	 * */
	public void placeQueens(int n) {
		Stack<Queen> solu = new Stack<Queen>(); //存放（部分）解的栈
		Queen q = new Queen(0,0);//从原点出发
		do {
			/**
			 * 如果冲突，回溯上一个皇后
			 * n <= solu.size() 表示当前已是全局解，回溯上一步尝试找到不同解
			 * n <= q.getY() 表示当前行无解，需要回溯上一步
			 * */
			if(n <= q.getY()) {
				q = solu.pop();
				q.setY(q.getY() + 1);
			} else {
				//通过已有皇后比较，尝试找到下一个皇后的位置	
				while((q.getY() < n) && (find(solu,q))) {
					q.setY(q.getY() + 1);						
				}				
				//若存在可摆位置，摆上皇后
				if(q.getY() < n) {
					solu.push(q);
					//找到全局解，将全局解打印出来
					if(n <= solu.size()) {
						System.out.println(solu.toString());						
					}
					/**
					 * 转入下一行，从第0列开始
					 * q.setX(q.getX() + 1);					
					 * q.setY(0);
					 * 不能通过这种方式改变q的值，会影响栈里的Queen对象的值
					 * 因为q跟栈里的元素指向同一个地址
					 * 必须通过新建Queen对象，将q指向与栈里对象的地址.				
					 */
					q = new Queen(q.getX() + 1, 0);
				}
			}			
		}while(q.getX() < n);//用此条件结束循环无法找到所有解
	}
	
	private boolean find(Stack<Queen> solu,Queen q) {
		Queen temp;
		if(!solu.empty()) { //此处判断防止在第一次尝试时访问null引发异常
			for(int i = 0 ; i < solu.size() ; i++) {
				temp = solu.get(i);
				if(temp.equals(q)) {
					return true;
				}
			}	
		}
		return false;
	}	
}
