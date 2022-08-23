import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * This class reads a text description of a metro subway system
 * and generates a graph representation of the metro.
 *
 * Students should feel free to modify this code as needed
 *  to complete this exercise.
 *
 *<p>
 *
 * The grammar for the file is described below in BNF. A typical line
 * in the file looks like this :
 *
 * <code> 20 NorthStation   Green 19 22  Orange 15 22  </code>
 *
 * where :
 *         20 is the StationID
 *         NorthStation is the StationName
 *         Green 19 22
 *                  Green is the LineName
 *                  19 is the StationID of the outbound station
 *                  22 is the StationID of the inbound station
 *         Orange 15 22 is a LineID in which :
 *                  Orange is the LineName
 *                  15 is the StationID of the outbound station
 *                  22 is the StationID of the inbound station
 *
 *         Therefore, NorthStation has two outgoing lines.
 *
 *  note : 0 denotes the end of a line : i.e. in this case,
 *  OakGrove would be at the end of the line, as there is no other outbound
 *  station.
 *
 *<p>
 * metro-map ::= station-spec* <BR>
 * station-spec ::= station-id station-name station-line+ <BR>
 * station-id ::= (positive integer) <BR>
 * station-name ::= string <BR>
 * station-line ::= line-name station-id station-id <BR>
 *
 */

public class MetroMapParser
{

    private final BufferedReader fileInput;
    private final String filename = "bostonmetromapv2-master\\src\\BostonMetro.txt";
    private final MultiGraph map;


    public void run()
    {
        try
        {
            MetroMapParser mmp = new MetroMapParser();
            mmp.generateGraphFromFile();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    /**
     * @effects: creates a new parser that will read from the file
     * filename unless the file does not exist. The filename should specify
     * the exact location of the file. This means it should be something like
     * /mit/$USER/6.170/ex3/bostonmetro.txt
     *
     *
     * @throws IOException if there <tt>filename</tt> cannot be read
     *
     * @returns a new MetroMapParser that will parse the file filename
     */

    public MetroMapParser() throws IOException
    {
        //a buffered reader reads line by line, returning null when file is done
        fileInput = new BufferedReader(new FileReader(filename));
        map = MultiGraph.getInstance();
    }

    /**
     * @effects: parses the file, and generates a graph from it, unless there
     * is a problem reading the file, or there is a problem with the format of the
     * file.
     *
     * @throws IOException if there is a problem reading the file
     * @throws  BadFileException if there is a problem with the format of the file
     *
     * @returns the Graph generated by the file
     */

    //USE TO STORE INFO IF LINE FAILED TO BE CREATED TO BE USED AT END OF CYCLE
    class failedLine{
        int inBoundID;
        int outBoundID;
        String name;
        failedLine(int inID, int outID, String aName){
            inBoundID = inID;
            outBoundID = outID;
            name = aName;
        }
        public int getInBoundID(){return inBoundID;}
        public int getOutBoundID(){return outBoundID;}
        public String getName(){return name;}
    }
    public  void  generateGraphFromFile()
            throws Exception
    {

        String line = fileInput.readLine();
        StringTokenizer st;
        String stationID;
        String stationName;
        String lineName;
        String outboundID, inboundID;
        ArrayList<failedLine> failures = new ArrayList<>();

        while(line != null)
        {

            //STUDENT :
            //
            //in this loop, you must collect the information necessary to
            //construct your graph, and you must construct your graph as well.
            //how and where you do this will depend on the design of your graph.
            //


            //StringTokenizer is a java.util Class that can break a string into tokens
            // based on a specified delimiter.  The default delimiter is " \t\n\r\f" which
            // corresponds to the space character, the tab character, the newline character,
            // the carriage-return character and the form-feed character.
            st = new StringTokenizer(line);

            //We want to handle empty lines effectively, we just ignore them!
            if(!st.hasMoreTokens())
            {
                line = fileInput.readLine();
                continue;
            }

            //from the grammar, we know that the Station ID is the first token on the line
            stationID = st.nextToken();

            if(!st.hasMoreTokens())
            {
                throw new BadFileException("no station name");
            }

            //from the grammar, we know that the Station Name is the second token on the line.
            stationName = st.nextToken();

            if(!st.hasMoreTokens())
            {
                throw new BadFileException("station is on no lines");
            }
            Node node = new Node(Integer.parseInt(stationID), stationName);
            map.addNode(node);
            while(st.hasMoreTokens())
            {
                lineName = st.nextToken();

                if(!st.hasMoreTokens())
                {
                    throw new BadFileException("poorly formatted line info");
                }

                outboundID = st.nextToken();
                Node to = (Node) map.getNode(Integer.parseInt(outboundID));
                if(to != null){
                    Edge edge1 = new Edge(to , node, lineName);
                    Edge edge2 = new Edge(node, to, lineName);
                    map.addEdge(edge1);
                    map.addEdge(edge2);
                }
                else if(outboundID.equals("0")){
                    //do nothing if id == 0 a no station exists
                }
                else{
                    failures.add(new failedLine(node.getID(), Integer.parseInt(outboundID), lineName));
                }

                if(!st.hasMoreTokens())
                {
                    throw new BadFileException("poorly formatted adjacent stations");
                }

                inboundID = st.nextToken();
                Node from = (Node) map.getNode(Integer.parseInt(inboundID));
                if(from != null){
                    Edge edge1 = new Edge(from , node, lineName);
                    Edge edge2 = new Edge(node, from, lineName);
                    map.addEdge(edge1);
                    map.addEdge(edge2);
                }
                else if(inboundID.equals("0")){
                    //do nothing if the id == 0 as no station exists with that id
                }
                else{
                    failures.add(new failedLine(node.getID(), Integer.parseInt(inboundID), lineName));
                }
            }
            line = fileInput.readLine();
        }
        for(failedLine fl: failures){
            Node to = (Node) map.getNode(fl.getInBoundID());
            Node from = (Node) map.getNode(fl.getOutBoundID());
            if((to == null)){
                System.out.println(fl.getInBoundID());
                continue;
            }
            if((from == null)){
                System.out.println(fl.getOutBoundID());
                continue;
            }
            Edge edge1 = new Edge(to , from, fl.getName());
            Edge edge2 = new Edge(from, to, fl.getName());
            map.addEdge(edge1);
            map.addEdge(edge2);
        }
    }


}