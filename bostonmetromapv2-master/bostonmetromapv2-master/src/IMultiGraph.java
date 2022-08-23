import java.util.ArrayList;

interface IMultiGraph{
    INode getNode(int search);
    void addNode(INode n);
    void addEdge(IEdge e);
    ArrayList<IEdge> findPath(INode start, INode end);
}