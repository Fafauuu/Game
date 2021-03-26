import javax.swing.JFrame;
import java.awt.*;
import java.util.ArrayList;


public class View extends JFrame {

    public static int size = 10;

    ArrayList<ArrayList<GameObject>> newField = new ArrayList<>(size);

    public void setEmptyField(int size) {

        for (int i = 0; i < 10; i++) {
            newField.add(new ArrayList<>());
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                newField.get(i).add(j, new GameObject(i, j, ID.Ground));
            }
        }
    }

    public ArrayList<ArrayList<GameObject>> getNewField() {
        return newField;
    }

    public void printNumbers(){
            for (int i = 0; i < newField.size(); i++) {
                for (int j = 0; j < newField.get(i).size(); j++) {
                    System.out.print(newField.get(i).get(j).getId());
                    System.out.print(" ");
                }
                System.out.println();
            }
            System.out.println();
        }

//    public void printNumbers(){
//        newField.forEach(outer -> {
//            outer.forEach(System.out::println);
//        });
//    }


//    private Color setColor(){
//        int r=255,g=255,b=255;
//
//        for (int i = 0; i < newField.size(); i++) {
//            for (int j = 0; j < newField.size(); j++) {
//                if (newField.get(i).get(j).getId() == ID.Ground) {
//                        r = 0;
//                        g = 70;
//                        b = 0;
//                    }
//                    if (newField.get(i).get(j).getId() == ID.Ally) {
//                        r = 80;
//                        g = 50;
//                        b = 70;
//                    }
//            }
//        }
//        Color color = new Color(r,g,b);
//
//        return color;
//    }

        @Override
        public void paint (Graphics g){
            super.paint(g);

            int r = 200, green = 0, b = 80;

            for (int i = 0; i < newField.size(); i++) {
                for (int j = 0; j < newField.size(); j++) {

                    System.out.println("i = " + i + " j = " + j + " ID = " + newField.get(i).get(j).getId());


//                    if (newField.get(i).get(j).getHp() > 50 && newField.get(i).get(j).getHp() <= 100) {
//                        r = 0;
//                        green = 255;
//                        b = 0;
//                    }
//
//                    if (newField.get(i).get(j).getHp() > 0 && newField.get(i).get(j).getHp() <= 50) {
//                        r = 0;
//                        green = 70;
//                        b = 0;
//                    }

                    if (newField.get(i).get(j).getId().equals(ID.Ground)) {
                        r = 0;
                        green = 70;
                        b = 0;
                    }
                    else if (newField.get(i).get(j).getId().equals(ID.Ally)) {
                        r = 255;
                        green = 255;
                        b = 255;
                    }




                    g.setColor(new Color(r, green, b));
                    g.fillRect(40 * j + 8, 40 * i + 30, 40, 40);
                    g.setColor(Color.BLACK);
                    g.drawRect(40 * j + 8, 40 * i + 30, 40, 40);

                }
            }

        }
}
