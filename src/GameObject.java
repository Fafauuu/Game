import java.awt.*;

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

    //    public Warrior[] createWarriors(int number, int startingX, int startingY){
//        Warrior[] warriors = new Warrior[number];
//        for (int i = 0; i < number; i++) {
//            warriors[i] = new Warrior(startingX,startingY);
//        }
//        return warriors;
//    }

    public Color objectColor() {

        int r = 0, g = 0, b = 0;

        if (hp > 50 && hp <= 100) {
            r=0;
            g=255;
            b=0;
        }

        if(hp>0 && hp<=50){
            r=0;
            g=70;
            b=0;
        }

        Color objectColor = new Color(r, g, b);
        return objectColor;
    }
}

