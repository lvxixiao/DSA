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

	//��ģ
	int size() {	
		return size;
	}
	//�п�
	public boolean empty(){
		return root==null?true:false;
	}
	//���ظ��ڵ�
	public Node<E> getRoot() {
		return root;
	}
	//������ڵ�
	public Node<E> inserAsRoot(E e) {
		// assert root == null;
		root = new Node<E>(e,null,null,null);
		return root;
	} 
	//��e��Ϊx�����Ӳ���
	public Node<E> inserAsLC(Node<E> x,E e){
		// assert x.lc == null;
		return x.inserAsLC(e);
	}
	//��E��Ϊx���Һ��Ӳ���
	public Node<E> inserAsRC(Node<E> x,E e){
		// assert x.rc == null;
		return x.inserAsRC(e);
	}
	//�����������x�ĺ��
	public void inserPre(Node<E> x,E[] arr) {
		// assert x != null;
		LinkedList<Node<E>> queue = new LinkedList<Node<E>>();
		queue.add(x);
		for(int i = 0 ; i < arr.length ; i++) {
			x = queue.peek();
			if(i % 2 == 0) {
				queue.add(queue.peek().inserAsLC(arr[i]));
				queue.peekLast().parent = x; //����������
			} else {
				queue.add(queue.poll().inserAsRC(arr[i]));
				queue.peekLast().parent = x;
			}	
		}
		size = arr.length;
	}
	//�������������
	public ArrayList<E> travPre() {
		return root.travPre();
	}
	//�������
	public ArrayList<E> travIn() {
		return root.travIn();
	}
	//�������
	public ArrayList<E> travPost() {
		return root.travPost();
	}
	//��α���
	public ArrayList<E> travLevel(){
		return root.travLevel();
	}
	
	//���ڵ���
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
		//��Ϊ��ǰ�ڵ�����Ӳ����½ڵ�
		public Node<E> inserAsLC(E e) {
			// assert lc == null;
			lc = new Node<E>(e,null,null,this);
			return lc;
		}
		//��Ϊ��ǰ�ڵ���Һ��Ӳ����½ڵ�
		public Node<E> inserAsRC(E e) {
			// assert rc == null;
			rc = new Node<E>(e,null,null,this);
			return rc;
		}
		//�����������
		public ArrayList<E> travPre() {
			LinkedList<Node<E>> stack = new LinkedList<Node<E>>();//����ջ
			ArrayList<E> al = new ArrayList<E>();
			Node<E> x = this;
			while(true) {
				visitAlongLeftBreanch(x,al,stack);
				if( stack.isEmpty())
					return al;
				x = stack.pop();
			}
		}
		//�����������
		public ArrayList<E> travIn() {
			LinkedList<Node<E>> stack = new LinkedList<Node<E>>();//����ջ
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
		//�����������
		public ArrayList<E> travPost() {
			LinkedList<Node<E>> stack = new LinkedList<Node<E>>();//����ջ
			ArrayList<E> al = new ArrayList<E>();
			Node<E> x = this;
			if(x != null)
				stack.push(x);
			while(!stack.isEmpty()) {
				if(stack.peek() != x.parent)//��ջ���ǵ�ǰ�ڵ�֮�������Ϊ�����֣���ʱ��
					goToHLVFL(stack);//����������Ϊ���������У��ҵ����֧����Ľڵ�
				x = stack.pop();
				al.add(x.e);
			}
			return al;
		}
		//
		public ArrayList<E> travLevel(){
			LinkedList<Node<E>> queue = new LinkedList<Node<E>>();//��������
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
				s.push(x.rc); //��ȡ���ӵ�ֵ�����Һ���ѹ��ջ
				x = x.lc;
			}
		}
		private void goAlongLeftBreanch(Node<E> x,LinkedList<Node<E>> s) {
			while(x != null) {
				s.push(x); //�����֧ѹ��ջ
				x = x.lc;
			}
		}
		private void goToHLVFL(LinkedList<Node<E>> s) {
			Node<E> x;				
			while(s.peek() != null) {
				x = s.peek();
				//�������Һ����˳��ѹ��ջ
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
