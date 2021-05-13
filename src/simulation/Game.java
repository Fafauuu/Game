package simulation;

import java.util.ArrayList;

public class Game {

    public static void main(String[] args) throws InterruptedException {
        View view = new View();
        view.setSize(15);
        view.setRectSize(60);
        view.setLocation(10, 50);
        view.setSize(1000, 1000);

        Engine engine = new Engine();

        engine.setEmptyField(view.getFieldSize());

        view.setField(engine.getField());

        ArrayList<GameObject> list = new ArrayList<>(0);


        //Example of movement
        list.addAll(engine.createObjects(ID.Ally, "knight", 0, 1, 0, 0));
        list.addAll(engine.createObjects(ID.Enemy, "axeman",7, 7, 1, 1));

//        //Example of differences in stats
//        list.addAll(engine.createObjects(ID.Ally, "knight",0,0,0,0));
//        list.addAll(engine.createObjects(ID.Ally, "knight", 0,0,2,2));
//        list.addAll(engine.createObjects(ID.Ally, "axeman",0,0,4,4));
//        list.addAll(engine.createObjects(ID.Enemy,"knight", 3,3,0,0));
//        list.addAll(engine.createObjects(ID.Enemy, "axeman", 3,3,2,2));
//        list.addAll(engine.createObjects(ID.Enemy, "axeman", 3,3,4,4));

//        //Example of archers
//        list.addAll(engine.createObjects(ID.Ally, "archer", 0, 0, 0, 0));
//        list.addAll(engine.createObjects(ID.Ally, "archer", 0, 0, 6, 6));
//        list.addAll(engine.createObjects(ID.Enemy, "axeman",7, 7, 0, 0));
//        list.addAll(engine.createObjects(ID.Enemy, "knight",7, 7, 8, 8));

//        //Example of different attack type
//        list.addAll(engine.createObjects(ID.Ally, "knight",2,2,2,2));
//        list.addAll(engine.createObjects(ID.Ally, "axeman",2,2,6,6));
//        list.addAll(engine.createObjects(ID.Enemy, "axeman",1,1,2,2));
//        list.addAll(engine.createObjects(ID.Enemy, "axeman",3,3,2,2));
//        list.addAll(engine.createObjects(ID.Enemy, "axeman",2,2,1,1));
//        list.addAll(engine.createObjects(ID.Enemy, "axeman", 1,1,6,6));
//        list.addAll(engine.createObjects(ID.Enemy, "axeman",3,3,6,6));
//        list.addAll(engine.createObjects(ID.Enemy, "axeman", 2,2,7,7));
//        list.addAll(engine.createObjects(ID.Enemy, "axeman", 1,1,7,7));

//        //Example of charging
//        list.addAll(engine.createObjects(ID.Ally, "knight",0,0,0,0));
//        list.addAll(engine.createObjects(ID.Ally, "axeman",0,0,3,3));
//        list.addAll(engine.createObjects(ID.Ally, "cavalry",0,0,5,5));
//        list.addAll(engine.createObjects(ID.Ally, "cavalry",0,0,8,8));
//        list.addAll(engine.createObjects(ID.Enemy, "knight",6,6,0,0));
//        list.addAll(engine.createObjects(ID.Enemy, "axeman", 6,6,2,2));
//        list.addAll(engine.createObjects(ID.Enemy, "cavalry",6,6,5,5));
//        list.addAll(engine.createObjects(ID.Enemy, "cavalry", 6,6,9,9));

//        //Example of some greater battle
//        list.addAll(engine.createObjects(ID.Ally, "axeman", 3,4,0,3));
//        list.addAll(engine.createObjects(ID.Ally, "knight",2,3,7,10));
//        list.addAll(engine.createObjects(ID.Ally, "cavalry",5,5,9,12));
//        list.addAll(engine.createObjects(ID.Ally, "archer",1,1,4,10));
//        list.addAll(engine.createObjects(ID.Enemy, "knight",12,13,1,5));
//        list.addAll(engine.createObjects(ID.Enemy, "axeman",11,12,9,12));
//        list.addAll(engine.createObjects(ID.Enemy, "cavalry",9,9,0,3));
//        list.addAll(engine.createObjects(ID.Enemy, "archer",14,14,4,8));


        engine.placeWarriors(list);
        view.setVisible(true);

        int x = 0;
        do{
            Thread.sleep(1000);

            engine.scanForEnemy(list, x++);

            view.revalidate();
            view.repaint();
//            view.printNumbers();

            System.out.println(x);

            System.out.println("Number of allies: " + engine.numberOfUnits(list, ID.Ally) + " Number of enemies: " + engine.numberOfUnits(list, ID.Enemy));

        }while(!engine.endSimulation(list));
    }
}

