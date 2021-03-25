import javax.swing.JFrame;
import java.awt.*;


public class View extends JFrame {

//    public static int[][] field =
//            {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
//                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
//                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
//                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
//                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
//                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
//                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
//                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
//                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
//                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
//
//            };

    public static int size = 10;

    public static GameObject[][]  field= new GameObject[size][size];


    private void setField(int size){
//        int x = 0, y = 0;
        ID id = ID.Ground;

//        GameObject empty = new GameObject(x, y, id);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = new GameObject(i, j, id);
            }
        }
    }

    public GameObject[][] getField() {
        return field;
    }

    public void printNumbers(){
        for (int i = 0; i < View.field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }System.out.println();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (int row = 0; row < field.length; row++) {
            for (int column = 0; column < field[0].length; column++) {

                Color color = Color.BLACK;
                if(field[row][column].getId() == ID.Ground) color = Color.WHITE;
                if(field[row][column].getId() == ID.Ally) color = field[row][column].objectColor();


//                Color color;
//                switch (field[row][column]) {
//                    case fiel
//                        color = Color.GREEN;
//                        break;
//                    case 1:
//                        color = Color.WHITE;
//                        break;
//                    case 2:
//                        color = Color.RED;
//                        break;
//                    default:
//                        color = Color.WHITE;
//                }
                g.setColor(color);
                g.fillRect(30 * column, 30 * row + 30, 30, 30);
                g.setColor(Color.BLACK);
                g.drawRect(30 * column, 30 * row + 30, 30, 30);
            }
        }
    }
}

