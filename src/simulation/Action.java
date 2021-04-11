package simulation;

import java.util.ArrayList;

public class Action {

    ArrayList<ArrayList<GameObject>> board;

    public ArrayList<ArrayList<GameObject>> getBoard() {
        return board;
    }

    public void setList(ArrayList<ArrayList<GameObject>> board) {
        this.board = board;
    }

    public void printField() {
        System.out.println(board.size());
    }


    public void placeWarriors(ArrayList<GameObject> list) {

        for (int i = 0; i < list.size(); i++) {
            board.get(list.get(i).getX()).set(list.get(i).getY(), list.get(i));
        }
    }

//    public void removeDeadWarriors() {
//        for (int i = 0; i < board.size(); i++) {
//            for (int j = 0; j < board.size(); j++) {
//                if (board.get(i).get(j).getHp() == 0) {
//                    board.get(i).set(j, new GameObject(ID.Ground));
//                }
//            }
//
//        }
//    }

    public void removeDeadWarriors(ArrayList <GameObject> warriors) {
        for (int i = 0; i < warriors.size(); i++) {
            if (warriors.get(i).getHp() <= 0){
                int x = warriors.get(i).getX();
                int y = warriors.get(i).getY();
                board.get(x).set(y, new GameObject(ID.Ground));
                warriors.get(i).setStatus(Status.Dead);
            }
        }
    }

    public void move(GameObject warrior, String destination) {

        if (warrior.getId() == ID.Ground) {
            System.out.println("Can't move ground");
            return;
        }

        int xModifier = 0;
        int yModifier = 0;

        if (destination == "up") xModifier = -1;
        if (destination == "down") xModifier = 1;
        if (destination == "left") yModifier = -1;
        if (destination == "right") yModifier = 1;

        int targetX = warrior.getX() + xModifier;
        int targetY = warrior.getY() + yModifier;

        if (targetX >= 0 && targetX < board.size() && targetY >= 0 && targetY < board.size()) {
            if (board.get(targetX).get(targetY).getId() == ID.Ground) {
                board.get(targetX).set(targetY, warrior);
                board.get(warrior.getX()).set(warrior.getY(), new GameObject(ID.Ground));
                warrior.setX(targetX);
                warrior.setY(targetY);
                warrior.setStatus(Status.Moved);
            } else {
                System.out.println("Can't stack objects");
                warrior.setStatus(Status.notMoved);
            }
        } else {
            System.out.println("Object moved out of board");
            warrior.setStatus(Status.notMoved);
        }
    }

    public void attack(GameObject attackedWarrior, int turn){

        boolean allyToMove;
        if(turn % 2 == 0){
            allyToMove = true;
        }else allyToMove = false;

        if ((attackedWarrior.getId() == ID.Ally && !allyToMove) || (attackedWarrior.getId() == ID.Enemy && allyToMove))
        attackedWarrior.setHp(attackedWarrior.getHp()-25);
    }

    public void scanForEnemy(ArrayList<GameObject> warriors, int turn) {

        for (int a = 0; a < warriors.size(); a++) {

            boolean allyToMove;
            if(turn % 2 == 0){
                allyToMove = true;
            }else allyToMove = false;

            double minDistance = 0;
            double distance;
            int x = warriors.get(a).getX();
            int y = warriors.get(a).getY();
            int targetX = x;
            int targetY = y;
            ID id = warriors.get(a).getId();
            ID opponentId = ID.Ally;
            if(id == ID.Ally) opponentId = ID.Enemy;
            if(id == ID.Enemy) opponentId = ID.Ally;
            warriors.get(a).setStatus(Status.notMoved);


                for (int i = 0; i < warriors.size(); i++) {
                    if (i != a) {
                        if (warriors.get(i).getId() == opponentId && warriors.get(i).getStatus() != Status.Dead) {
                            if (minDistance == 0) {
                                minDistance = Math.sqrt((warriors.get(i).getX() - x) * (warriors.get(i).getX() - x)
                                        + (warriors.get(i).getY() - y) * (warriors.get(i).getY() - y));
                                targetX = warriors.get(i).getX();
                                targetY = warriors.get(i).getY();
                            }
                            if(minDistance != 0){
                                distance = Math.sqrt((warriors.get(i).getX() - x) * (warriors.get(i).getX() - x)
                                        + (warriors.get(i).getY() - y) * (warriors.get(i).getY() - y));
                                if (distance < minDistance){
                                    minDistance = distance;
                                    targetX = warriors.get(i).getX();
                                    targetY = warriors.get(i).getY();
                                }
                            }
                        }
                    }
                }

            if (minDistance == 1){
                for (int i = 0; i < warriors.size(); i++) {
                    if (warriors.get(i).getX() == targetX && (warriors.get(i).getY() == targetY)){
                        if(board.get(x).get(y).getId() != ID.Ground){
                            attack(warriors.get(i), turn);
                            warriors.get(a).setStatus(Status.Attacked);
                        }
                    }
                }
            }else warriors.get(a).setStatus(Status.notMoved);

//        if (warriors.getStatus() == Status.notMoved) {
            if (warriors.get(a).getStatus() != Status.Attacked) {
                if ((warriors.get(a).getId() == ID.Ally && allyToMove) || warriors.get(a).getId() == ID.Enemy && !allyToMove)
                    if (Math.abs(targetX - x) >= Math.abs(targetY - y)) {
                        if (targetX - x > 0) move(warriors.get(a), "down");
                        if (targetX - x < 0) move(warriors.get(a), "up");
                    } else {
                        if (targetY - y > 0) move(warriors.get(a), "right");
                        if (targetY - y < 0) move(warriors.get(a), "left");
                    }
//            warriors.get(a).setStatus(Status.Moved);
//        }else warriors.setStatus(Status.notMoved);

                removeDeadWarriors(warriors);
            }
            minDistance = 0;
        }
    }

}
