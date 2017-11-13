package utils;

import java.util.ArrayList;

public class BinarySearchTree<T extends Comparable<T>> {
	private BinNode<T> root;
	private BinNode<T> hot;
	
	public BinNode<T> getHot() {
		return hot;
	}
	public void setHot(BinNode<T> hot) {
		this.hot = hot;
	}
	public BinarySearchTree() {
		root = null;
	}
	public BinarySearchTree(BinNode<T> root) {
		this.root = root;
	}
	public BinarySearchTree(T e) {
		root = new BinNode<T>(e,null,null,null);
	}
	//��e��Ϊx�����Ӳ���
	public BinNode<T> insertAsLc(BinNode<T> x,BinNode<T> e) {
		BinNode<T> lc = x.getLc();
		x.setLc(e);
		return lc;
	}
	//��e��Ϊx���Һ��Ӳ���
	public BinNode<T> insertAsRc(BinNode<T> x,BinNode<T> e) {
		BinNode<T> rc = x.getRc();
		x.setRc(e);
		return rc;
	}
	//�����������
	public ArrayList<T> travIn(BinNode<T> e) {
		return e.travIn();
	}
	//����
	public BinNode<T> search(BinNode<T> e) {
		return null;
	}
	//����
	public boolean insert(T e) {
		BinNode<T> x = search(e);
		//Ĭ�ϲ������ظ�Ԫ��
		if(x!=null) 
			return false;
		x = new BinNode<T>(e,this.hot, null, null);
		if(this.hot.getData().compareTo(e) < 0) {
			this.hot.setRc(x);
		} else {
			this.hot.setLc(x);
		}
		return true;
	}
	//ɾ��
	public boolean remove(T e) {
		BinNode<T> x = search(e);
		if(x==null)
			return false;
		removeAt(x);
		return true;
	}
	private void removeAt(BinNode<T> x) {
		BinNode<T> w = x;//ʵ�ʱ�ժ���Ľڵ㣬��ֵ����x
		BinNode<T> succ = null;//ʵ�ʱ�ɾ���ڵ�Ľ�����
		boolean isLeftChild = true;//����ȷ����ɾ���ڵ��Ǹ��׽ڵ�����ӻ����Һ���		
		
		if( !x.hasLChild() )//���������Ϊ��
		{
			succ = x.getRc();
			x = x.getRc();
		} else if(!x.hasRChild()){ //���������Ϊ��
			succ = x.getLc();
			x = x.getLc();
		} else { //��������������
			w = w.succ();
			swap(x,w);
			BinNode<T> u = w.getParent();
			succ = w.getRc();
			if(u==x) {
				u.setRc(succ);
			} else {
				u.setLc(succ);
			}
		}
		this.hot = w.getParent();
		if(hot!=null) {
			if(hot.getData().compareTo(w.getData())<0) {
				isLeftChild=false;
			} else {
				isLeftChild=true;
			}
			//�ж��Ƿ��н�����
			if(succ!=null) {
				//����˫�����ӣ����뱻ɾ���ڵ�
				if(isLeftChild) {
					hot.setLc(succ);					
				} else {
					hot.setRc(succ);
				}
				succ.setParent(hot);
			} else {
				if(isLeftChild) {
					hot.removeLc();
				} else {
					hot.removeRc();
				}
			}
		} 
	}

	private void swap(BinNode<T> x, BinNode<T> w) {
		T temp = x.getData();
		x.setData(w.getData());
		w.setData(temp);
	}
	public BinNode<T> getRoot() {
		return root;
	}
	public void setRoot(BinNode<T> root) {
		this.root = root;
	}
	
	public BinNode<T> search(T e) {
		return searchIn(root,e);
	}
	private BinNode<T> searchIn(BinNode<T> v, T e) {
		if(v==null || v.getData()==e)
			return v;
		this.hot = v;
		return v.getData().compareTo(e)<0?searchIn(v.getRc(), e):searchIn(v.getLc(),e);
	}
}
