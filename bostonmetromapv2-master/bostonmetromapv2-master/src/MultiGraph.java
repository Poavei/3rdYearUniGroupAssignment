import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiGraph implements IMultiGraph {
    private final Map<Node, List<Edge>> CONNECTIONS;
    private static MultiGraph INSTANCE;

    public MultiGraph(){
        CONNECTIONS = new HashMap<>();
    }
    public static MultiGraph getInstance(){
        if(INSTANCE == null){
            INSTANCE = new MultiGraph();
        }
        return INSTANCE;
    }
    public void initialise() {
        ///run metro map parser
        try{
            MetroMapParser mmparser = new MetroMapParser();
            mmparser.run();
        }
        catch(Exception e){
            System.out.println("METRO MAP PARSER FAILED.");
        }
    }
    //variation of getNode and isNode used to replace with station inputs
    public boolean isNode(String search){
        for (Node node : CONNECTIONS.keySet()){
            if(node.getName().toUpperCase().equals(search.toUpperCase())){
                return true;
            }
        }
        return false;
    }
    public INode getNode(String search){
        for(INode node : CONNECTIONS.keySet()){
            if(((Node) node).getName().toUpperCase().equals(search.toUpperCase())){
                return node;
            }
        }
        return null;
    }
    public List<Edge> getConnections(Node node){
        return CONNECTIONS.get(node);
    }


    @Override
    public ArrayList<IEdge> findPath(INode start, INode end) {
        //implementing bfs
        System.out.println("Finding path from: " + ((Node)start).getName() + " to: " + ((Node) end).getName());
        ArrayList<IEdge> current = new ArrayList<>();
        if(start == end){
            return current;
        }
        ArrayList<ArrayList<IEdge>> agenda = new ArrayList<>();
        for (IEdge e: CONNECTIONS.get((Node)start)){
            ArrayList<IEdge> arr = new ArrayList<IEdge>();
            arr.add(e);
            agenda.add(arr);
        }
        boolean found = false;
        while(!found && !agenda.isEmpty()){
            current = agenda.remove(0);
            System.out.println("Currently at: " + ((Node) current.get(current.size() -1).getTo()).getName() + " trying to get to: " + ((Node) end).getName());
            if(((Node)(current.get(current.size() -1)).getTo()).getName().equals(((Node) end).getName())){
                System.out.println("PATH FOUND!");
                found = true;
            }
            System.out.println("Size of current: " + current.size());
            List<Edge> next = getConnections(((Node) current.get(current.size() -1).getTo()));
            for (Edge e: next){
                if(!((current.get(current.size()-1).getFrom() == e.getTo()))){
                    ArrayList<IEdge> copy = new ArrayList<>();
                    for(IEdge n: current){
                        copy.add(n);
                    }
                    copy.add(e);
                    agenda.add(copy);
                }
            }
        }
        return current;
    }
    public String connectionInfo(Edge edge){
        return (((Node) edge.getFrom()).getName() + ((Node) edge.getTo()).getName() + edge.getName());
    }
    @Override
    public INode getNode(int search) {
        for(Node node : CONNECTIONS.keySet()){
            if(node.getID() == search){
                return node;
            }
        }
        return null;
    }
    @Override
    public void addEdge(IEdge e) {
        Edge edge = (Edge) e;
        Node from = (Node) edge.getFrom();
        if(!CONNECTIONS.containsKey(from)){
            ArrayList<Edge> edges = new ArrayList<>();
            edges.add(edge);
            CONNECTIONS.put(from, edges);
        }
        else{
            List<Edge> edges = CONNECTIONS.get(from);
            for (Edge aEdge : edges){
                if(connectionInfo(aEdge).equals(connectionInfo(edge))){
                    return;
                }
            }
            edges.add(edge);
            CONNECTIONS.put(from, edges);
        }
    }
    @Override
    public void addNode(INode n) {
        if (!isNode(((Node) n).getName())){
            CONNECTIONS.put((Node) n, new ArrayList<Edge>());
        }
    }
}