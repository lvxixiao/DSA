package utils;

import java.util.ArrayList;
import java.util.LinkedList;

public class BinNode<T extends Comparable<T>> {
	private T data;
	private BinNode<T> parent;
	private BinNode<T> lc;
	private BinNode<T> rc;

	
	BinNode(){
		this.data = null;
		this.parent = null;
		this.lc = null;
		this.rc = null;
	}
	BinNode(T data,BinNode<T> parent,BinNode<T> lc,BinNode<T> rc) {
		this.data = data;
		this.parent = parent;
		this.lc = lc;
		this.rc = rc;
	}
	//子树中序遍历
	public ArrayList<T> travIn() {
		LinkedList<BinNode<T>> s = new LinkedList<BinNode<T>>();
		ArrayList<T> nodes = new ArrayList<T>();
		BinNode<T> x = this;
		while(true) {
			if(x!=null) {
				s.push(x);
				x = x.lc;
			} else if(!s.isEmpty()) {
				x = s.pop();
				nodes.add(x.data);
				x = x.rc;
			} else {
				break;
			}
		}
		return nodes;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	public BinNode<T> getParent() {
		return parent;
	}
	public void setParent(BinNode<T> parent) {
		this.parent = parent;
	}
	public BinNode<T> getLc() {
		return lc;
	}
	public void setLc(BinNode<T> lc) {
		this.lc = lc;
	}
	public void removeLc() {
		this.lc = null;
	}
	public void setLc(T e) {
		this.lc = new BinNode<T>(e,this,null,null);
	}
	public BinNode<T> getRc() {
		return rc;
	}
	public void setRc(BinNode<T> rc) {
		this.rc = rc;
	}
	public void removeRc() {
		this.rc = null;
	}
	public void setRc(T e) {
		this.rc = new BinNode<T>(e,this,null,null);
	}
	//取当前节点的直接后继
	public BinNode<T> succ() {
		BinNode<T> s = this;
		//如果有右孩子，直接后继必在右子树中最靠左的节点
		if(s.getRc()!=null) {
			s = getRc();
			while(s.hasLChild())
				s = s.getLc();
		} else {
			//如果没有右孩子，直接后继位于“将当前节点包含于其左子树中的最低祖先”
			while(s.isRChild())
				s = s.getParent();
			s = s.getParent();
		}
		return s;
	}
	public boolean isRChild() {
		BinNode<T> parent = this.getParent();
		if(parent==null)
			return false;
		
		if(parent.getRc()==this)
			return true;
		return false;
	}
	public boolean hasLChild() {
		if(this.getLc()!=null)
			return true;
		return false;
	}
	public boolean hasRChild() {
		if(this.getRc()!=null)
			return true;
		return false;
	}
}
