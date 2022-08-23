public class Controller {


    private static Model model;
    private static View view;
    private static Controller instance;

    public static void main(String[] args) {
        model = new Model();
        view = new View();
    }
    public static Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }
    public void getRoute(){
        String current = view.getCurr();
        String destination = view.getDest();
        if(model.isRoute(current, destination)) {
            view.returnRouteString(model.getRoute(current, destination));

        }
        else{
            view.noStationError();
        }
    }
}
