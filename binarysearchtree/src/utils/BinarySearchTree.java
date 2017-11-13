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
	//将e作为x的左孩子插入
	public BinNode<T> insertAsLc(BinNode<T> x,BinNode<T> e) {
		BinNode<T> lc = x.getLc();
		x.setLc(e);
		return lc;
	}
	//将e作为x的右孩子插入
	public BinNode<T> insertAsRc(BinNode<T> x,BinNode<T> e) {
		BinNode<T> rc = x.getRc();
		x.setRc(e);
		return rc;
	}
	//子树中序遍历
	public ArrayList<T> travIn(BinNode<T> e) {
		return e.travIn();
	}
	//查找
	public BinNode<T> search(BinNode<T> e) {
		return null;
	}
	//插入
	public boolean insert(T e) {
		BinNode<T> x = search(e);
		//默认不存在重复元素
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
	//删除
	public boolean remove(T e) {
		BinNode<T> x = search(e);
		if(x==null)
			return false;
		removeAt(x);
		return true;
	}
	private void removeAt(BinNode<T> x) {
		BinNode<T> w = x;//实际被摘除的节点，初值等于x
		BinNode<T> succ = null;//实际被删除节点的接替者
		boolean isLeftChild = true;//用来确定待删除节点是父亲节点的左孩子还是右孩子		
		
		if( !x.hasLChild() )//如果左子树为空
		{
			succ = x.getRc();
			x = x.getRc();
		} else if(!x.hasRChild()){ //如果右子树为空
			succ = x.getLc();
			x = x.getLc();
		} else { //左右子树都存在
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
			//判断是否有接替者
			if(succ!=null) {
				//建立双向连接，隔离被删除节点
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
