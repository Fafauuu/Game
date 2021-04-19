package simulation;

import java.util.ArrayList;

public class Game {

    public static void main(String[] args) throws InterruptedException {
        View view = new View();
        view.setSize(10);
        view.setRectSize(60);
        view.setLocation(10, 50);
        view.setSize(900, 900);

        Action action = new Action();

        action.setEmptyField(view.getFieldSize());

        view.setField(action.getField());

        ArrayList<GameObject> list = new ArrayList<>(0);


//        //Example of movement
//        list.addAll(action.createObjects(ID.Ally, "knight", 0, 1, 0, 0));
//        list.addAll(action.createObjects(ID.Enemy, "axeman",7, 7, 1, 1));

//        //Example of different attack type
//        list.addAll(action.createObjects(ID.Ally, "knight",2,2,2,2));
//        list.addAll(action.createObjects(ID.Ally, "axeman",2,2,6,6));
//        list.addAll(action.createObjects(ID.Enemy, "axeman",1,1,2,2));
//        list.addAll(action.createObjects(ID.Enemy, "axeman",3,3,2,2));
//        list.addAll(action.createObjects(ID.Enemy, "axeman",2,2,1,1));
//        list.addAll(action.createObjects(ID.Enemy, "axeman", 1,1,6,6));
//        list.addAll(action.createObjects(ID.Enemy, "axeman",3,3,6,6));
//        list.addAll(action.createObjects(ID.Enemy, "axeman", 2,2,7,7));
//        list.addAll(action.createObjects(ID.Enemy, "axeman", 1,1,7,7));


        //Example of charging
        list.addAll(action.createObjects(ID.Ally, "knight",0,0,0,0));
        list.addAll(action.createObjects(ID.Ally, "axeman",0,0,3,3));
        list.addAll(action.createObjects(ID.Ally, "cavalry",0,0,5,5));
        list.addAll(action.createObjects(ID.Ally, "cavalry",0,0,8,8));

        list.addAll(action.createObjects(ID.Enemy, "knight",6,6,0,0));
        list.addAll(action.createObjects(ID.Enemy, "axeman", 6,6,2,2));
        list.addAll(action.createObjects(ID.Enemy, "cavalry",6,6,5,5));
        list.addAll(action.createObjects(ID.Enemy, "cavalry", 6,6,9,9));

//        //Example of some greater battle
//        list.addAll(action.createObjects(ID.Ally, "knight", 1,3,0,3));
//        list.addAll(action.createObjects(ID.Ally, "axeman",0,2,6,8));
//        list.addAll(action.createObjects(ID.Enemy, "knight",6,8,1,4));
//        list.addAll(action.createObjects(ID.Enemy, "axeman",6,8,7,8));

//        //Example of differences in stats
//        list.addAll(action.createObjects(ID.Ally, "knight",0,0,0,0));
//        list.addAll(action.createObjects(ID.Ally, "knight", 0,0,2,2));
//        list.addAll(action.createObjects(ID.Ally, "axeman",0,0,4,4));
//        list.addAll(action.createObjects(ID.Enemy,"knight", 3,3,0,0));
//        list.addAll(action.createObjects(ID.Enemy, "axeman", 3,3,2,2));
//        list.addAll(action.createObjects(ID.Enemy, "axeman", 3,3,4,4));


        action.placeWarriors(list);
        view.setVisible(true);

        int x = 0;
        do{
            Thread.sleep(800);

            action.scanForEnemy(list, x++);

            view.revalidate();
            view.repaint();
//            view.printNumbers();

            System.out.println(x);

        }while(action.endSimulation(list) == false);
    }
}

