import java.util.ArrayList;

public class Model {
    private final MultiGraph multiGraph;

    public Model (){
        multiGraph = MultiGraph.getInstance();
        multiGraph.initialise();
    }
    public boolean isRoute(String curr, String dest){
        return (multiGraph.isNode(curr) && multiGraph.isNode(dest));
    }
    public String getRoute(String curr, String dest){
        String route = "Travel Route: " + curr + " -> " + dest + "\n---------------------------";
        System.out.println("Got to here!");
        ArrayList<IEdge> theRoute = multiGraph.findPath(multiGraph.getNode(curr), multiGraph.getNode(dest));
        System.out.println("Size of route: " + theRoute.size());
        int index = 0;
        if(theRoute.size() == 0){
            route += "\n + You're already there!";
        }
        else {
            for (IEdge e : theRoute) {
                if (index == (theRoute.size() - 1)) {
                    route += "\n Finally, take the " + e.getName() + " line from " + ((Node) e.getFrom()).getName() + " to " + ((Node) e.getTo()).getName();
                    route += "\n---------------------------";
                    route += "\n You have reached your destination.";
                    break;
                } else {
                    route += "\n Take the " + e.getName() + " line from " + ((Node) e.getFrom()).getName() + " to " + ((Node) e.getTo()).getName();
                    index++;
                }
            }
        }

        return route;

    }
}
