package simulation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class View extends JFrame {



    public static int size = 0;

    public static void setSize(int size) {
        View.size = size;
    }

    ArrayList<ArrayList<GameObject>> field = new ArrayList<>(size);


    public void setEmptyField() {

        for (int i = 0; i < size; i++) {
            field.add(new ArrayList<>(size));
        }

        GameObject ground = new GameObject(ID.Ground);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field.get(i).add(j, ground);
            }
        }
        setLocation(10,50);
        setSize(900,900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public ArrayList<ArrayList<GameObject>> getField() {
        return field;
    }

    public void setField(ArrayList<ArrayList<GameObject>> field) {
        this.field = field;
    }

    public void printNumbers() {
        for (int i = 0; i < field.size(); i++) {
            for (int j = 0; j < field.get(i).size(); j++) {
                System.out.print(field.get(i).get(j).getId());
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        BufferedImage image = null;

        try{
            image = ImageIO.read(new File("sword.png"));
        }catch (IOException ex){
            System.out.println("\nreading image issue\n");
            ex.printStackTrace();
        }

//        try {
//            URL url = new URL("resources\\sword.png");
//            image = ImageIO.read(url);
//        } catch (IOException e) {
//            System.out.println("\nreading image issue\n");
//        }


        int rectSize = 60;


        GameObject object = new GameObject();

        for (int i = 0; i < field.size(); i++) {
            for (int j = 0; j < field.size(); j++) {

                g.setColor(object.objectColor(field.get(i).get(j)));
                g.fillRect(rectSize * j + 8, rectSize * i + 30, rectSize, rectSize);
                g.setColor(Color.BLACK);
                g.drawRect(rectSize * j + 8, rectSize * i + 30, rectSize, rectSize);


                if(field.get(i).get(j).getId() == ID.Ally) {
                    g.drawImage(image, rectSize * j + 8, rectSize * i + 30, rectSize, rectSize,this);
                }



                if(field.get(i).get(j).getId() != ID.Ground){

                    int hpBarSize = (int) Math.ceil((rectSize-10)*field.get(i).get(j).getHp()/100);

                    g.setColor(new Color(255, 255, 255));
                    g.fillRect(rectSize * j + 8 + 5, rectSize * i + 30 + 5, rectSize - 10, 5);
                    g.setColor(object.objectHpColor(field.get(i).get(j)));
                    g.fillRect(rectSize * j + 8 + 5, rectSize * i + 30 + 5, hpBarSize, 5);
                    g.setColor(Color.BLACK);
                    g.drawRect(rectSize * j + 8 + 5, rectSize * i + 30 + 5, rectSize - 10, 5);
                }
            }
        }

    }


}
