package simulation;

import java.util.ArrayList;

public class Action {

    ArrayList<ArrayList<GameObject>>board;

    public ArrayList<ArrayList<GameObject>> getBoard() {
        return board;
    }

    public void setList(ArrayList<ArrayList<GameObject>> board) {
        this.board = board;
    }

    public void printField(){
        System.out.println(board.size());
    }


    public void placeWarriors(ArrayList<GameObject> list){

        for (int i = 0; i < list.size(); i++) {
            board.get(list.get(i).getX()).set(list.get(i).getY(), list.get(i));
        }

    }

}
