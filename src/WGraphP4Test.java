import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.LinkedList;

/** JUnit tests for the MaxPriorityQueue interface.
 */
public class WGraphP4Test {

    static WGraphP4<Integer> intGraph;
    static WGraphP4<String> strGraph;
    static Integer[] iray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; //size = 11
    static String[] sray = {"zro", "one", "two", "tre", "for", "fyv", "six", "svn", "ate", "nyn", "ten"};

    @BeforeClass
    public static void init() {
    	
    }


    @Before
    public void setup() {
    	//fresh graphes for each test
		intGraph = new WGraphP4<>();
    	strGraph = new WGraphP4<>();
    }






//--------------------  numEdges() --------------------
    @Test
    public void numEdgesNew() {
        assertEquals(0, intGraph.numEdges());
        assertEquals(0, strGraph.numEdges());
    }

    @Test
    public void numEdgesAdd() {
    	//check after adding edge
		GVertex<Integer> gInt1 = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr1 = new GVertex<>("one", strGraph.nextID());
        GVertex<Integer> gInt2 = new GVertex<>((Integer) 2, intGraph.nextID());
        GVertex<String> gStr2 = new GVertex<>("two", strGraph.nextID());
		double weight = (double) 1;

		WEdge<Integer> wInt = new WEdge<>(gInt1, gInt2, weight);
		WEdge<String> wStr = new WEdge<>(gStr1, gStr2, weight);

    	intGraph.addEdge(wInt);
    	strGraph.addEdge(wStr);

    	assertEquals(1, intGraph.numEdges());
    	assertEquals(1, strGraph.numEdges());

    	//dont increment if duplicate edge added
    	intGraph.addEdge(wInt);
    	strGraph.addEdge(wStr);

    	assertEquals(1, intGraph.numEdges());
    	assertEquals(1, strGraph.numEdges());

    }

    @Test
    public void numEdgesRemove() {
    	//check after removing edge
    }


//--------------------  hasData() --------------------

    @Test
    public void hasVertexNew() {
    	GVertex<Integer> gInt = new GVertex<>((Integer) 1, 1);
        GVertex<String> gStr = new GVertex<>("one", 1);
    	assertFalse(intGraph.hasData((Integer) 1));
    	assertFalse(strGraph.hasData("one"));
    }

    @Test
    public void hasVertexAdd() {
    	GVertex<Integer> gInt = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr = new GVertex<>("one", strGraph.nextID());
        intGraph.addVertex(gInt);
        strGraph.addVertex(gStr);
    	assertTrue(intGraph.hasData((Integer) 1));
    	assertTrue(strGraph.hasData("one"));

    	intGraph.addVertex((Integer) 2);
    	strGraph.addVertex("two");
    	assertTrue(intGraph.hasData((Integer) 1));
    	assertTrue(strGraph.hasData("one"));

    	gInt = new GVertex<>((Integer) 2, 1);
        gStr = new GVertex<>("two", 1);
    	assertTrue(intGraph.hasData((Integer) 2));
    	assertTrue(strGraph.hasData("two"));

    	assertFalse(intGraph.hasData((Integer) 3));
    	assertFalse(strGraph.hasData("three"));
    }







//--------------------  numVerts() --------------------
	@Test
    public void numVertsNew() {
        assertEquals(0, intGraph.numVerts());
        assertEquals(0, strGraph.numVerts());
    }

    @Test
    public void numVertsAdd() {
    	//check after adding vertex
    	intGraph.addVertex((Integer) 0);
    	strGraph.addVertex("zro");
    	assertEquals(1, intGraph.numVerts());
        assertEquals(1, strGraph.numVerts());

        GVertex<Integer> gInt = new GVertex<>((Integer) 1, 1);
        GVertex<String> gStr = new GVertex<>("one", 1);
        intGraph.addVertex(gInt);
        strGraph.addVertex(gStr);
        assertEquals(2, intGraph.numVerts());
        assertEquals(2, strGraph.numVerts());
    }

    @Test
    public void numVertsAddContained() {
        GVertex<Integer> gInt = new GVertex<>((Integer) 1, 1);
        GVertex<String> gStr = new GVertex<>("one", 1);
        intGraph.addVertex(gInt);
        strGraph.addVertex(gStr);
    	assertEquals(1, intGraph.numVerts());
        assertEquals(1, strGraph.numVerts());

        intGraph.addVertex(gInt);
        strGraph.addVertex(gStr);
    	assertEquals(1, intGraph.numVerts());
        assertEquals(1, strGraph.numVerts());
    }

