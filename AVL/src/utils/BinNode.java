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
	//�����������
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
	//���½ڵ�x�ĸ߶�
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
	 * �߶ȶ���
	 * Ҷ�ڵ�Ϊ0
	 * �սڵ�Ϊ-1
	 * */
	private int stature(BinNode<T> x) {
		if(x == null)
			return -1;
		else
			return x.getHight();
	}
	//���½ڵ�x�������ȵĸ߶�
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
	//ȡ��ǰ�ڵ��ֱ�Ӻ��
	public BinNode<T> succ() {
		BinNode<T> s = this;
		//������Һ��ӣ�ֱ�Ӻ�̱��������������Ľڵ�
		if(s.getRc()!=null) {
			s = getRc();
			while(s.hasLChild())
				s = s.getLc();
		} else {
			//���û���Һ��ӣ�ֱ�Ӻ��λ�ڡ�����ǰ�ڵ���������������е�������ȡ�
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
		//����avl�����ԣ�����tallerChild�Ľڵ㲻�������Һ���Ϊnull��������!null�ж�
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
		 * ��rhight == lhightʱ���븸��ͬ������
		 * �Ҹ��ײ�����ΪNull
		 * ��һ������tallerChild()�Ľڵ�Ϊ��ƽ��ڵ㣬��ʱ������rhight == lhight
		 * �ڶ�������tallerChild()�Ľڵ�Ϊ��ƽ��ڵ���ӽڵ㣬�ýڵ�ĸ���Ϊ��ƽ��ڵ�
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
