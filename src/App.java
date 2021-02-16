import util.*;

public class App {

    private static Screen screen;
    public static void main(String[] args){
        screen = new Screen(80,20);
        FirstPersonView fpv = new FirstPersonView(60,20);
        fpv.leftWall(1, screen);
        fpv.leftWall(2, screen);

        fpv.rightWall(1, screen);
        

        screen.draw(); 
    }
}
