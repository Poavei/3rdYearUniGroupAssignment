public class Node implements INode {

    private int ID;
    private String NAME;

    public Node(int ID, String NAME){
        this.ID = ID;
        this.NAME = NAME;
    }
    @Override
    public int getID() {
        return ID;
    }
    public String getName(){
        return NAME;
    }
}
