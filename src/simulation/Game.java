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

//        list.addAll(knight.createAllyObjects(1,3,1,6));
//        list.addAll(axeMan.createAllyObjects(1,3,7,8));
//
//        list.addAll(knight.createEnemyObjects(6,7,0,4));
//        list.addAll(axeMan.createEnemyObjects(6,7,6,8));

        list.addAll(knight.createAllyObjects(1,1,1,1));
        list.addAll(knight.createEnemyObjects(1,1,4,4));
        list.addAll(knight.createEnemyObjects(7,7,9,9));

        action.setList(view.getField());
        action.printField();
        action.placeWarriors(list);

        view.setVisible(true);

        for (int x = 0, i = 9; x < 21; x++) {
            Thread.sleep(1000);


            action.scanForEnemy(list, x);

            for (int j = 0; j < list.size(); j++) {
                System.out.print(list.get(j).getId() + " ");
                System.out.println(list.get(j).getStatus());
            }

            action.removeDeadWarriors(list);
            view.revalidate();
            view.repaint();
            view.printNumbers();



//            System.out.println(view.field.get(1).get(1).getStatus());
//            System.out.println(view.field.get(1).get(2).getStatus());

//            view.field.get(1).get(1).setHp(10 * i--);

            System.out.println(x);
        }
    }
}

