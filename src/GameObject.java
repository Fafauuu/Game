import java.awt.*;
import java.util.ArrayList;

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

    public void setId(ID id) {
        this.id = id;
    }

    public ArrayList<GameObject> createObjects(int number, int startingX, int startingY){
        ArrayList<GameObject> warriors = new ArrayList<>(number);
        for (int i = 0; i < number; i++) {
            warriors.add(new GameObject(startingX, startingY, ID.Ally));
        }
        return warriors;
    }

    public Color objectColor(GameObject object) {

        Color objectColors = new Color(255,255,255);

        if (object.getId() == ID.Ground) {
            objectColors = new Color(89, 113, 67);
        }

        if(object.getId() == ID.Ally){
            objectColors = new Color(29, 57, 224);
        }

        return objectColors;
    }

    public Color objectHpColor(GameObject object) {

        Color objectHpColors = new Color(255,255,255);

        if (object.getHp() > 50 && object.getHp() <= 100) {
            objectHpColors = new Color(48, 226, 14);
        }

        if(object.getHp()>0 && object.getHp()<=50){
            objectHpColors = new Color(215, 11, 11);
        }

        return objectHpColors;
    }
}

