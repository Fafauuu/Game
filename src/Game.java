import javax.swing.JFrame;
import java.awt.*;
import java.util.ArrayList;


public class Game {

    public static void main(String[] args) throws InterruptedException {
        View view = new View();
        view.setEmptyField(view.size);
        view.setLocation(3200,400);
        view.setSize(600,600);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Action action = new Action();
        GameObject gameObject = new GameObject();
//        view.printNumbers();



        ArrayList<GameObject> list= new ArrayList<>();
        list = gameObject.createObjects(6,0,0);
        for (int i = 0; i < list.size(); i++) {
            view.newField.get(0).set(i, list.get(i));
        }



//        action.placeWarriors(list);
//        action.placeWarriors(warrior.createArchers(4));
//        action.placeWarriors(warrior.createKnights(8));
//        action.placeWarriors(warrior.createArchers(3));

//        action.placeWarriors(warrior1);

        for (int x = 0; x < 10; x++) {
            System.out.println(view.newField.size());
            System.out.println(view.newField.get(x).size());
            Thread.sleep(1000);
            view.setVisible(true);
            view.repaint();
            view.printNumbers();
//            for (int i = 0; i < list.size(); i++) {
//                System.out.println(list.get(i).getId());
//            }
            System.out.println(x);
        }
    }
}

