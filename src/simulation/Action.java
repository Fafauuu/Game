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

    public void removeDeadWarriors() {
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.size(); j++) {
                if (board.get(i).get(j).getHp() == 0) {
                    board.get(i).set(j, new GameObject(ID.Ground));
                }
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
            } else {
                System.out.println("Can't stack objects");
            }
        } else {
            System.out.println("Object moved out of board");
        }
    }

    public void scanForEnemy(GameObject warrior) {
        double minDistance = 0;
        double distance;
        int x = warrior.getX();
        int y = warrior.getY();
        int targetX = x;
        int targetY = y;

        if (warrior.getId() == ID.Ally) {
            for (int i = 0; i < board.size(); i++) {
                for (int j = 0; j < board.size(); j++) {
                    if (board.get(i).get(j).getId() == ID.Enemy) {
                        if (minDistance == 0) {
                            minDistance = Math.sqrt((board.get(i).get(j).getX() - x) * (board.get(i).get(j).getX() - x)
                                    + (board.get(i).get(j).getY() - y) * (board.get(i).get(j).getX() - y));
                            targetX = board.get(i).get(j).getX();
                            targetY = board.get(i).get(j).getY();
                        }
                        if (minDistance != 0) {
                            distance = Math.sqrt((board.get(i).get(j).getX() - x) * (board.get(i).get(j).getX() - x)
                                    + (board.get(i).get(j).getY() - y) * (board.get(i).get(j).getX() - y));
                            if (distance < minDistance) {
                                minDistance = distance;
                                targetX = board.get(i).get(j).getX();
                                targetY = board.get(i).get(j).getY();
                            }
                        }
                    }
                }
            }
        }

        if (warrior.getId() == ID.Enemy) {
            for (int i = 0; i < board.size(); i++) {
                for (int j = 0; j < board.size(); j++) {
                    if (board.get(i).get(j).getId() == ID.Ally) {
                        if (minDistance == 0) {
                            minDistance = Math.sqrt((board.get(i).get(j).getX() - x) * (board.get(i).get(j).getX() - x)
                                    + (board.get(i).get(j).getY() - y) * (board.get(i).get(j).getX() - y));
                            targetX = board.get(i).get(j).getX();
                            targetY = board.get(i).get(j).getY();
                        }
                        if (minDistance != 0) {
                            distance = Math.sqrt((board.get(i).get(j).getX() - x) * (board.get(i).get(j).getX() - x)
                                    + (board.get(i).get(j).getY() - y) * (board.get(i).get(j).getX() - y));
                            if (distance < minDistance) {
                                minDistance = distance;
                                targetX = board.get(i).get(j).getX();
                                targetY = board.get(i).get(j).getY();
                            }
                        }
                    }
                }
            }
        }

//        if (warrior.getStatus() == Status.notMoved) {
            if (Math.abs(targetX - x) >= Math.abs(targetY - y)) {
                if (targetX - x > 0) move(warrior, "down");
                if (targetX - x < 0) move(warrior, "up");
            } else {
                if (targetY - y > 0) move(warrior, "right");
                if (targetY - y < 0) move(warrior, "left");
            }
            warrior.setStatus(Status.Moved);
//        }else warrior.setStatus(Status.notMoved);
    }


}
