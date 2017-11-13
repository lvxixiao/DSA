package util;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;


public class Vertex<T> {

    private T label;//标识标点,可以用不同类型来标识顶点如String,Integer....
    private LinkedList<Edge<T>> edgeList;//到该顶点邻接点的边,实际以java.util.LinkedList存储
    private Vertex<T> previousVertex;//该顶点的前驱顶点
    private double cost;//顶点的权值,与边的权值要区别开来 
    private VStatus status;//顶点的状态
    private int dTime; //时间标签
    private int fTime; //时间标签
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
	
	//获得该顶点邻接顶点的迭代器
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
                Edge<T> edgeToNextNeighbor = edgesIterator.next();//LinkedList中存储的是Edge
                nextNeighbor = edgeToNextNeighbor.getEndVertex();//从Edge对象中取出顶点
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
