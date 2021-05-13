package simulation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class View extends JFrame {


    private int size = 0;
    private int rectSize = 0;

    public void setSize(int size) {
        this.size = size;
    }

    public int getFieldSize() {
        return size;
    }

    public void setRectSize(int rectSize) { this.rectSize = rectSize; }

    private ArrayList<ArrayList<GameObject>> field;

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

        for (int i = 0; i < field.size(); i++) {
            for (int j = 0; j < field.get(i).size(); j++) {
                System.out.print(field.get(i).get(j).getStatus());
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i = 0; i < field.size(); i++) {
            for (int j = 0; j < field.size(); j++) {

                g.setColor(GameObject.objectColor(field.get(i).get(j)));
                g.fillRect(rectSize * j + 8, rectSize * i + 30, rectSize, rectSize);
                g.setColor(Color.BLACK);
                g.drawRect(rectSize * j + 8, rectSize * i + 30, rectSize, rectSize);


                if (field.get(i).get(j).getId() != ID.Ground) {
//                    g.drawImage(GameObject.objectIcon(field.get(i).get(j)), rectSize * j + 8, rectSize * i + 30, rectSize, rectSize, this);
                    g.drawImage(field.get(i).get(j).objectIcon(), rectSize * j + 8, rectSize * i + 30, rectSize, rectSize, this);
                }


                if (field.get(i).get(j).getId() != ID.Ground) {

                    int hpBarSize = (int) Math.ceil((float) (rectSize - 10) * field.get(i).get(j).getHp() / field.get(i).get(j).getMaxHp());

                    g.setColor(new Color(255, 255, 255));
                    g.fillRect(rectSize * j + 8 + 5, rectSize * i + 30 + 5, rectSize - 10, 5);
                    g.setColor(GameObject.objectHpColor(field.get(i).get(j)));
                    g.fillRect(rectSize * j + 8 + 5, rectSize * i + 30 + 5, hpBarSize, 5);
                    g.setColor(Color.BLACK);
                    g.drawRect(rectSize * j + 8 + 5, rectSize * i + 30 + 5, rectSize - 10, 5);
                }
            }
        }

    }

}
