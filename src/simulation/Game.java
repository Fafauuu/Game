package simulation;

import javax.swing.JFrame;

public class Game {

    public static void main(String[] args) throws InterruptedException {
        View view = new View();
        view.setSize(10);
        view.setEmptyField();
        GameObject gameObject = new GameObject();
        Action action = new Action();

        action.setList(view.getField());
        action.printField();
        action.placeWarriors(gameObject.createObjects(6, 1, 1));

        for (int x = 0, i = 9; x < 10; x++) {
            Thread.sleep(1000);
            view.setVisible(true);
            view.repaint();
            view.printNumbers();


            view.field.get(1).get(1).setHp(10 * i--);

            System.out.println(x);
        }
    }
}

