import javax.swing.JFrame;


public class Game {

    public static void main(String[] args) throws InterruptedException {
        View view = new View();
        view.setLocation(200,200);
        view.setSize(600,600);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Action action = new Action();
        view.printNumbers();
        GameObject warrior1 = new GameObject(0,0, ID.Ally);


//        action.placeWarrior(warrior.createKnights(3));
//        action.placeWarrior(warrior.createArchers(4));
//        action.placeWarrior(warrior.createKnights(8));
//        action.placeWarrior(warrior.createArchers(3));

//        action.placeWarrior(warrior1);

        for (int x = 0; x < 10; x++) {
            Thread.sleep(1000);
            view.setVisible(true);
            view.repaint();
            view.printNumbers();
        }
    }
}

