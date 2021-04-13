package simulation;

import java.util.ArrayList;

public class Game {

    public static void main(String[] args) throws InterruptedException {
        View view = new View();
        view.setSize(10);
        view.setEmptyField();
        Knight knight = new Knight();
        AxeMan axeMan = new AxeMan();
        Action action = new Action();

        ArrayList<GameObject> list = new ArrayList<>(0);


//        //Example of changing direction to get closer
//        list.addAll(knight.createAllyObjects(0, 1, 0, 0));
//        list.addAll(knight.createEnemyObjects(7, 7, 1, 1));


//        //Example of charging
//        list.addAll(knight.createAllyObjects(0,0,0,0));
//        list.addAll(knight.createAllyObjects(0,0,6,6));
//        list.addAll(knight.createEnemyObjects(6,6,0,0));
//        list.addAll(knight.createEnemyObjects(6,6,7,7));


        //Example of some greater battle
        list.addAll(knight.createAllyObjects(1,2,0,3));
        list.addAll(axeMan.createAllyObjects(0,2,6,8));
        list.addAll(axeMan.createEnemyObjects(6,8,1,4));
        list.addAll(knight.createEnemyObjects(6,9,7,8));

//        //Example of differences in stats
//        list.addAll(knight.createAllyObjects(0,0,0,0));
//        list.addAll(knight.createAllyObjects(0,0,2,2));
//        list.addAll(axeMan.createAllyObjects(0,0,4,4));
//        list.addAll(knight.createEnemyObjects(3,3,0,0));
//        list.addAll(axeMan.createEnemyObjects(3,3,2,2));
//        list.addAll(axeMan.createEnemyObjects(3,3,4,4));


        action.setList(view.getField());
        action.printField();
        action.placeWarriors(list);

        view.setVisible(true);

        for (int x = 0, i = 9; x < 101; x++) {
            Thread.sleep(1000);

            System.out.println(list.size());

            action.scanForEnemy(list, x);

            action.removeDeadWarriors(list);
            view.revalidate();
            view.repaint();
            view.printNumbers();
            action.removeDeadWarriors(list);

            System.out.println(x+1);

            if (action.endSimulation(list) == true) break;
        }
    }
}

