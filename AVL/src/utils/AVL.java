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
	//ƽ������
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
	//ƽ������
	public boolean avlBalanced(BinNode<T> x) {
		if(-2 < balFac(x) && balFac(x) < 2)
			return true;
		return false;
	}
	//��e��Ϊx�����Ӳ���
	public BinNode<T> insertAsLc(BinNode<T> x,BinNode<T> e) {
		BinNode<T> lc = x.getLc();
		x.setLc(e);
		e.setParent(x);
		x.updateHeightAbove();
		return lc;
	}
	//��e��Ϊx���Һ��Ӳ���
	public BinNode<T> insertAsRc(BinNode<T> x,BinNode<T> e) {
		BinNode<T> rc = x.getRc();
		x.setRc(e);
		e.setParent(x);
		x.updateHeightAbove();
		return rc;
	}
	//�����������
	public ArrayList<BinNode<T>> travIn(BinNode<T> e) {
		return e.travIn();
	}
	//����
	public BinNode<T> insert(T e) {
		BinNode<T> x = search(e);
		//Ĭ�ϲ������ظ�Ԫ��
		if(x!=null) 
			return x;
		x = new BinNode<T>(e,this.hot, null, null);
		if(this.hot.getData().compareTo(e) < 0) {
			this.hot.setRc(x);
		} else {
			this.hot.setLc(x);
		}
		//������ڵ�x�ĸ���hot���ߣ����ܵ������游ʧ��
		//�ӱ�����ڵ�x�ĸ��׿�ʼ�����ϼ����������Ƿ�ʧ��
		for(BinNode<T> g = this.hot; g!=null ; g = g.getParent()) {
			if(!avlBalanced(g)) {
				//����ʧ�⣬�����ع��㷨ʹ��ָ�ƽ��,���ӻ�ԭ��
				rotateAt(g.tallerChild().tallerChild());				
				break;
			} else {
				//���¸߶�(��ʹgδƽ�⣬�߶�Ҳ��������)
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
					//p�����ع���Ϊ�����ĸ��ڵ㣬��p����ԭ��������ԭ���Ĳ�ƽ����
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
					//v�����ع���Ϊ�����ĸ��ڵ㣬��v����ԭ��������ԭ���Ĳ�ƽ����
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
					//v�����ع���Ϊ�����ĸ��ڵ㣬��v����ԭ��������ԭ���Ĳ�ƽ����
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
					//p�����ع���Ϊ�����ĸ��ڵ㣬��p����ԭ��������ԭ���Ĳ�ƽ����
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
	//ɾ��
	public boolean remove(T e) {
		BinNode<T> x = search(e);
		if(x==null)
			return false;
		removeAt(x);
		for(BinNode<T> g = this.hot ; g !=null ; g = g.getParent()) {
			if(!avlBalanced(g))
				//����ʧ��ڵ㣬ʹ���ع��㷨ʹ��ƽ�Ⲣ�ӻ�ԭ��
				rotateAt(g.tallerChild().tallerChild());
			g.updateHeight();//��ʹgδƽ�⣬�߶�����ܽ���
		}
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
	//�ع�
	/**
	 * ����BST����������ĵ����ԣ���Ȼ����
	 * to<a<t1<b<t2<c<t3
	 * ���������ع�Ϊ���½ṹ
	 * 					b
	 * 			a				c
	 * 		t0		t1		t2		t3
	 * ������Ҫzig,zag,zig-zag,zag-zig,zig-zig,zag-zag�������������λ��
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
/*	��תͨ���ع�ʵ��
  
  
   //zig ˳ʱ����ת
	
	 * 					v					c
	 * 			c			z	----->	x			v
	 * 		x		y							y		z
	 * 
	public void zig(BinNode<T> v) {
		BinNode<T> c = v.getLc();
		//���ڵ�c���Һ���ת�Ƹ�v
		c.getRc().setParent(v);
		v.setLc(c.getRc());
		//��v��c��λ��ת��
		hot = v.getParent();
		if(hot!=null)
			c.setParent(hot);
		else
			c.setParent(null);
		v.setParent(c);
		c.setRc(v);
		//�ж�v�Ƿ�Ϊ��Ԫ��
		if(v == root)
			this.root = c;
		//���¸߶�
		v.updateHeightAbove();
	}
	//zag ��ʱ����ת
	
	 *		v										c			      
	 *	x			c			--->		v    		z
	 *			y		z				x		y	  
	 * 
	public void zag(BinNode<T> v) {
		BinNode<T> c = v.getRc();
		//���ڵ�c������ת�Ƹ�v
		c.getLc().setParent(v);
		v.setRc(c.getLc());
		//��v��c��λ��ת��
		hot = v.getParent();
		if(hot!=null)
			c.setParent(hot);
		else
			c.setParent(null);
		v.setParent(c);
		c.setLc(v);
		//�ж�v�Ƿ�Ϊ��Ԫ��
		if(v == root)
			this.root = c;
		//���¸߶�
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
