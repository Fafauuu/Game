import javax.swing.JFrame;
import java.awt.*;
import java.util.ArrayList;


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

//    public static GameObject[][]  field= new GameObject[size][size];
    ArrayList<ArrayList<GameObject>> newField = new ArrayList<>(size);


    private void setField(int size) {
////        int x = 0, y = 0;
//        ID id = ID.Ground;
//
////        GameObject empty = new GameObject(x, y, id);
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                field[i][j] = new GameObject(i, j, id);
//            }
//        }


        for (int i = 0; i < size; i++) {
            newField.add(new ArrayList<>(size));

            for (int k = 0; k < newField.size(); k++) {
                for (int j = 0; j < newField.get(k).size(); j++) {
                    newField.get(k).set(k, new GameObject(k, j, ID.Ground));
                }

            }
        }
    }

    public ArrayList getField() {
        return newField;
    }

    public void printNumbers(){
        for (int i = 0; i < newField.size(); i++) {
            for (int j = 0; j < newField.get(i).size(); j++) {
                System.out.print(newField.get(j));
            }
            System.out.println();
        }System.out.println();
    }
//    public void printNumbers(){
//        newField.forEach(outer -> {
//            outer.forEach(System.out::println);
//        });
//    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        setField(size);

        for (int row = 0; row < newField.size(); row++) {
            for (int column = 0; column < newField.get(row).size(); column++) {

                Color color = Color.BLACK;
                if(newField.get(row).get(column).getId() == ID.Ground) color = Color.GREEN;
                if(newField.get(row).get(column).getId() == ID.Ally) color = newField.get(row).get(column).objectColor();


//                Color color;
//                switch (field[row][column]) {
//                    case 0:
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

