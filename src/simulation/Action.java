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

        int xModifier = 0;
        int yModifier = 0;

        if (destination == "up")xModifier = -1;
        if (destination == "down")xModifier = 1;
        if (destination == "left")yModifier = -1;
        if (destination == "right")yModifier = 1;

        int destinatedX = warrior.getX()+xModifier;
        int destinatedY = warrior.getY()+yModifier;

        if (destinatedX >= 0 && destinatedX < board.size() && destinatedY >= 0 && destinatedY < board.size()) {
            if (board.get(destinatedX).get(destinatedY).getId() == ID.Ground) {
                board.get(destinatedX).set(destinatedY, warrior);
                board.get(warrior.getX()).set(warrior.getY(), new GameObject(ID.Ground));
                warrior.setX(destinatedX);
                warrior.setY(destinatedY);
            }
        } else {
            System.out.println("Object moved out of board");
            return;
        }
    }



}
