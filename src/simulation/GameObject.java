package simulation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

    public GameObject(ID id, Status status) {
        this.id = id;
        this.status = status;
    }

    public GameObject() {
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


    public BufferedImage objectIcon(GameObject object) {
        BufferedImage image = null;
        String fileName = new String();

        if (object instanceof Knight) {
            fileName = "sword";
        }
        if (object instanceof AxeMan) {
            fileName = "axe";
        }



        try {
            image = ImageIO.read(new File("src/icons/" + fileName + ".png"));
        } catch (IOException ex) {
            System.out.println("\nreading image issue\n");
            ex.printStackTrace();
        }
        return image;
    }

    public Color objectColor(GameObject object) {

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

    public Color objectHpColor(GameObject object) {

        Color objectHpColor = new Color(255, 255, 255);
        int objectHp = (int) Math.ceil((double)object.getHp()/object.getMaxHp() * 100);

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