    @Test
    public void numVertsRemove() {
    	//check after removing vertex
    	
    	intGraph.addVertex((Integer) 0);
    	strGraph.addVertex("zro");
    	assertEquals(1, intGraph.numVerts());
        assertEquals(1, strGraph.numVerts());
        
    }





//--------------------  nextID() --------------------
	@Test
    public void nextIDNew() {
        assertEquals(0, intGraph.nextID());
        assertEquals(0, strGraph.nextID());
    }

    @Test
    public void nextIDAdd() {
	   	//check ID after adding vertex
	    GVertex<Integer> gInt = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr = new GVertex<>("one", strGraph.nextID());
        intGraph.addVertex(gInt);
        strGraph.addVertex(gStr);
        assertEquals(1, intGraph.nextID());
        assertEquals(1, strGraph.nextID());
    	}




//--------------------  addVertex(VT d) --------------------
    @Test
    public void addVertexNew() {
    	assertTrue(intGraph.addVertex((Integer) 0));
    	assertTrue(strGraph.addVertex("zro"));
    	assertTrue(intGraph.hasData((Integer) 0));
    	assertTrue(strGraph.hasData("zro"));

    }



//--------------------  addVertex(GVertex<VT> v) --------------------
    @Test
    public void addVertexObjectNew() {
    	GVertex<Integer> gInt = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr = new GVertex<>("one", strGraph.nextID());
        assertTrue(intGraph.addVertex(gInt));
        assertTrue(strGraph.addVertex(gStr));
        assertTrue(intGraph.hasData((Integer) 1));
    	assertTrue(strGraph.hasData("one"));

    }

    @Test
    public void addVertexObjectContained() {
    	GVertex<Integer> gInt = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr = new GVertex<>("one", strGraph.nextID());

    	assertTrue(intGraph.addVertex(gInt));
    	assertTrue(strGraph.addVertex(gStr));

    	GVertex<Integer> gInt2 = new GVertex<>((Integer) 2, intGraph.nextID());
        GVertex<String> gStr2 = new GVertex<>("two", strGraph.nextID());

    	assertTrue(intGraph.addVertex(gInt2));
    	assertTrue(strGraph.addVertex(gStr2));

    	//vertices with the same ID as other vertices already contained cannot be added
    	assertFalse(intGraph.addVertex(gInt));
    	assertFalse(strGraph.addVertex(gStr));
    }




//--------------------  addEdge(WEdge e) --------------------
	@Test
	public void addEdgeObjectFromVertexObject() {
		GVertex<Integer> gInt1 = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr1 = new GVertex<>("one", strGraph.nextID());
        GVertex<Integer> gInt2 = new GVertex<>((Integer) 2, intGraph.nextID());
        GVertex<String> gStr2 = new GVertex<>("two", strGraph.nextID());
		double weight = (double) 1;

		WEdge<Integer> wInt = new WEdge<>(gInt1, gInt2, weight);
		WEdge<String> wStr = new WEdge<>(gStr1, gStr2, weight);

		assertTrue(intGraph.addEdge(wInt));
        assertTrue(strGraph.addEdge(wStr));

        GVertex<Integer> gInt3 = new GVertex<>((Integer) 3, intGraph.nextID());
        GVertex<String> gStr3 = new GVertex<>("three", strGraph.nextID());
        GVertex<Integer> gInt4 = new GVertex<>((Integer) 3, intGraph.nextID());
        GVertex<String> gStr4 = new GVertex<>("three", strGraph.nextID());

		WEdge<Integer> wInt2 = new WEdge<>(gInt3, gInt4, weight);
		WEdge<String> wStr2 = new WEdge<>(gStr3, gStr4, weight);

		assertTrue(intGraph.addEdge(wInt2));
        assertTrue(strGraph.addEdge(wStr2));

        //edge already added, should return false
        assertFalse(intGraph.addEdge(wInt));
        assertFalse(strGraph.addEdge(wStr));
	}


//--------------------  addEdge(GVertex<VT> v, GVertex<VT> u, double weight) --------------------


//--------------------  deleteEdge() --------------------
	@Test
	public void removeEdgeContained() {
		GVertex<Integer> gInt1 = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr1 = new GVertex<>("one", strGraph.nextID());
        GVertex<Integer> gInt2 = new GVertex<>((Integer) 2, intGraph.nextID());
        GVertex<String> gStr2 = new GVertex<>("two", strGraph.nextID());
		double weight = (double) 1;

		WEdge<Integer> wInt = new WEdge<>(gInt1, gInt2, weight);
		WEdge<String> wStr = new WEdge<>(gStr1, gStr2, weight);

		intGraph.addEdge(wInt);
        strGraph.addEdge(wStr);

        assertTrue(intGraph.deleteEdge(gInt1, gInt2));
        assertTrue(strGraph.deleteEdge(gStr1, gStr2));

        //edge will have been deleted, should be false
        assertFalse(intGraph.deleteEdge(gInt1, gInt2));
        assertFalse(strGraph.deleteEdge(gStr1, gStr2));
	}


