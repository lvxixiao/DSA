package test;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Test;

import util.Edge;
import util.Graph;
import util.Vertex;

public class TestGraph {
	//测试Vertex类的equals函数
	@Test
	public void testVertexEquals() {
		Vertex<Integer> v1 = new Vertex<Integer>(1234);
		Vertex<Integer> v2 = new Vertex<Integer>(1234);
		Vertex<Integer> v3 = new Vertex<Integer>(1434);
		Vertex<String> v4 = new Vertex<String>("1434");
		Vertex<String> v5 = new Vertex<String>("1434");
		Vertex<String> v6 = new Vertex<String>("1234");
		System.out.println(v1.equals(v2));
		System.out.println(v1.equals(v3));
		System.out.println(v4.equals(v5));
		System.out.println(v4.equals(v6));
	}
	//测试Edge类的equals函数
	@Test
	public void testEdgeEquals() {
		Vertex<Integer> v1 = new Vertex<Integer>(1234);
		Vertex<Integer> v2 = new Vertex<Integer>(1234);
		Vertex<Integer> v3 = new Vertex<Integer>(1434);
		Vertex<Integer> v4 = new Vertex<Integer>(1434);
		Edge<Integer> e1 = new Edge<Integer>(v1,v3,0);
		Edge<Integer> e2 = new Edge<Integer>(v2,v4,0);
		System.out.println(e1.equals(e2));
		Vertex<Integer> v5 = new Vertex<Integer>(1234);
		Edge<Integer> e3 = new Edge<Integer>(v2,v5,0);
		System.out.println(e1.equals(e3));
		Vertex<String> v6 = new Vertex<String>("1434");
		Vertex<String> v7 = new Vertex<String>("1456");
		Vertex<String> v8 = new Vertex<String>("1234");
		Vertex<String> v9 = new Vertex<String>("1456");
		Edge<String> e4 = new Edge<String>(v6,v7,0);
		Edge<String> e5 = new Edge<String>(v6,v8,0);
		Edge<String> e6 = new Edge<String>(v6,v9,0);
		System.out.println(e4.equals(e5));
		System.out.println(e4.equals(e6));
	}
	@Test
	public void testBFS() {
		LinkedList<Edge<String>> edgeList = new LinkedList<Edge<String>>();
		Vertex<String> vA = new Vertex<String>("A");  
		Vertex<String> vB = new Vertex<String>("B");  
		Vertex<String> vC = new Vertex<String>("C");  
		Vertex<String> vD = new Vertex<String>("D");  
		Vertex<String> vE = new Vertex<String>("E");  
		Vertex<String> vF = new Vertex<String>("F");  
		Vertex<String> vG = new Vertex<String>("G");  
		Vertex<String> vS = new Vertex<String>("S");  
		
		edgeList = vS.getEdgeList();
		Edge<String> e0 = new Edge<String>(vS,vA,0);
		Edge<String> e1 = new Edge<String>(vS,vC,0);
		Edge<String> e2 = new Edge<String>(vS,vD,0);
		edgeList.add(e0);
		edgeList.add(e1);
		edgeList.add(e2);
		
		edgeList = vA.getEdgeList();
		e0 = new Edge<String>(vA,vC,0);
		e1 = new Edge<String>(vA,vE,0);
		edgeList.add(e0);
		edgeList.add(e1);
		
		edgeList = vC.getEdgeList();
		e0 = new Edge<String>(vC,vB,0);
		edgeList.add(e0);
		
		edgeList = vD.getEdgeList();
		e0 = new Edge<String>(vD,vB,0);
		edgeList.add(e0);
		
		edgeList = vE.getEdgeList();
		e0 = new Edge<String>(vE,vF,0);
		e1 = new Edge<String>(vE,vG,0);
		edgeList.add(e0);
		edgeList.add(e1);
		
		edgeList = vG.getEdgeList();
		e0 = new Edge<String>(vG,vB,0);
		e1 = new Edge<String>(vG,vF,0);
		edgeList.add(e0);
		edgeList.add(e1);
		Graph<String> g = new Graph<String>();
		
		ArrayList<Vertex<String>> vertexs = g.getVertexs();
		vertexs.add(vS);
		vertexs.add(vA);
		vertexs.add(vB);
		vertexs.add(vC);
		vertexs.add(vD);
		vertexs.add(vE);
		vertexs.add(vF);
		vertexs.add(vG);
		
		g.bfs(vS);
		System.out.println("执行完成");
	}
	
	@Test
	public void testDFS() {
		LinkedList<Edge<String>> edgeList = null;
		Vertex<String> vA = new Vertex<String>("A");  
		Vertex<String> vB = new Vertex<String>("B");  
		Vertex<String> vC = new Vertex<String>("C");  
		Vertex<String> vD = new Vertex<String>("D");  
		Vertex<String> vE = new Vertex<String>("E");  
		Vertex<String> vF = new Vertex<String>("F");  
		Vertex<String> vG = new Vertex<String>("G"); 
		
		edgeList = vA.getEdgeList();
		Edge<String> e0 = new Edge<String>(vA,vB,0);
		Edge<String> e1 = new Edge<String>(vA,vC,0);
		Edge<String> e2 = new Edge<String>(vA,vF,0);
		edgeList.add(e0);
		edgeList.add(e1);
		edgeList.add(e2);
		
		edgeList = vB.getEdgeList();
		e0 = new Edge<String>(vB,vC,0);
		edgeList.add(e0);
		
		edgeList = vD.getEdgeList();
		e0 = new Edge<String>(vD,vA,0);
		e1 = new Edge<String>(vD,vE,0);
		edgeList.add(e0);
		edgeList.add(e1);
		
		edgeList = vE.getEdgeList();
		e0 = new Edge<String>(vE,vF,0);
		edgeList.add(e0);
		
		edgeList = vF.getEdgeList();
		e0 = new Edge<String>(vF,vG,0);
		edgeList.add(e0);

		edgeList = vG.getEdgeList();
		e0 = new Edge<String>(vG,vA,0);
		e1 = new Edge<String>(vG,vC,0);
		edgeList.add(e0);
		edgeList.add(e1);
		
		Graph<String> g = new Graph<String>();
		
		ArrayList<Vertex<String>> vertexs = g.getVertexs();
		vertexs.add(vA);
		vertexs.add(vB);
		vertexs.add(vC);
		vertexs.add(vD);
		vertexs.add(vE);
		vertexs.add(vF);
		vertexs.add(vG);
		
		g.dfs(vA);
	}
}
