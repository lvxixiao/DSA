package utils;

import java.util.ArrayList;
import java.util.LinkedList;

public class BinNode<T extends Comparable<T>> {
	private T data;
	private BinNode<T> parent;
	private BinNode<T> lc;
	private BinNode<T> rc;
	private int hight;

	BinNode(){
		this.data = null;
		this.parent = null;
		this.lc = null;
		this.rc = null;
		this.hight = 0;
	}
	BinNode(T data){
		this.data = data;
		this.parent = null;
		this.lc = null;
		this.rc = null;
		this.hight = 0;
	}
	BinNode(T data,BinNode<T> parent,BinNode<T> lc,BinNode<T> rc) {
		this.data = data;
		this.parent = parent;
		this.lc = lc;
		this.rc = rc;
		this.hight = 0;
	}
	//子树中序遍历
	public ArrayList<BinNode<T>> travIn() {
		LinkedList<BinNode<T>> s = new LinkedList<BinNode<T>>();
		ArrayList<BinNode<T>> nodes = new ArrayList<BinNode<T>>(); 
		BinNode<T> x = this;
		while(true) {
			if(x!=null) {
				s.push(x);
				x = x.lc;
			} else if(!s.isEmpty()) {
				x = s.pop();
				nodes.add(x);
				x = x.rc;
			} else {
				break;
			}
		}
		return nodes;
	}
	//更新节点x的高度
	public int updateHeight() {
		int lhight;
		int rhight;
		lhight = stature(this.getLc());
		rhight = stature(this.getRc());
		if(lhight > rhight) {
			this.setHight(1 + lhight);
		} else {
			this.setHight(1 + rhight);
		}
		return this.getHight();
	}
	/*
	 * 高度定义
	 * 叶节点为0
	 * 空节点为-1
	 * */
	private int stature(BinNode<T> x) {
		if(x == null)
			return -1;
		else
			return x.getHight();
	}
	//更新节点x及其祖先的高度
	public void updateHeightAbove() {		
		BinNode<T> x = this;
		do {
			x.updateHeight();
			x = x.getParent();
		} while (x!=null);
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
		if(lc!=null) 
			lc.setParent(this);
	}
	public void removeLc() {
		this.lc = null;
		this.updateHeight();
	}
	public void setLc(T e) {
		this.lc = new BinNode<T>(e,this,null,null);
	}
	public BinNode<T> getRc() {
		return rc;
	}
	public void setRc(BinNode<T> rc) {
		this.rc = rc;
		if(rc!=null)
			rc.setParent(this);
	}
	public void removeRc() {
		this.rc = null;
	}
	public void setRc(T e) {
		this.rc = new BinNode<T>(e,this,null,null);
	}
	public int getHight() {
		return hight;
	}
	public void setHight(int hight) {
		this.hight = hight;
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
	public BinNode<T> tallerChild() {
		//由于avl的特性，传入tallerChild的节点不存在左右孩子为null，无需做!null判断
		int lhight,rhight;
		if(this.lc!=null)
			lhight = this.lc.getHight();
		else
			lhight = -1;
		if(this.rc != null)
			rhight = this.rc.getHight();
		else 
			rhight = -1;
		/**
		 * 当rhight == lhight时，与父亲同侧优先
		 * 且父亲不可能为Null
		 * 第一个调用tallerChild()的节点为不平衡节点，此时不存在rhight == lhight
		 * 第二个调用tallerChild()的节点为不平衡节点的子节点，该节点的父亲为不平衡节点
		*/
		if(lhight == rhight) { 
			if(this.isRChild())
				return this.rc;
			else
				return this.lc;
		} else if(lhight > rhight) {
			return this.lc;
		} else {
			return this.rc;
		}
	}
}
