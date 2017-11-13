package nqueen;

import java.util.LinkedList;

public class Stack<E> {
	private LinkedList<E> storage = null;
	
	public Stack() {
		storage = new LinkedList<E>();
	}
	
	public int size() {
		return storage.size();
	}
	
	public boolean empty() {
		return storage.isEmpty();
	}
	/**
	 * LinkedList����ջ��ص�Դ����Կ���Java�ٷ���ջ����first��ջ����last��
	 * ������ѹ��ջ
	 * */
	public void push(E e) {
		storage.addFirst(e);
	}
	/**
	 * ���ز�ɾ��ջ������
	 * */
	public E pop() {
		return storage.removeFirst();
	}
	/**
	 * ����ջ�������ǲ�ɾ��
	 * */
	public E top() {
		return storage.getFirst();
	}

	/**
	 * ����������ѯջԪ��
	 * */
	public E get(int index) {
		return storage.get(index);
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		int n = size();
		sb.append("��Ч�⣺");
		for(int i = 0 ; i < n ; i++) {
			sb.append(pop().toString());
		}
		return sb.toString();
	}	
	
}
