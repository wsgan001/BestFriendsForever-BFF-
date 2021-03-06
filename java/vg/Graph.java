package vg;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Labeled Version Graph class
 * 
 * @author ksemer
 */
public class Graph implements Serializable {

	private static final long serialVersionUID = 1L;
	// =================================================================
	private Map<Integer, Node> nodes;
	// =================================================================

	/**
	 * Constructor
	 * 
	 * @throws IOException
	 */
	public Graph() {
		nodes = new HashMap<Integer, Node>();
	}

	/**
	 * Add node in LVG
	 * 
	 * @param node
	 */
	public void addNode(int node) {
		if (nodes.get(node) == null) {
			nodes.put(node, new Node(node));
		}
	}

	/**
	 * Get node object with id = nodeID
	 * 
	 * @param nodeID
	 * @return
	 */
	public Node getNode(int nodeID) {
		return nodes.get(nodeID);
	}

	/**
	 * Add edge in LVG
	 * 
	 * @param src
	 * @param trg
	 * @param time
	 */
	public void addEdge(int src, int trg, int time) {
		nodes.get(src).addEdge(nodes.get(trg), time);
	}

	/**
	 * Return the version graph nodes
	 * 
	 * @return
	 */
	public Collection<Node> getNodes() {
		return nodes.values();
	}

	/**
	 * Return version graph nodes as a map
	 * 
	 * @return
	 */
	public Map<Integer, Node> getNodesAsMap() {
		return this.nodes;
	}

	/**
	 * Removes node with id nodeID
	 * 
	 * @param nodeID
	 */
	public void removeNode(int nodeID) {
		nodes.remove(nodeID);
	}

	/**
	 * Graph Size
	 * 
	 * @return
	 */
	public int size() {
		return nodes.size();
	}

	/**
	 * Returns true if graph is empty
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return nodes.size() == 0;
	}

	/**
	 * This method makes a "deep clone" of Graph it is given.
	 */
	public static Graph deepClone(Object object) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			oos.flush();
			oos.close();
			baos.close();
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (Graph) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}