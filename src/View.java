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

//    public void setNewField(ArrayList<ArrayList<GameObject>> newField) {
//        this.newField = newField;
//    }

    public void setEmptyField(int size) {

        for (int i = 0; i < 10; i++) {
                newField.add(new ArrayList<>());
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                newField.get(i).add(j, new GameObject(i, j, ID.Ground));
            }
        }

//        for (int k = 0; k < size; k++) {
//                for (int j = 0; j < size; j++) {
//                    newField.get(k).add(new GameObject(k, j, ID.Ground));
//                }


////        int x = 0, y = 0;
//        ID id = ID.Ground;
//
////        GameObject empty = new GameObject(x, y, id);
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                field[i][j] = new GameObject(i, j, id);
//            }
//        }


//        for (int i = 0; i < size; i++) {
//            newField.add(new ArrayList<>(size));
//
//            for (int k = 0; k < newField.size(); k++) {
//                for (int j = 0; j < newField.size(); j++) {
//                    newField.get(k).add(new GameObject(k, j, ID.Ground));
//                }
//
//            }
//        }this.newField = newField;
    }

    public ArrayList getField() {
        return newField;
    }

    public void printNumbers(){
        for (int i = 0; i < newField.size(); i++) {
            for (int j = 0; j < newField.get(i).size(); j++) {
                System.out.print(newField.get(i).get(j));
                System.out.print(" ");
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

//        setField(size);
//        System.out.println(newField.size());
//        for (int row = 0; row < newField.size(); row++) {
//            for (int column = 0; column < newField.get(row).size(); column++) {
//
//                Color color = Color.BLACK;
//                if(newField.get(row).get(column).getId() == ID.Ground) color = Color.GREEN;
//                if(newField.get(row).get(column).getId() == ID.Ally) color = newField.get(row).get(column).objectColor();
//
//
////                Color color;
////                switch (field[row][column]) {
////                    case 0:
////                        color = Color.GREEN;
////                        break;
////                    case 1:
////                        color = Color.WHITE;
////                        break;
////                    case 2:
////                        color = Color.RED;
////                        break;
////                    default:
////                        color = Color.WHITE;
////                }
////                g.setColor(color);
////                g.fillRect(30 * column, 30 * row + 30, 30, 30);
////                g.setColor(Color.BLACK);
////                g.drawRect(30 * column, 30 * row + 30, 30, 30);
//
//            }
//
//        }
        int r = 0, green = 255, b = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                if (newField.get(i).get(j).getHp() > 50 && newField.get(i).get(j).getHp() <= 100) {
                    r=0;
                    green=255;
                    b=0;
                }

                if(newField.get(i).get(j).getHp()>0 && newField.get(i).get(j).getHp()<=50){
                    r=0;
                    green=70;
                    b=0;
                }
                if (newField.get(i).get(j).getId()==ID.Ground){
                    r=0;
                    green=0;
                    b=0;
                }
                Color objectColor = new Color(r, green, b);

                g.setColor(objectColor);
                g.fillRect(30*i, 30*j, 30, 30);
                g.setColor(Color.BLACK);
                g.drawRect(30*i,30*j, 30, 30);

            }
        }

    }
}

