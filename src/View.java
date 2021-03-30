import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class View extends JFrame {

    public static int size = 10;

    ArrayList<ArrayList<GameObject>> newField = new ArrayList<>(size);

    public void setEmptyField(int size) {

        for (int i = 0; i < 10; i++) {
            newField.add(new ArrayList<>());
        }

        GameObject ground = new GameObject(ID.Ground);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                newField.get(i).add(j, ground);
            }
        }
    }

    public ArrayList<ArrayList<GameObject>> getNewField() {
        return newField;
    }

    public void printNumbers() {
        for (int i = 0; i < newField.size(); i++) {
            for (int j = 0; j < newField.get(i).size(); j++) {
                System.out.print(newField.get(i).get(j).getId());
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();

//        for (int i = 0; i < newField.size(); i++) {
//            for (int j = 0; j < newField.get(i).size(); j++) {
//                System.out.print(newField.get(i).get(j));
//                System.out.print(" ");
//            }
//            System.out.println();
//        }
//        System.out.println();
    }

//    public void printNumbers(){
//        newField.forEach(outer -> {
//            outer.forEach(System.out::println);
//        });
//    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int rectSize = 40;
        int red = 255, green = 255, blue = 255;

        for (int i = 0; i < newField.size(); i++) {
            for (int j = 0; j < newField.size(); j++) {

//                    System.out.println("i = " + i + " j = " + j + " ID = " + newField.get(i).get(j).getId());

                GameObject object = new GameObject();

                if (newField.get(i).get(j).getId() == ID.Ground) {
                    red = 0;
                    green = 70;
                    blue = 0;
                }

                if (newField.get(i).get(j).getId() == ID.Ally) {
                    int[] rgb = object.objectColor(newField.get(i).get(j));
                    red = rgb[0];
                    green = rgb[1];
                    blue = rgb[2];
                }


                g.setColor(new Color(red, green, blue));
                g.fillRect(rectSize * j + 8, rectSize * i + 30, rectSize, rectSize);
                g.setColor(Color.BLACK);
                g.drawRect(rectSize * j + 8, rectSize * i + 30, rectSize, rectSize);



                if(newField.get(i).get(j).getId() != ID.Ground){
                    int[] rgb = object.objectHpColor(newField.get(i).get(j));
                    red = rgb[0];
                    green = rgb[1];
                    blue = rgb[2];


                    int hpBarSize = (int) Math.ceil((rectSize-10)*newField.get(i).get(j).getHp()/100);
                    System.out.println(hpBarSize);
//                    System.out.println(newField.get(0).get(1).getHp());

//                    (int) Math.ceil((rectSize - 10)*hpBar)

                    g.setColor(new Color(255,255,255));
                    g.fillRect(rectSize * j + 8 + 5, rectSize * i + 30 + 5, rectSize - 10, 5);
                    g.setColor(new Color(red, green, blue));
                    g.fillRect(rectSize * j + 8 + 5, rectSize * i + 30 + 5, hpBarSize, 5);
                    g.setColor(Color.BLACK);
                    g.drawRect(rectSize * j + 8 + 5, rectSize * i + 30 + 5, rectSize - 10, 5);
                }
            }
        }

    }


}
