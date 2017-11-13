package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;

@SuppressWarnings("unused")
public class BinaryTree<E> {
	private Node<E> root;
	private int size;
	
	public BinaryTree(){
		root = null;
	}
	public BinaryTree(E e){
		root = new Node<E>();
		root.e = e;
	}

	//规模
	int size() {	
		return size;
	}
	//判空
	public boolean empty(){
		return root==null?true:false;
	}
	//返回根节点
	public Node<E> getRoot() {
		return root;
	}
	//插入根节点
	public Node<E> inserAsRoot(E e) {
		// assert root == null;
		root = new Node<E>(e,null,null,null);
		return root;
	} 
	//将e作为x的左孩子插入
	public Node<E> inserAsLC(Node<E> x,E e){
		// assert x.lc == null;
		return x.inserAsLC(e);
	}
	//将E作为x的右孩子插入
	public Node<E> inserAsRC(Node<E> x,E e){
		// assert x.rc == null;
		return x.inserAsRC(e);
	}
	//按照先序插入x的后代
	public void inserPre(Node<E> x,E[] arr) {
		// assert x != null;
		LinkedList<Node<E>> queue = new LinkedList<Node<E>>();
		queue.add(x);
		for(int i = 0 ; i < arr.length ; i++) {
			x = queue.peek();
			if(i % 2 == 0) {
				queue.add(queue.peek().inserAsLC(arr[i]));
				queue.peekLast().parent = x; //建立父链接
			} else {
				queue.add(queue.poll().inserAsRC(arr[i]));
				queue.peekLast().parent = x;
			}	
		}
		size = arr.length;
	}
	//先序遍历迭代版
	public ArrayList<E> travPre() {
		return root.travPre();
	}
	//中序遍历
	public ArrayList<E> travIn() {
		return root.travIn();
	}
	//后序遍历
	public ArrayList<E> travPost() {
		return root.travPost();
	}
	//层次遍历
	public ArrayList<E> travLevel(){
		return root.travLevel();
	}
	
	//树节点类
	private static class Node<E> {		
		Node<E> lc;
		Node<E> rc;
		Node<E> parent;
		E e;
		int h;
		Node(E e,Node<E> lc,Node<E> rc,Node<E> parent){
			this.e = e;
			this.lc = lc;
			this.rc = rc;
			this.parent = parent;
			h  = 0;
		}
		Node(){
		}
		//作为当前节点的左孩子插入新节点
		public Node<E> inserAsLC(E e) {
			// assert lc == null;
			lc = new Node<E>(e,null,null,this);
			return lc;
		}
		//作为当前节点的右孩子插入新节点
		public Node<E> inserAsRC(E e) {
			// assert rc == null;
			rc = new Node<E>(e,null,null,this);
			return rc;
		}
		//子树先序遍历
		public ArrayList<E> travPre() {
			LinkedList<Node<E>> stack = new LinkedList<Node<E>>();//辅助栈
			ArrayList<E> al = new ArrayList<E>();
			Node<E> x = this;
			while(true) {
				visitAlongLeftBreanch(x,al,stack);
				if( stack.isEmpty())
					return al;
				x = stack.pop();
			}
		}
		//子树中序遍历
		public ArrayList<E> travIn() {
			LinkedList<Node<E>> stack = new LinkedList<Node<E>>();//辅助栈
			ArrayList<E> al = new ArrayList<E>();
			Node<E> x = this;
			while(true) {
				goAlongLeftBreanch(x,stack);
				if(stack.isEmpty())
					return al;
				x = stack.pop();
				al.add(x.e);
				x = x.rc;
			}
		}
		//子树后序遍历
		public ArrayList<E> travPost() {
			LinkedList<Node<E>> stack = new LinkedList<Node<E>>();//辅助栈
			ArrayList<E> al = new ArrayList<E>();
			Node<E> x = this;
			if(x != null)
				stack.push(x);
			while(!stack.isEmpty()) {
				if(stack.peek() != x.parent)//若栈顶非当前节点之父，则必为其右兄，此时需
					goToHLVFL(stack);//在以其右兄为根的子树中，找到左分支最深的节点
				x = stack.pop();
				al.add(x.e);
			}
			return al;
		}
		//
		public ArrayList<E> travLevel(){
			LinkedList<Node<E>> queue = new LinkedList<Node<E>>();//辅助队列
			ArrayList<E> al = new ArrayList<E>();
			Node<E> x = this;
			queue.add(x);
			while(!queue.isEmpty()) {
				x = queue.poll();
				al.add(x.e);
				if(x.lc != null)
					queue.add(x.lc);
				if(x.rc != null)
					queue.add(x.rc);
			}
			return al;
		}
		private void visitAlongLeftBreanch(Node<E> x,ArrayList<E> al,LinkedList<Node<E>> s) {
			while(x != null) {
				al.add(x.e);
				s.push(x.rc); //读取左孩子得值，将右孩子压入栈
				x = x.lc;
			}
		}
		private void goAlongLeftBreanch(Node<E> x,LinkedList<Node<E>> s) {
			while(x != null) {
				s.push(x); //将左分支压入栈
				x = x.lc;
			}
		}
		private void goToHLVFL(LinkedList<Node<E>> s) {
			Node<E> x;				
			while(s.peek() != null) {
				x = s.peek();
				//按照先右后左的顺序压入栈
				if(x.lc != null) {
					if(x.rc != null) {
						s.push(x.rc);
					}
					s.push(x.lc);
				} else {
					s.push(null);
				}
			}
			s.pop();
		}
	}
}
