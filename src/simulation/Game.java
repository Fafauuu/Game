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

        ArrayList<GameObject> list = new ArrayList<>();

        list.addAll(knight.createAllyObjects(0,3,1,6));
        list.addAll(axeMan.createAllyObjects(0,3,7,9));

        list.addAll(knight.createEnemyObjects(6,8,0,4));
        list.addAll(axeMan.createEnemyObjects(6,8,6,8));

        action.setList(view.getField());
        action.printField();
        action.placeWarriors(list);

        view.setVisible(true);

        for (int x = 0, i = 9; x < 10; x++) {
            Thread.sleep(2000);
            view.revalidate();
            view.repaint();
//            view.printNumbers();


            view.field.get(1).get(1).setHp(10 * i--);

            System.out.println(x);
        }
    }
}

