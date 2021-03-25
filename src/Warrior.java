public abstract class Warrior {

    protected int x,y;
    protected ID id;
    protected int hp;

    public Warrior(int x, int y, ID id) {
        this.x = x;
        this.y = y;
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

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
    //    public int Knight(){
//        return 1;
//    }
//
//    public int[] createKnights(int number){
//        int[] knights = new int[number];
//        for (int i = 0; i < number; i++) {
//            knights[i] = new Warrior().Knight();
//        }
//        return knights;
//    }
//
//    public int Archer(){
//        return 2;
//    }
//    public int[] createArchers(int number){
//        int[] archers = new int[number];
//        for (int i = 0; i < number; i++) {
//            archers[i] = new Warrior().Archer();
//        }
//        return archers;
//    }
}
