package simulation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameObject {

    private int x, y;
    private int maxHp;
    private int hp;
    private int baseDmg;
    private int attack;
    private int defence;

    private ID id;
    private Status status = Status.NotMoved;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public GameObject(ID id) {
        this.id = id;;
    }

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

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getBaseDmg() {
        return baseDmg;
    }

    public void setBaseDmg(int baseDmg) {
        this.baseDmg = baseDmg;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public ID getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static ArrayList<GameObject> createObjects (ID side, String type, int startingX, int finalX, int startingY, int finalY) {

        ArrayList<GameObject> warriors = new ArrayList<>(0);

        if (type.equals("knight") || type.equals("axeman") || type.equals("archer") || type.equals("cavalry")) {

            if (startingX > finalX) {
                int buff;
                buff = finalX;
                finalX = startingX;
                startingX = buff;
            }

            if (startingY > finalY) {
                int buff;
                buff = finalY;
                finalY = startingY;
                startingY = buff;
            }

            for (int x = startingX; x <= finalX; x++) {
                for (int y = startingY; y <= finalY; y++) {
                    if (type.equals("knight")) warriors.add(new Knight(x, y, side));
                    if (type.equals("axeman")) warriors.add(new AxeMan(x, y, side));
//                    if (type.equals("archer")) warriors.add(new Archer(x, y, side));
//                    if (type.equals("cavalry")) warriors.add(new Cavalry(x, y, side));
                }
            }
        } else {
            System.out.println("wrong unit type");
            System.exit(0);
        }
        return warriors;
    }


    public static BufferedImage objectIcon(GameObject object) {
        BufferedImage image = null;
        String fileName = new String();

        if (object instanceof Knight) {
            fileName = "sword";
        }
        if (object instanceof AxeMan) {
            fileName = "axe";
        }
        if (object instanceof Archer) {
            fileName = "archer";
        }
        if (object instanceof Cavalry) {
            fileName = "cavalry";
        }


        try {
            image = ImageIO.read(new File("src/icons/" + fileName + ".png"));
        } catch (IOException ex) {
            System.out.println("\nreading image issue\n");
            ex.printStackTrace();
        }
        return image;
    }

    public static Color objectColor(GameObject object) {

        Color objectColor = new Color(255, 255, 255);

        if (object.getId() == ID.Ground) {
            objectColor = new Color(89, 113, 67);
        }

        if (object.getId() == ID.Ally) {
            objectColor = new Color(207, 255, 74);
        }

        if (object.getId() == ID.Enemy) {
            objectColor = new Color(255, 31, 64);
        }

        return objectColor;
    }

    public static Color objectHpColor(GameObject object) {

        Color objectHpColor = new Color(255, 255, 255);
        int objectHp = (int) Math.ceil((double) object.getHp() / object.getMaxHp() * 100);

//        System.out.println(object.getId() +  " X: " + object.getX() +  " Y: " + object.getY()  + " HP: " + objectHp +  " Attack: " + object.getAttack() +  " Defence: " + object.getDefence());

        if (objectHp > 75 && objectHp <= 100) {
            objectHpColor = new Color(48, 226, 14);
        }
        if (objectHp > 50 && objectHp <= 75) {
            objectHpColor = new Color(250, 225, 0);
        }
        if (objectHp > 25 && objectHp <= 50) {
            objectHpColor = new Color(250, 113, 4);
        }
        if (objectHp > 0 && objectHp <= 25) {
            objectHpColor = new Color(215, 11, 11);
        }

        return objectHpColor;
    }
}

