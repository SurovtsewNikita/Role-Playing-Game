import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static BufferedReader br;
    private static Unit player = null;
    private static Battle battle = null;
    private static Merchant trader = new Merchant();

    public static void main(String[] args) {
        //инициируем чтение BufferedReader
        br = new BufferedReader(new InputStreamReader(System.in));
        //инициируем класс для боя
        battle = new Battle();

        //просим ввести имя игрока
        System.out.println("Введите имя игрока:");
        try {
            command(br.readLine());//запускаем создание игрока и меню
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //в этом методе реализуем рекурсию в метод будет передаваться буферридер
    private static void command(String str) throws IOException {
        boolean check = true, first = true;
        //Если это первый запуск, то мы должны создать игрока, именем будет служить первая введенная строка из консоли
        if (player == null) {
            player = new Character(str, 100, 20, 20, 25, 50, 0);
            System.out.println(String.format("Спасти наш мир от драконов и чудовищь вызвался путник %s!", player.getName()));
            printMenu();
        }
        //варианты команд
        switch (br.readLine()) {
            //Торговец
            case "1": {
                if (first) {
                    System.out.println("Здравствуй путник и добро пожаловать в мой магазин");
                    first = false;
                } else System.out.println("Снова здраствуй путник");
                System.out.println("Что изволите купить? (Зелье / Камень Силы / Камень ловкости)");
                //Метод покупок
                trading(br.readLine());
                //диалог с продавцом
                while (check) {
                    System.out.println("Может что нибудь еще хотите приобрести? (Да/Нет)");
                    if (br.readLine().equals("Да")) {
                        System.out.println("Выбирайте! (Зелье / Камень Силы / Камень ловкости)");
                        trading(br.readLine());
                    } else {
                        check = false;
                        System.out.println("До новых встречь, путник");
                        printMenu();
                        command("2");
                    }
                }
            }
            break;
            case "2", "Да": {
                commitFight();//ошибка
            }
            break;
            case "3": {
                System.out.println(String.format("На этом приключения %s закончились", player.getName()));
                System.exit(1);
            }
            break;
            case "Нет": {
                printMenu();
                command("2");
            }
        }
    }

    interface Callback {
        void Lost();

        void Win();
    }

    private static void trading(String goodsAdd) {
        //Trader trader = new Trader(); чтобы каждый раз нового торговца не создавать
        trader.sell(goodsAdd, (Character) player);
    }

    //метод реализации боевой логики
    private static void commitFight() {
        //запускаем объект и вызываем метод битвы
        battle.fight(player, creatMonster(), new Callback() {
            @Override
            public void Lost() {
                System.out.println(String.format("Увы, смерть настигла %s, но память о его деяниях останется", player.getName()));
                System.exit(0);
            }

            @Override
            public void Win() {
                System.out.println(String.format("%s победил! Параметры %s - здоровье: %d, ловкость: %d, опыт: %d, сила: %d, золото: %d, уровень: %d",
                        player.getName(), player.getName(), player.getHp(), player.getAgility(), player.getExp(),
                        player.getStrength(), player.getGold(), player.getLevel()));
                System.out.println(String.format("Желает ли %s продолжить приключение? (Да/Нет)", player.getName()));
                try {
                    command("2");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static Unit creatMonster() {
        int random = (int) (Math.random() * 10);
        //рандомим появление монстра
        if (random < 5) return new Goblin("Гоблин", 100, 10, 15, 10, 50, 0);//Гоблин появится с шансом 50%
        else if (random < 9) return new Skeleton("Скелет", 80, 20, 20, 15, 15, 0);//Скелет появвится с шансом 40%
        else return new Dragon("Dragon", 1000, 100, 150, 150, 100, 3);//Дракон может появится в оставшиеся 10%

    }

    //меню
    private static void printMenu() {
        System.out.println("Куда вы хотите пойти?");
        System.out.println("1. К торговцу.");
        System.out.println("2. В темный лес.");
        System.out.println("3. Возвратится в город.");
    }
}
