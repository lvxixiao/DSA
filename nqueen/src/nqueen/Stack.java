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
	 * LinkedList中于栈相关的源码可以看出Java官方的栈底是first，栈顶是last。
	 * 将对象压入栈
	 * */
	public void push(E e) {
		storage.addFirst(e);
	}
	/**
	 * 返回并删除栈顶对象
	 * */
	public E pop() {
		return storage.removeFirst();
	}
	/**
	 * 引用栈顶对象但是不删除
	 * */
	public E top() {
		return storage.getFirst();
	}

	/**
	 * 根据索引查询栈元素
	 * */
	public E get(int index) {
		return storage.get(index);
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		int n = size();
		sb.append("有效解：");
		for(int i = 0 ; i < n ; i++) {
			sb.append(pop().toString());
		}
		return sb.toString();
	}	
	
}
