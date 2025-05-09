package org.example;

import org.example.model.Electro;
import org.example.model.Dvs;
import org.example.model.Motor;
import org.example.service.MotorService;
import org.example.util.HibernateUtil;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MotorService motorService = new MotorService();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===== МЕНЮ =====");
            System.out.println("1. Добавить электромотор");
            System.out.println("2. Добавить ДВС");
            System.out.println("3. Показать все моторы");
            System.out.println("4. Показать мотор по ID");
            System.out.println("5. Удалить мотор по ID");
            System.out.println("6. Действия с мотором");
            System.out.println("0. Выход");
            System.out.print("Выбор: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // очистка \n

            try {
                switch (choice) {
                    case 1:
                        Electro e = new Electro();
                        System.out.print("Имя: ");
                        e.setName(scanner.nextLine());
                        System.out.print("ЛС: ");
                        e.setLs(scanner.nextLine());
                        System.out.print("Тип: ");
                        e.setType(scanner.nextLine());
                        System.out.print("кВт: ");
                        e.setKVt(scanner.nextInt());
                        scanner.nextLine(); // очистка
                        motorService.saveMotor(e);
                        System.out.println("Электромотор добавлен.");
                        break;

                    case 2:
                        Dvs d = new Dvs();
                        System.out.print("Имя: ");
                        d.setName(scanner.nextLine());
                        System.out.print("ЛС: ");
                        d.setLs(scanner.nextLine());
                        System.out.print("Тип: ");
                        d.setType(scanner.nextLine());
                        System.out.print("Расход: ");
                        d.setRashod(scanner.nextInt());
                        scanner.nextLine();
                        motorService.saveMotor(d);
                        System.out.println("ДВС добавлен.");
                        break;

                    case 3:
                        List<Motor> motors = motorService.getAllMotors();
                        System.out.println("Список моторов:");
                        for (Motor m : motors) {
                            String type = (m instanceof Electro) ? ((Electro) m).getType()
                                    : (m instanceof Dvs) ? ((Dvs) m).getType()
                                    : "Неизвестно";

                            System.out.println("ID: " + m.getId() + ", Имя: " + m.getName() + ", Тип: " + type);
                        }
                        break;

                    case 4:
                        System.out.print("Введите ID мотора: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // очистка
                        Motor m = motorService.getMotorById(id);
                        if (m != null) {
                            System.out.println("Имя: " + m.getName());
                            System.out.println("ЛС: " + m.getLs());
                            if (m instanceof Electro) {
                                Electro el = (Electro) m;
                                System.out.println("Тип: " + el.getType());
                                System.out.println("кВт: " + el.getKVt());
                                System.out.println("Примечание: Электрический");
                            } else if (m instanceof Dvs) {
                                Dvs dv = (Dvs) m;
                                System.out.println("Тип: " + dv.getType());
                                System.out.println("Расход: " + dv.getRashod());
                                System.out.println("Примечание: ДВС");
                            }
                        } else {
                            System.out.println("Мотор не найден.");
                        }
                        break;

                    case 5:
                        System.out.print("Введите ID мотора для удаления: ");
                        int delId = scanner.nextInt();
                        scanner.nextLine();
                        motorService.deleteMotorById(delId);
                        System.out.println("Мотор удалён.");
                        break;

                    case 6:
                        System.out.print("Введите ID мотора: ");
                        int actionId = scanner.nextInt();
                        scanner.nextLine();

                        Motor selected = motorService.getMotorById(actionId);
                        if (selected == null) {
                            System.out.println("Мотор не найден.");
                            break;
                        }

                        boolean actionRunning = true;
                        while (actionRunning) {
                            System.out.println("\nВыберите действие для мотора \"" + selected.getName() + "\":");
                            System.out.println("1. Запустить");
                            System.out.println("2. Заглушить");
                            System.out.println("3. Обслужить");
                            System.out.println("4. Заправить/зарядить");
                            System.out.println("5. Специальные действия");
                            System.out.println("0. Назад в меню");
                            System.out.print("Выбор: ");
                            int act = scanner.nextInt();
                            scanner.nextLine();

                            switch (act) {
                                case 1:
                                    selected.startMotor();
                                    break;
                                case 2:
                                    selected.stopMotor();
                                    break;
                                case 3:
                                    selected.remont();
                                    break;
                                case 4:
                                    selected.addEnergy();
                                    break;
                                case 5:
                                    if (selected instanceof Dvs) {
                                        Dvs dv = (Dvs) selected;
                                        System.out.println("1. Выделение газов");
                                        System.out.println("2. Шум двигателя");
                                        System.out.print("Выбор: ");
                                        int sub = scanner.nextInt();
                                        scanner.nextLine();
                                        if (sub == 1) dv.videlGazov();
                                        else if (sub == 2) dv.videlShyma();
                                        else System.out.println("Неизвестная команда.");
                                    } else if (selected instanceof Electro) {
                                        Electro el = (Electro) selected;
                                        System.out.println("1. Малые обороты");
                                        System.out.println("2. Обратная зарядка");
                                        System.out.print("Выбор: ");
                                        int sub = scanner.nextInt();
                                        scanner.nextLine();
                                        if (sub == 1) el.malieOborot();
                                        else if (sub == 2) el.obratnaZaryad();
                                        else System.out.println("Неизвестная команда.");
                                    } else {
                                        System.out.println("Нет специальных действий для данного типа.");
                                    }
                                    break;
                                case 0:
                                    actionRunning = false;
                                    break;
                                default:
                                    System.out.println("Неверное действие.");
                            }
                        }
                        break;
                    case 0:
                        running = false;
                        break;

                    default:
                        System.out.println("Неверный выбор.");
                        break;
                }
            } catch (Exception e) {
                System.err.println("Ошибка: " + e.getMessage());
                scanner.nextLine(); // сброс ошибки ввода
            }
        }

        scanner.close();
        HibernateUtil.shutdown();
    }
}
