public class Warrior {

    public int Knight(){
        return 1;
    }

    public int[] createKnights(int number){
        int[] knights = new int[number];
        for (int i = 0; i < number; i++) {
            knights[i] = new Warrior().Knight();
        }
        return knights;
    }

    public int Archer(){
        return 2;
    }
    public int[] createArchers(int number){
        int[] archers = new int[number];
        for (int i = 0; i < number; i++) {
            archers[i] = new Warrior().Archer();
        }
        return archers;
    }
}
