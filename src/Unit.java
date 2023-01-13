public abstract class Unit implements Attack {
    String name;
    int hp, gold, exp, strength, agility, level;
    int boundary = 50;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public Unit(String name, int hp, int gold, int exp, int strength, int agility, int level) {
        this.name = name;
        this.hp = hp;
        this.gold = gold;
        this.exp = exp;
        this.strength = strength;
        this.agility = agility;
        this.level = level;
    }

    public int getRandomNumber() {
        return (int) (Math.random() * 100);
    }

    public void levelUp(){
        if (exp>=boundary){
            setLevel(getLevel()+1);
            setAgility(getAgility()+5);
            setStrength(getStrength()+5);
            boundary=(int)(boundary*(1.3));
        }

    }

    @Override
    public int attack() {
        if (agility * 3 > (int) (Math.random() * 100))
            if ((Math.random() * 10) % 4 == 0)
                return strength * 2;
        return strength;
    }
}
