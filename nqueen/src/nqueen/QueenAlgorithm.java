package nqueen;

public class QueenAlgorithm {
	/**
	 * N�ʺ��㷨
	 * �˰汾ֻ���ҳ�һ���⣬��Ҳ���ӵ�и����Ч��
	 * */
	public void placeQueens(int n) {
		Stack<Queen> solu = new Stack<Queen>(); //��ţ����֣����ջ
		Queen q = new Queen(0,0);//��ԭ�����
		do {
			/**
			 * �����ͻ��������һ���ʺ�
			 * n <= solu.size() ��ʾ��ǰ����ȫ�ֽ⣬������һ�������ҵ���ͬ��
			 * n <= q.getY() ��ʾ��ǰ���޽⣬��Ҫ������һ��
			 * */
			if(n <= q.getY()) {
				q = solu.pop();
				q.setY(q.getY() + 1);
			} else {
				//ͨ�����лʺ�Ƚϣ������ҵ���һ���ʺ��λ��	
				while((q.getY() < n) && (find(solu,q))) {
					q.setY(q.getY() + 1);						
				}				
				//�����ڿɰ�λ�ã����ϻʺ�
				if(q.getY() < n) {
					solu.push(q);
					//�ҵ�ȫ�ֽ⣬��ȫ�ֽ��ӡ����
					if(n <= solu.size()) {
						System.out.println(solu.toString());						
					}
					/**
					 * ת����һ�У��ӵ�0�п�ʼ
					 * q.setX(q.getX() + 1);					
					 * q.setY(0);
					 * ����ͨ�����ַ�ʽ�ı�q��ֵ����Ӱ��ջ���Queen�����ֵ
					 * ��Ϊq��ջ���Ԫ��ָ��ͬһ����ַ
					 * ����ͨ���½�Queen���󣬽�qָ����ջ�����ĵ�ַ.				
					 */
					q = new Queen(q.getX() + 1, 0);
				}
			}			
		}while(q.getX() < n);//�ô���������ѭ���޷��ҵ����н�
	}
	
	private boolean find(Stack<Queen> solu,Queen q) {
		Queen temp;
		if(!solu.empty()) { //�˴��жϷ�ֹ�ڵ�һ�γ���ʱ����null�����쳣
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
