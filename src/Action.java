public class Action {

    public void paint() {
        View view = new View();
        GameObject board[][] = view.getField();

//        for (int row = 0; row < board.length; row++) {
//            for (int column = 0; column < board[0].length; column++) {
//                if(board[row][column] == 2){
//                    view.field[row][column] = 0;
//                    continue;
//                }
//                view.field[row][column]++;
//            }
//        }

    }    public void placeWarrior(GameObject[] warriors) {
        View view = new View();
        GameObject board[][] = view.getField();

        int startingRow = 0, startingColumn = 0;

        outerLoop:
        for (int i = 0; i < view.size; i++) {
            for (int j = 0; j < view.size; j++) {
                if (board[i][j].getId() == ID.Ground) continue outerLoop;
                startingColumn++;
            }
            startingRow++;
        }

        double rows = Math.ceil(((double) startingRow*view.size + startingColumn + warriors.length)/View.size);
        int x = 0;
        for (int i = startingRow; i < rows; i++) {
            for (int j = startingColumn; j < view.size; j++) {
                board[i][j] = warriors[x++];
                if(x >= warriors.length)return;
            }startingColumn=0;
        }

//    public void moveUp(){
//    }
    }
}