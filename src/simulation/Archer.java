package simulation;

import java.util.ArrayList;

public class Archer extends GameObject{

    private boolean melee = false;

    public Archer(int x, int y, ID id) {
        super(x, y, id);
        super.setHp(100);
        super.setMaxHp(100);
        super.setRange(2);
    }

    public void setMelee(boolean melee) {
        this.melee = melee;
    }

    @Override
    public void attack(ArrayList<GameObject> warriors, GameObject attackedObject) {

        int attackValue = 0;

        if(melee == false) {
            if (attackedObject instanceof Knight) attackValue = 20;
            if (attackedObject instanceof AxeMan) attackValue = 60;
            if (attackedObject instanceof Archer) attackValue = 70;
            if (attackedObject instanceof Cavalry) attackValue = 30;
        }else{
            if (attackedObject instanceof Knight) attackValue = 10;
            if (attackedObject instanceof AxeMan) attackValue = 20;
            if (attackedObject instanceof Archer) attackValue = 30;
            if (attackedObject instanceof Cavalry) attackValue = 10;
        }

        attackedObject.setHp(attackedObject.getHp() - attackValue);

    }
}
