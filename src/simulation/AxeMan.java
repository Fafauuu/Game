package simulation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AxeMan extends GameObject {

    public AxeMan(int x, int y, ID id) {
        super(x, y, id);
        super.setHp(200);
        super.setMaxHp(200);
    }

    @Override
    public void attack(ArrayList<GameObject> warriors, GameObject attackedObject){

        int attackValue = 0;

        if(attackedObject instanceof Knight) attackValue = 50;
        if(attackedObject instanceof AxeMan) attackValue = 60;
        if(attackedObject instanceof Archer) attackValue = 90;
        if(attackedObject instanceof Cavalry) attackValue = 60;

        attackedObject.setHp(attackedObject.getHp()-attackValue);

        int x = getX();
        int y = getY();

        // AoE attack
        for (GameObject warrior: warriors) {
            if ((warrior != attackedObject) && warrior.getId()!=getId() && (((Math.abs(x - warrior.getX()) == 1) && (Math.abs(y - warrior.getY()) == 0)) ||
                    ((Math.abs(x - warrior.getX()) == 0) && (Math.abs(y - warrior.getY()) == 1)))){
                if(warrior instanceof Knight) attackValue = 25;
                if(warrior instanceof AxeMan) attackValue = 30;
                if(warrior instanceof Archer) attackValue = 55;
                if(warrior instanceof Cavalry) attackValue = 30;

                warrior.setHp(warrior.getHp()-attackValue);
            }
        }
    }

    @Override
    public BufferedImage objectIcon() {
        BufferedImage image = null;

        String fileName = "axe";

        try {
            image = ImageIO.read(new File("src/icons/" + fileName + ".png"));
        } catch (IOException ex) {
            System.out.println("\nreading image issue\n");
            ex.printStackTrace();
        }
        return image;
    }
}
