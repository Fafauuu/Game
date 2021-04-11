package simulation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameObject {

    private int x, y;
    private int hp;
    private ID id;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.hp = 100;
        this.id = id;
    }

    public GameObject(ID id){
        this.id = id;
    }

    public GameObject(){}


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public ID getId() {
        return id;
    }

//    public ArrayList<GameObject> createObjects(int startingX, int finalX, int startingY, int finalY){
//        int rowNumber = finalX-startingX+1;
//        int columnNumber = finalY-startingY+1;
//        int number = rowNumber*columnNumber;
//
//        ArrayList<GameObject> warriors = new ArrayList<>(number);
////            for (int i = 0, x = startingX, y = startingY; i < number; i++, y++) {
////                warriors.add(new GameObject(x, y, ID.Ally));
////            }
//
//        for (int x = startingX; x <= finalX; x++) {
//            for (int y = startingY; y <= finalY; y++) {
//                warriors.add(new GameObject(x, y, ID.Ally));
//            }
//        }
//
//        return warriors;
//    }

    public BufferedImage objectIcon(GameObject object){
        BufferedImage image = null;
        String fileName = new String();

        if (object instanceof Knight){
            fileName = "sword";
        }
        if (object instanceof AxeMan){
            fileName = "axe";
        }


        try{
            image = ImageIO.read(new File("src/icons/" + fileName + ".png"));
        }catch (IOException ex){
            System.out.println("\nreading image issue\n");
            ex.printStackTrace();
        }
        return image;
    }

    public Color objectColor(GameObject object) {

        Color objectColor = new Color(255,255,255);

        if (object.getId() == ID.Ground) {
            objectColor = new Color(89, 113, 67);
        }

        if(object.getId() == ID.Ally){
            objectColor = new Color(207, 255, 74);
        }

        if(object.getId() == ID.Enemy){
            objectColor = new Color(255, 31, 64);
        }

        return objectColor;
    }

    public Color objectHpColor(GameObject object) {

        Color objectHpColor = new Color(255, 255, 255);

        if (object.getHp() > 75 && object.getHp() <= 100) {
            objectHpColor = new Color(48, 226, 14);
        }
        if (object.getHp() > 50 && object.getHp() <= 75) {
            objectHpColor = new Color(250, 225, 0);
        }
        if (object.getHp() > 25 && object.getHp() <= 50) {
            objectHpColor = new Color(250, 113, 4);
        }
        if (object.getHp() > 0 && object.getHp() <= 25) {
            objectHpColor = new Color(215, 11, 11);
        }

        return objectHpColor;
    }
}

