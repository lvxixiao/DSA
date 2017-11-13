package utils;

import java.util.ArrayList;

public class AVL<T extends Comparable<T>> {
	private BinNode<T> root;
	private BinNode<T> hot;
	
	public BinNode<T> getHot() {
		return hot;
	}
	public void setHot(BinNode<T> hot) {
		this.hot = hot;
	}
	public AVL() {
		root = null;
	}
	public AVL(BinNode<T> root) {
		this.root = root;
	}
	public AVL(T e) {
		root = new BinNode<T>(e,null,null,null);
	}
	//平衡因子
	public int balFac(BinNode<T> x) {
		int rhight,lhight;
		if(x.getRc()!=null)
			rhight = x.getRc().getHight();
		else
			rhight = -1;
		if(x.getLc()!=null)
			lhight = x.getLc().getHight();
		else
			lhight = -1;
		return rhight - lhight;
	}
	//平衡条件
	public boolean avlBalanced(BinNode<T> x) {
		if(-2 < balFac(x) && balFac(x) < 2)
			return true;
		return false;
	}
	//将e作为x的左孩子插入
	public BinNode<T> insertAsLc(BinNode<T> x,BinNode<T> e) {
		BinNode<T> lc = x.getLc();
		x.setLc(e);
		e.setParent(x);
		x.updateHeightAbove();
		return lc;
	}
	//将e作为x的右孩子插入
	public BinNode<T> insertAsRc(BinNode<T> x,BinNode<T> e) {
		BinNode<T> rc = x.getRc();
		x.setRc(e);
		e.setParent(x);
		x.updateHeightAbove();
		return rc;
	}
	//子树中序遍历
	public ArrayList<BinNode<T>> travIn(BinNode<T> e) {
		return e.travIn();
	}
	//插入
	public BinNode<T> insert(T e) {
		BinNode<T> x = search(e);
		//默认不存在重复元素
		if(x!=null) 
			return x;
		x = new BinNode<T>(e,this.hot, null, null);
		if(this.hot.getData().compareTo(e) < 0) {
			this.hot.setRc(x);
		} else {
			this.hot.setLc(x);
		}
		//被插入节点x的父亲hot增高，可能导致其祖父失衡
		//从被插入节点x的父亲开始，向上检查各代祖先是否失衡
		for(BinNode<T> g = this.hot; g!=null ; g = g.getParent()) {
			if(!avlBalanced(g)) {
				//发现失衡，采用重构算法使其恢复平衡,并接回原树
				rotateAt(g.tallerChild().tallerChild());				
				break;
			} else {
				//更新高度(即使g未平衡，高度也可能增加)
				g.updateHeight();
			}
		}
		return x;
	}
	private void rotateAt(BinNode<T> v) {
		BinNode<T> p = v.getParent();
		BinNode<T> g = p.getParent();
		BinNode<T> parent = g.getParent();
		if(p.isRChild()) { //zag
			if(v.isRChild()) {//zag-zag
				System.out.println("zag-zag");
				if(parent !=null) {
					p.setParent(parent);
					//p经过重构后为子树的根节点，将p接入原树，代替原来的不平衡树
					if(parent.getData().compareTo(p.getData()) < 0)
						parent.setRc(p);
					else
						parent.setLc(p);
				} else {
					this.root = p;
					p.setParent(null);
				}
				connect34(g, p, v, g.getLc(), p.getLc(), v.getLc(), v.getRc());
			} else { //zag-zig
				System.out.println("zag-zig");
				if(parent!=null) {
					v.setParent(parent);
					//v经过重构后为子树的根节点，将v接入原树，代替原来的不平衡树
					if(parent.getData().compareTo(v.getData()) < 0)
						parent.setRc(v);
					else
						parent.setLc(v);
				} else {
					this.root = v;
					v.setParent(null);
				}
				connect34(g, v, p, g.getLc(), v.getLc(), v.getRc(), p.getRc());
			}
		} else { //zig
			if(v.isRChild()) {//zig-zag
				System.out.println("zig-zag");
				if(parent!=null) {
					v.setParent(parent);
					//v经过重构后为子树的根节点，将v接入原树，代替原来的不平衡树
					if(parent.getData().compareTo(v.getData()) < 0)
						parent.setRc(v);
					else
						parent.setLc(v);
				} else {
					this.root = v;
					v.setParent(null);
				}
				connect34(p, v, g, p.getLc(), v.getLc(), v.getRc(), g.getRc());
			} else { //zig-zig
				System.out.println("zig-zig");
				if(parent!=null) {
					p.setParent(parent);
					//p经过重构后为子树的根节点，将p接入原树，代替原来的不平衡树
					if(parent.getData().compareTo(p.getData()) < 0)
						parent.setRc(p);
					else
						parent.setLc(p);
				} else {
					this.root = p;
					p.setParent(null);
				}
				connect34(v, p, g, v.getLc(), v.getRc(), p.getRc(), g.getRc());
			}
		}
	}
	//删除
	public boolean remove(T e) {
		BinNode<T> x = search(e);
		if(x==null)
			return false;
		removeAt(x);
		for(BinNode<T> g = this.hot ; g !=null ; g = g.getParent()) {
			if(!avlBalanced(g))
				//发现失衡节点，使用重构算法使其平衡并接回原树
				rotateAt(g.tallerChild().tallerChild());
			g.updateHeight();//即使g未平衡，高度亦可能降低
		}
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
	//重构
	/**
	 * 由于BST的中序遍历的单调性，必然存在
	 * to<a<t1<b<t2<c<t3
	 * 将此子树重构为以下结构
	 * 					b
	 * 			a				c
	 * 		t0		t1		t2		t3
	 * 根据需要zig,zag,zig-zag,zag-zig,zig-zig,zag-zag决定传入参数的位置
	 * */
	public void connect34(BinNode<T> a,BinNode<T> b,BinNode<T> c,
					BinNode<T> t0,BinNode<T> t1,BinNode<T> t2,BinNode<T> t3) {
		
		b.setLc(a);
		b.setRc(c);
		
		a.setLc(t0);
		a.setRc(t1);
		
		c.setLc(t2);
		c.setRc(t3);
	}
/*	旋转通过重构实现
  
  
   //zig 顺时针旋转
	
	 * 					v					c
	 * 			c			z	----->	x			v
	 * 		x		y							y		z
	 * 
	public void zig(BinNode<T> v) {
		BinNode<T> c = v.getLc();
		//将节点c的右孩子转移给v
		c.getRc().setParent(v);
		v.setLc(c.getRc());
		//将v与c的位置转换
		hot = v.getParent();
		if(hot!=null)
			c.setParent(hot);
		else
			c.setParent(null);
		v.setParent(c);
		c.setRc(v);
		//判断v是否为根元素
		if(v == root)
			this.root = c;
		//更新高度
		v.updateHeightAbove();
	}
	//zag 逆时针旋转
	
	 *		v										c			      
	 *	x			c			--->		v    		z
	 *			y		z				x		y	  
	 * 
	public void zag(BinNode<T> v) {
		BinNode<T> c = v.getRc();
		//将节点c的左孩子转移给v
		c.getLc().setParent(v);
		v.setRc(c.getLc());
		//将v与c的位置转换
		hot = v.getParent();
		if(hot!=null)
			c.setParent(hot);
		else
			c.setParent(null);
		v.setParent(c);
		c.setLc(v);
		//判断v是否为根元素
		if(v == root)
			this.root = c;
		//更新高度
		v.updateHeightAbove();
	}*/
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
