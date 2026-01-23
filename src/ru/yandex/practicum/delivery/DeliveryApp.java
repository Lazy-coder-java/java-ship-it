package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> trackables = new ArrayList<>();

    private static ParcelBox<StandardParcel> boxStandard = new ParcelBox<>(50);
    private static ParcelBox<FragileParcel> boxFragile = new ParcelBox<>(30);
    private static ParcelBox<PerishableParcel> boxPerishable = new ParcelBox<>(40);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    updateTracking();
                    break;
                case 5:
                    showBoxContents();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Обновить трекинг");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {
        System.out.println("Выберите тип посылки: 1-Standard, 2-Fragile, 3-Perishable");
        int type = Integer.parseInt(scanner.nextLine());

        System.out.println("Описание:");
        String desc = scanner.nextLine();

        System.out.println("Вес:");
        int weight = Integer.parseInt(scanner.nextLine());

        System.out.println("Адрес:");
        String address = scanner.nextLine();

        System.out.println("День отправки:");
        int sendDay = Integer.parseInt(scanner.nextLine());

        switch (type) {
            case 1:
                StandardParcel sp = new StandardParcel(desc, weight, address, sendDay);
                allParcels.add(sp);
                boxStandard.addParcel(sp);
                break;

            case 2:
                FragileParcel fp = new FragileParcel(desc, weight, address, sendDay);
                allParcels.add(fp);
                boxFragile.addParcel(fp);
                trackables.add(fp);
                break;

            case 3:
                System.out.println("Срок хранения (timeToLive):");
                int ttl = Integer.parseInt(scanner.nextLine());
                PerishableParcel pp = new PerishableParcel(desc, weight, address, sendDay, ttl);
                allParcels.add(pp);
                boxPerishable.addParcel(pp);
                break;

            default:
                System.out.println("Неверный тип.");
        }
    }

    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        int total = 0;
        for (Parcel parcel : allParcels) {
            total += parcel.calculateDeliveryCost();
        }
        System.out.println("Общая стоимость доставки: " + total);
    }

    private static void updateTracking() {
        System.out.println("Введите новое местоположение:");
        String location = scanner.nextLine();
        for (Trackable trackable : trackables) {
            trackable.reportStatus(location);
        }
    }

    private static void showBoxContents() {
        System.out.println("Выберите тип коробки: 1-Standard, 2-Fragile, 3-Perishable");
        int type = Integer.parseInt(scanner.nextLine());

        switch (type) {
            case 1:
                boxStandard.getAllParcels()
                        .forEach(p -> System.out.println(p.description));
                break;
            case 2:
                boxFragile.getAllParcels()
                        .forEach(p -> System.out.println(p.description));
                break;
            case 3:
                boxPerishable.getAllParcels()
                        .forEach(p -> System.out.println(p.description));
                break;
            default:
                System.out.println("Неверный тип.");
        }
    }
}
