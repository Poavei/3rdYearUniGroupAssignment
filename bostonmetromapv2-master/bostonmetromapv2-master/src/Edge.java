public class Edge implements IEdge {
    private Node TO;
    private Node FROM;
    private String NAME;

    public Edge(Node TO, Node FROM, String NAME){
        this.TO = TO;
        this.FROM = FROM;
        this.NAME = NAME;
    }
    @Override
    public INode getTo() {
        return TO;
    }

    @Override
    public INode getFrom() {
        return FROM;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
