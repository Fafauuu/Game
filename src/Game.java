import javax.swing.JFrame;
import java.awt.*;


public class Game {

    public static void main(String[] args) throws InterruptedException {
        View view = new View();
        view.setEmptyField(view.size);
//        view.paint();
        view.setLocation(3200,400);
        view.setSize(600,600);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        Action action = new Action();
//        view.printNumbers();
//        GameObject warrior1 = new GameObject(0,0, ID.Ally);


//        action.placeWarrior(warrior.createKnights(3));
//        action.placeWarrior(warrior.createArchers(4));
//        action.placeWarrior(warrior.createKnights(8));
//        action.placeWarrior(warrior.createArchers(3));

//        action.placeWarrior(warrior1);

        for (int x = 0; x < 10; x++) {
            System.out.println(view.newField.size());
            System.out.println(view.newField.get(x).size());
            Thread.sleep(1000);
            view.setVisible(true);
            view.repaint();
            view.printNumbers();
            System.out.println(x);
        }
    }
}

