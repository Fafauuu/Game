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

        list.addAll(knight.createAllyObjects(1,1,0,0));
        list.addAll(knight.createEnemyObjects(1,1,9,9));
        list.addAll(knight.createEnemyObjects(7,7,1,1));

        action.setList(view.getField());
        action.printField();
        action.placeWarriors(list);

        view.setVisible(true);

        for (int x = 0, i = 9; x < 11; x++) {
            Thread.sleep(3000);
            action.removeDeadWarriors();
            view.revalidate();
            view.repaint();
//            view.printNumbers();
//            if(x == 1){
//                action.move(view.field.get(1).get(2), "up");
//            }
//            if(x == 3){
//                action.move(view.field.get(0).get(2), "up");
//            }
//            if(x == 5){
//                action.move(view.field.get(0).get(2), "down");
//            }
//            if(x == 7){
//                action.move(view.field.get(1).get(8), "right");
//            }
//            if(x == 8){
//                action.move(view.field.get(1).get(7), "left");
//            }
//            if(x == 9){
//                action.move(view.field.get(1).get(8), "left");
//            }



//            for (int j = 0; j < view.field.size(); j++) {
//                for (int k = 0; k < view.field.size(); k++) {
//                    action.scanForEnemy(view.field.get(j).get(k));
//                    if(view.field.get(j).get(k).getId() != ID.Ground){
//                        System.out.print(view.field.get(j).get(k).getId() + " ");
//                        System.out.println(view.field.get(j).get(k).getStatus());
//                    }
//
//                }
//            }

            for (int j = 0; j < list.size(); j++) {
                action.scanForEnemy(list.get(j));
                System.out.print(list.get(j).getId() + " ");
                System.out.println(list.get(j).getStatus());
            }


//            System.out.println(view.field.get(1).get(1).getStatus());
//            System.out.println(view.field.get(1).get(2).getStatus());

//            view.field.get(1).get(1).setHp(10 * i--);

            System.out.println(x);
        }
    }
}

