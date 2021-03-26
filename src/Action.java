import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.*;


public class Action {

//    public void paint() {
//        View view = new View();
//        ArrayList<ArrayList<GameObject>> board = view.getField();
//
////        for (int row = 0; row < board.length; row++) {
////            for (int column = 0; column < board[0].length; column++) {
////                if(board[row][column] == 2){
////                    view.field[row][column] = 0;
////                    continue;
////                }
////                view.field[row][column]++;
////            }
////        }
//}

    public void placeWarriors(ArrayList<GameObject> warriors) {
        View view = new View();
        ArrayList<ArrayList<GameObject>> board = view.getNewField();

        board.get(0).addAll(warriors);
//        view.newField = board;
//        for (int i = 0; i < warriors.size(); i++) {
//            board.get(i).set(i, warriors.get(i));
//        }

//        int startingRow = 0, startingColumn = 0;
//
//        outerLoop:
//        for (int i = 0; i < view.size; i++) {
//            for (int j = 0; j < view.size; j++) {
//                if (board.get(i).get(j).getId() == ID.Ground) continue outerLoop;
//                startingColumn++;
//            }
//            startingRow++;
//        }
//
//        double rows = Math.ceil(((double) startingRow*view.size + startingColumn + warriors.size())/View.size);
//        int x = 0;
//        for (int i = startingRow; i < rows; i++) {
//            for (int j = startingColumn; j < view.size; j++) {
//                board.get(i).set(j, warriors.get(x++));
//                if(x >= warriors.size())return;
//            }startingColumn=0;
//        }
//    }

    }
}