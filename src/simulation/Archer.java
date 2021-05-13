package simulation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Archer extends GameObject{

    private boolean melee = false;
    private int ammunition = 5;

    public Archer(int x, int y, ID id) {
        super(x, y, id);
        super.setHp(300);
        super.setMaxHp(300);
        super.setRange(3);
    }

    public void setMelee(boolean melee) {
        this.melee = melee;
    }

    @Override
    public void attack(ArrayList<GameObject> warriors, GameObject attackedObject) {

        int attackValue = 0;

        if(!melee) {
            if (attackedObject instanceof Knight) attackValue = 20;
            if (attackedObject instanceof AxeMan) attackValue = 80;
            if (attackedObject instanceof Archer) attackValue = 50;
            if (attackedObject instanceof Cavalry) attackValue = 30;
            attackedObject.setHp(attackedObject.getHp() - attackValue);
            --ammunition;
        }else{
            if (attackedObject instanceof Knight) attackValue = 10;
            if (attackedObject instanceof AxeMan) attackValue = 20;
            if (attackedObject instanceof Archer) attackValue = 30;
            if (attackedObject instanceof Cavalry) attackValue = 10;
            attackedObject.setHp(attackedObject.getHp() - attackValue);
        }

        if(ammunition < 1) melee = true;
        int initialRange = getRange();
        if(melee)setRange(1);
        else setRange(initialRange);

    }

    @Override
    public BufferedImage objectIcon() {
        BufferedImage image = null;

        String fileName = "bow";

        try {
            image = ImageIO.read(new File("src/icons/" + fileName + ".png"));
        } catch (IOException ex) {
            System.out.println("\nreading image issue\n");
            ex.printStackTrace();
        }
        return image;
    }
}
