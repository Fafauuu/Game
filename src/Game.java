import javax.swing.JFrame;
import java.awt.*;
import java.util.ArrayList;


public class Game {

    public static void main(String[] args) throws InterruptedException {
        View view = new View();
        view.setEmptyField(view.size);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameObject gameObject = new GameObject();
        Action action = new Action();
//        view.printNumbers();



        ArrayList<GameObject> list;
        list = gameObject.createObjects(6,0,0);

        for (int i = 0; i < list.size(); i++) {
            view.field.get(0).set(i, list.get(i));
        }


//        action.placeWarriors(list);

//        view.field.get(0).addAll(list);

        for (int x = 0, i = 9; x < 10; x++) {
//            System.out.println(view.newField.size());
//            System.out.println(view.newField.get(x).size());
            Thread.sleep(1000);
            view.setVisible(true);
            view.repaint();
            view.printNumbers();


//            System.out.println(view.newField.get(0).get(1).getHp());


            view.field.get(0).get(1).setHp(10*i--);


//            for (int i = 0; i < list.size(); i++) {
//                System.out.println(list.get(i).getId());
//            }
            System.out.println(x);
        }
    }
}

