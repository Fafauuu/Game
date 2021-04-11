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

        list.addAll(knight.createAllyObjects(1,1,1,6));
        list.addAll(axeMan.createAllyObjects(1,1,7,8));

        list.addAll(knight.createEnemyObjects(6,6,0,4));
        list.addAll(axeMan.createEnemyObjects(6,6,6,8));

        action.setList(view.getField());
        action.printField();
        action.placeWarriors(list);

        view.setVisible(true);

        for (int x = 0, i = 9; x < 11; x++) {
            Thread.sleep(1000);
            action.removeDeadWarriors();
            view.revalidate();
            view.repaint();
//            view.printNumbers();
            if(x == 2){
                action.move(view.field.get(1).get(2), "up");
            }
            if(x == 4){
                action.move(view.field.get(0).get(2), "up");
            }
            if(x == 6){
                action.move(view.field.get(0).get(2), "down");
            }
            if(x == 8){
                action.move(view.field.get(1).get(8), "right");
            }

            view.field.get(1).get(1).setHp(10 * i--);

            System.out.println(x);
        }
    }
}

