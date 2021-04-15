package simulation;

import java.util.ArrayList;

public class Knight extends GameObject {

    public Knight(int x, int y, ID id) {
        super(x, y, id);
        super.setHp(300);
        super.setMaxHp(300);
        super.setBaseDmg(20);
        super.setAttack(20);
        super.setDefence(40);
    }

    public static ArrayList<GameObject> createKnights(ID side, int startingX, int finalX, int startingY, int finalY) {

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

        int rowNumber = finalX - startingX + 1;
        int columnNumber = finalY - startingY + 1;
        int number = rowNumber * columnNumber;

        ArrayList<GameObject> warriors = new ArrayList<>(number);

        for (int x = startingX; x <= finalX; x++) {
            for (int y = startingY; y <= finalY; y++) {
                warriors.add(new Knight(x, y, side));
            }
        }
        return warriors;
    }
}
