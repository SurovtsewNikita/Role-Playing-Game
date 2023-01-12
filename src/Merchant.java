public class Merchant implements Sell {
    @Override
    public void sell(String item, Character character) {
        int goldT = character.getGold();
        int cost = 0;
        switch (item) {
            case "Зелье":
                cost = 15;
                if (goldT >= cost) {
                    character.setGold(goldT - cost);
                    character.setHp(character.getHp() + 50);
                    System.out.println(String.format("Куплено зелье(хп повышено на 50)" + "\n" + "Здоровье: %d" + "\n" + "Остаток золота: %d", character.getHp(), character.getGold()));
                } else {
                    System.out.println("Недостаточно средств для покупки");
                }
                break;
            case "Камень силы":
                cost = 50;
                if (goldT >= cost) {
                    character.setGold(goldT - cost);
                    character.setStrength(character.getStrength() + 10);
                    System.out.println(String.format("Сила повышена на 10" + "\n" + "Остаток золота: %d", character.getGold()));
                } else {
                    System.out.println("Недостаточно средств для покупки");
                }
                break;
            case "Камень ловкости":
                cost = 50;
                if (goldT >= cost) {
                    character.setGold(goldT - cost);
                    character.setAgility(character.getAgility() + 10);
                    System.out.println(String.format("Ловкость повышена на 10" + "\n" + "Остаток золота: %d", character.getGold()));
                } else {
                    System.out.println("Недостаточно средств для покупки");
                }
                break;
            default:
                System.out.println("Извини, но у меня нет такого товара");
            }
        }

}

