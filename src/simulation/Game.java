package simulation;

import java.util.ArrayList;

public class Game {

    public static void main(String[] args) throws InterruptedException {
        View view = new View();
        view.setSize(10);
        view.setEmptyField();
        GameObject gameObject = new GameObject();
        Knight knight = new Knight();
        AxeMan axeMan = new AxeMan();
        Action action = new Action();

        ArrayList<GameObject> list = new ArrayList<>(0);

        list.addAll(knight.createAllyObjects(0, 1, 0, 2));
        list.addAll(axeMan.createAllyObjects(0, 1, 4, 7));
        list.addAll(knight.createEnemyObjects(7, 7, 0, 1));
        list.addAll(axeMan.createEnemyObjects(7, 7, 6, 9));

        action.setList(view.getField());
        action.printField();
        action.placeWarriors(list);

        view.setVisible(true);

        for (int x = 0, i = 9; x < 100; x++) {
            Thread.sleep(1000);


            action.scanForEnemy(list, x);

            action.removeDeadWarriors(list);
            view.revalidate();
            view.repaint();
            view.printNumbers();
            action.removeDeadWarriors(list);

            System.out.println(x);
        }
    }
}

