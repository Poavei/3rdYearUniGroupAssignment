public class Driver {
    public static void main(String[] args){
        MultiGraph map = MultiGraph.getInstance();
        map.initialise();
        Node node1 = (Node) map.getNode(29);
        Node node2 = (Node) map.getNode(43);
        for (IEdge e : map.findPath(node1, node2)){
            System.out.println("Take line from: " + ((Node) e.getFrom()).getName() + " to: " + ((Node) e.getTo()).getName());
        }
        /*Path path = new Path(station1);
        Path copy = new Path(path);
        path.printPath();
        path.moveAlong(path.getConnections().get(0));
        path.printPath();
        path.moveAlong(path.getConnections().get(0));
        path.printPath();
        copy.printPath();

         */
        /*for(Line l: map.getConnections(station1)){
            System.out.println("Accepting line from" + ((Station) l.getFrom()).getName() + " to " + ((Station) l.getTo()).getName());
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        for(Line l: path.getConnections()){
            System.out.println("Accepting line from" + ((Station) l.getFrom()).getName() + " to " + ((Station) l.getTo()).getName());
        }

         */

        //map.testPathState(path);
        /*Path path2 = new Path(path);
        path.moveAlong(path.getConnections().get(0));
        path.printCurrent();
        path2.printCurrent();

         */
    }
}