	@Test
	public void removeEdgeNotContained() {
		GVertex<Integer> gInt1 = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr1 = new GVertex<>("one", strGraph.nextID());
        GVertex<Integer> gInt2 = new GVertex<>((Integer) 2, intGraph.nextID());
        GVertex<String> gStr2 = new GVertex<>("two", strGraph.nextID());
		double weight = (double) 1;

		WEdge<Integer> wInt = new WEdge<>(gInt1, gInt2, weight);
		WEdge<String> wStr = new WEdge<>(gStr1, gStr2, weight);


		//edge not inserted, return false
        assertFalse(intGraph.deleteEdge(gInt1, gInt2));
        assertFalse(strGraph.deleteEdge(gStr1, gStr2));

		intGraph.addEdge(wInt);
        strGraph.addEdge(wStr);

        assertTrue(intGraph.deleteEdge(gInt1, gInt2));
        assertTrue(strGraph.deleteEdge(gStr1, gStr2));
	}



//--------------------  areAdjacent() --------------------

	@Test
	public void areAdjacentNew() {
		GVertex<Integer> gInt1 = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr1 = new GVertex<>("one", strGraph.nextID());
        GVertex<Integer> gInt2 = new GVertex<>((Integer) 2, intGraph.nextID());
        GVertex<String> gStr2 = new GVertex<>("two", strGraph.nextID());
		double weight = (double) 1;

		WEdge<Integer> wInt = new WEdge<>(gInt1, gInt2, weight);
		WEdge<String> wStr = new WEdge<>(gStr1, gStr2, weight);


		//edge not inserted, return false
        assertFalse(intGraph.areAdjacent(gInt1, gInt2));
        assertFalse(strGraph.areAdjacent(gStr1, gStr2));

		intGraph.addEdge(wInt);
        strGraph.addEdge(wStr);

        assertTrue(intGraph.areAdjacent(gInt1, gInt2));
        assertTrue(strGraph.areAdjacent(gStr1, gStr2));
	}

	@Test
	public void areAdjacentRemoved() {
		GVertex<Integer> gInt1 = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr1 = new GVertex<>("one", strGraph.nextID());
        GVertex<Integer> gInt2 = new GVertex<>((Integer) 2, intGraph.nextID());
        GVertex<String> gStr2 = new GVertex<>("two", strGraph.nextID());
		double weight = (double) 1;

		WEdge<Integer> wInt = new WEdge<>(gInt1, gInt2, weight);
		WEdge<String> wStr = new WEdge<>(gStr1, gStr2, weight);

		intGraph.addEdge(wInt);
        strGraph.addEdge(wStr);

        assertTrue(intGraph.areAdjacent(gInt1, gInt2));
        assertTrue(strGraph.areAdjacent(gStr1, gStr2));


        intGraph.deleteEdge(gInt1, gInt2);
        strGraph.deleteEdge(gStr1, gStr2);

       	//edge not inserted, return false
        assertFalse(intGraph.areAdjacent(gInt1, gInt2));
        assertFalse(strGraph.areAdjacent(gStr1, gStr2));

	}



//--------------------  neighbors() --------------------
	@Test
	public void neighborsAddEdge() {
		GVertex<Integer> gInt1 = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr1 = new GVertex<>("one", strGraph.nextID());
        GVertex<Integer> gInt2 = new GVertex<>((Integer) 2, intGraph.nextID());
        GVertex<String> gStr2 = new GVertex<>("two", strGraph.nextID());
		double weight = (double) 1;

		WEdge<Integer> wInt = new WEdge<>(gInt1, gInt2, weight);
		WEdge<String> wStr = new WEdge<>(gStr1, gStr2, weight);

		assertTrue(intGraph.addEdge(wInt));
        assertTrue(strGraph.addEdge(wStr));

        GVertex<Integer> gInt3 = new GVertex<>((Integer) 3, intGraph.nextID());
        GVertex<String> gStr3 = new GVertex<>("three", strGraph.nextID());
        GVertex<Integer> gInt4 = new GVertex<>((Integer) 3, intGraph.nextID());
        GVertex<String> gStr4 = new GVertex<>("three", strGraph.nextID());


        WEdge<Integer> wInt2 = new WEdge<>(gInt1, gInt3, weight);
		WEdge<String> wStr2 = new WEdge<>(gStr1, gStr3, weight);

		assertTrue(intGraph.addEdge(wInt2));
        assertTrue(strGraph.addEdge(wStr2));

        WEdge<Integer> wInt3 = new WEdge<>(gInt1, gInt4, weight);
		WEdge<String> wStr3 = new WEdge<>(gStr1, gStr4, weight);

		assertTrue(intGraph.addEdge(wInt3));
        assertTrue(strGraph.addEdge(wStr3));

        LinkedList<GVertex<Integer>> list = new LinkedList<>();
        list.add(gInt2);
        list.add(gInt3);
        list.add(gInt4);

        assertEquals(list, intGraph.neighbors(gInt1));
	}








	

