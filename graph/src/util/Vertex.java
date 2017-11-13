package util;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;


public class Vertex<T> {

    private T label;//��ʶ���,�����ò�ͬ��������ʶ������String,Integer....
    private LinkedList<Edge<T>> edgeList;//���ö����ڽӵ�ı�,ʵ����java.util.LinkedList�洢
    private Vertex<T> previousVertex;//�ö����ǰ������
    private double cost;//�����Ȩֵ,��ߵ�ȨֵҪ������ 
    private VStatus status;//�����״̬
    private int dTime; //ʱ���ǩ
    private int fTime; //ʱ���ǩ
    public int getdTime() {
		return dTime;
	}

	public void setdTime(int dTime) {
		this.dTime = dTime;
	}

	public int getfTime() {
		return fTime;
	}

	public void setfTime(int fTime) {
		this.fTime = fTime;
	}

	public Vertex(T label) {
    	this.label = label;
    	edgeList = new LinkedList<Edge<T>>();
    	status = VStatus.UNDISCOVEREN;
    	previousVertex = null;
    	cost = 0;
    }
    
	public VStatus getStatus() {
		return status;
	}

	public void setStatus(VStatus status) {
		this.status = status;
	}

	public T getLabel() {
		return label;
	}
	public void setLabel(T label) {
		this.label = label;
	}
	public LinkedList<Edge<T>> getEdgeList() {
		return edgeList;
	}
	public void setEdgeList(LinkedList<Edge<T>> edgeList) {
		this.edgeList = edgeList;
	}
	public Vertex<T> getPreviousVertex() {
		return previousVertex;
	}
	public void setPreviousVertex(Vertex<T> previousVertex) {
		this.previousVertex = previousVertex;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	//��øö����ڽӶ���ĵ�����
	public Iterator<Edge<T>> listIterator(Vertex<T> v) {
        return new NeighborIterator(v).edgesIterator;
    }
	
    
    private class NeighborIterator implements Iterator<Vertex<T>> {
    	Iterator<Edge<T>> edgesIterator;
    	NeighborIterator(Vertex<T> v) {
    		edgesIterator = edgeList.iterator();
    	}
    	public boolean hasNext() {
            return edgesIterator.hasNext();
        }
    	public Vertex<T> next() {
            Vertex<T> nextNeighbor = null;
            if(edgesIterator.hasNext()){
                Edge<T> edgeToNextNeighbor = edgesIterator.next();//LinkedList�д洢����Edge
                nextNeighbor = edgeToNextNeighbor.getEndVertex();//��Edge������ȡ������
            }
            else
                throw new NoSuchElementException();
            return nextNeighbor;
        }
    	
    	public void remove() {
            throw new UnsupportedOperationException();
        }
    	
    }
    @Override
    @SuppressWarnings("all")
    public boolean equals(Object obj) {
    	Vertex<T> u = (Vertex<T>)obj;
    	if(this.label.equals(u.getLabel()))
    		return true;
    	return false;
    }
}
