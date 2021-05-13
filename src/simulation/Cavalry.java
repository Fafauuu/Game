package simulation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Cavalry extends GameObject {

    public Cavalry(int x, int y, ID id) {
        super(x, y, id);
        super.setHp(100);
        super.setMaxHp(100);
    }

    @Override
    public void attack(ArrayList<GameObject> warriors, GameObject attackedObject) {

        int attackValue = 0;

        if (attackedObject instanceof Knight) attackValue = 90;
        if (attackedObject instanceof AxeMan) attackValue = 60;
        if (attackedObject instanceof Archer) attackValue = 150;
        if (attackedObject instanceof Cavalry) attackValue = 40;

        if (getStatus() == Status.Charged) {
            attackValue *= 2;
        }

        attackedObject.setHp(attackedObject.getHp() - attackValue);

    }

    @Override
    public BufferedImage objectIcon() {
        BufferedImage image = null;

        String fileName = "horse";

        try {
            image = ImageIO.read(new File("src/icons/" + fileName + ".png"));
        } catch (IOException ex) {
            System.out.println("\nreading image issue\n");
            ex.printStackTrace();
        }
        return image;
    }
}