	@Test
	public void neighborsRemoveEdge() {
		GVertex<Integer> gInt1 = new GVertex<>((Integer) 1, intGraph.nextID());
        GVertex<String> gStr1 = new GVertex<>("one", strGraph.nextID());
        GVertex<Integer> gInt2 = new GVertex<>((Integer) 2, intGraph.nextID());
        GVertex<String> gStr2 = new GVertex<>("two", strGraph.nextID());
		double weight = (double) 1;

		WEdge<Integer> wInt = new WEdge<>(gInt1, gInt2, weight);
		WEdge<String> wStr = new WEdge<>(gStr1, gStr2, weight);

		assertTrue(intGraph.addEdge(wInt));
        assertTrue(strGraph.addEdge(wStr));

        GVertex<Integer> gInt3 = new GVertex<>((Integer) 3, intGraph.nextID());
        GVertex<String> gStr3 = new GVertex<>("three", strGraph.nextID());
        GVertex<Integer> gInt4 = new GVertex<>((Integer) 3, intGraph.nextID());
        GVertex<String> gStr4 = new GVertex<>("three", strGraph.nextID());


        WEdge<Integer> wInt2 = new WEdge<>(gInt1, gInt3, weight);
		WEdge<String> wStr2 = new WEdge<>(gStr1, gStr3, weight);

		assertTrue(intGraph.addEdge(wInt2));
        assertTrue(strGraph.addEdge(wStr2));

        WEdge<Integer> wInt3 = new WEdge<>(gInt1, gInt4, weight);
		WEdge<String> wStr3 = new WEdge<>(gStr1, gStr4, weight);

		assertTrue(intGraph.addEdge(wInt3));
        assertTrue(strGraph.addEdge(wStr3));

        LinkedList<GVertex<Integer>> iList = new LinkedList<>();
        LinkedList<GVertex<String>> sList = new LinkedList<>();
        iList.add(gInt2);
        iList.add(gInt3);
        iList.add(gInt4);
        sList.add(gStr2);
        sList.add(gStr3);
        sList.add(gStr4);


        assertEquals(iList, intGraph.neighbors(gInt1));
        assertEquals(sList, strGraph.neighbors(gStr1));

        //delete ende and check neighbors
       	assertTrue(intGraph.deleteEdge(gInt1, gInt4));
        assertTrue(strGraph.deleteEdge(gStr1, gStr4));

        iList.remove(gInt4);
        sList.remove(gStr4);

        assertEquals(iList, intGraph.neighbors(gInt1));
        assertEquals(sList, strGraph.neighbors(gStr1));


	}










/*
//--------------------  degree() --------------------


//--------------------  areIncident() --------------------


//--------------------  allEdges() --------------------


//--------------------  allVertices() --------------------



//--------------------  depthFirst() --------------------


//--------------------  incidentEdges() --------------------



//--------------------  kruskals() --------------------





*/
}
