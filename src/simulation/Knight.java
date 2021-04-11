package simulation;

import java.awt.*;
import java.util.ArrayList;

public class Knight extends GameObject {

    public Knight(int x, int y, ID id) {
        super(x, y, id);
    }

    public Knight() {
    }

    public ArrayList<GameObject> createAllyObjects(int startingX, int finalX, int startingY, int finalY) {
        int rowNumber = finalX - startingX + 1;
        int columnNumber = finalY - startingY + 1;
        int number = rowNumber * columnNumber;

        ArrayList<GameObject> warriors = new ArrayList<>(number);

        for (int x = startingX; x <= finalX; x++) {
            for (int y = startingY; y <= finalY; y++) {
                warriors.add(new Knight(x, y, ID.Ally));
            }
        }
        return warriors;
    }

    public ArrayList<GameObject> createEnemyObjects(int startingX, int finalX, int startingY, int finalY) {
        int rowNumber = finalX - startingX + 1;
        int columnNumber = finalY - startingY + 1;
        int number = rowNumber * columnNumber;

        ArrayList<GameObject> warriors = new ArrayList<>(number);
//            for (int i = 0, x = startingX, y = startingY; i < number; i++, y++) {
//                warriors.add(new GameObject(x, y, ID.Ally));
//            }

        for (int x = startingX; x <= finalX; x++) {
            for (int y = startingY; y <= finalY; y++) {
                warriors.add(new Knight(x, y, ID.Enemy));
            }
        }
        return warriors;
    }

    public Color objectColor(GameObject object) {

        Color objectColor = new Color(255, 255, 255);

        if (object.getId() == ID.Ally) {
            objectColor = new Color(207, 255, 74);
        }

        if (object.getId() == ID.Enemy) {
            objectColor = new Color(207, 255, 74);
        }

        return objectColor;
    }
}
