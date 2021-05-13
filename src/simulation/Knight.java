package simulation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Knight extends GameObject {

    public Knight(int x, int y, ID id) {
        super(x, y, id);
        super.setHp(200);
        super.setMaxHp(200);
    }

    @Override
    public void attack(ArrayList<GameObject> warriors, GameObject attackedObject){

        int attackValue = 0;

        if(attackedObject instanceof Knight) attackValue = 20;
        if(attackedObject instanceof AxeMan) attackValue = 40;
        if(attackedObject instanceof Archer) attackValue = 80;
        if(attackedObject instanceof Cavalry) attackValue = 30;

        attackedObject.setHp(attackedObject.getHp()-attackValue);
    }

    @Override
    public BufferedImage objectIcon() {
        BufferedImage image = null;

        String fileName = "sword";

        try {
            image = ImageIO.read(new File("src/icons/" + fileName + ".png"));
        } catch (IOException ex) {
            System.out.println("\nreading image issue\n");
            ex.printStackTrace();
        }
        return image;
    }
}
