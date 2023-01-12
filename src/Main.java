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
        //Если это первый запуск, то мы должны создать игрока, именем будет служить первая введенная строка из консоли
        if (player == null) {
            player = new Character(str, 100, 20, 20, 13, 50, 0);
            String.format("Спасти наш мир от драконов и леших вызвался богатырь %s!", player.getName());

        }
        //отдельным методом выводим меню
        printMenu();

        boolean first =true;
        //варианты команд
        switch (br.readLine()) {
            //Торговец
            case "1": {
                if (first) {
                    System.out.println("Здравствуй путник и добро пожаловать в мой магазин");
                    first = false;
                }
                else System.out.println("Снова здраствуй путник");
                System.out.println("Что изволите купить? (Зелье / Камень Силы / Камень ловкости)");
                //Метод покупок
                trading(br.readLine());
                //диалог с продавцом
                while () {
                    System.out.println("Может что нибудь еще хотите приобрести? (Да/Нет)");
                    if (br.readLine().equals("Да")) {
                        System.out.println("Выбирайте! (Зелье / Камень Силы / Камень ловкости)");
                        trading(br.readLine());
                        System.out.println("Спасибо что зашли! Удачи вам в бою!");
                        command("2");
                    } else {
                        System.out.println("До новых встречь, путник");
                        command("2");
                    }
                }
            }
            break;
            case "2": {
                commitFight();//ошибка
            }
            break;
            case "3": {
                System.exit(1);
            }
            break;
            case "да": {
                command("2");
            }
            break;
            case "нет": {
                printMenu();
                command(br.readLine());
            }
        }

        //снова ждем команды от пользователя
        command(br.readLine());
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
                System.out.println(String.format("Увы, богатырь %s пал на поле брани смертью храбрых.", player.getName()));
                System.exit(0);
            }

            @Override
            public void Win() {
                System.out.println(String.format("%s победил! Параметры %s - здоровье: %d, ловкость: %d, опыт: %d, сила: %d, наличка: %d, уровень: %d",
                        player.getName(), player.getName(), player.getHp(), player.getAgility(), player.getExp(),
                        player.getStrength(), player.getGold(), player.getLevel()));
                System.out.println(String.format("Желает ли богатырь %s продолжить вершить подвиги? (да/нет)", player.getName()));
                try {
                    command(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static Unit creatMonster() {
        //рандомим появление монстра
        if ((Math.random() * 10) % 2 == 0) return new Goblin("Uj", 100, 5, 10, 10, 50, 0);
        else return new Dragon("Dragon", 100, 10, 10, 15, 100, 0);

    }

    //меню
    private static void printMenu() {
        System.out.println("Куда вы хотите пойти?");
        System.out.println("1. К торговцу.");
        System.out.println("2. В темный лес.");
        System.out.println("3. Да ну Вас! Пойду на печи полежу.");
    }
}
