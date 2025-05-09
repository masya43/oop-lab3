package org.example;

import org.example.dao.ElectroDao;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MotorService motorService = new MotorService();

        Electro electro = new Electro();
        electro.setName("ElectroMotor1");
        electro.setLs("1200");
        electro.setType("Electric");
        electro.setKVt(50);
        electro.setType("TypeA");

        Dvs dvs = new Dvs();
        dvs.setName("DvsMotor1");
        dvs.setLs("2000");
        dvs.setType("Combustion");
        dvs.setRashod(15);
        dvs.setType("TypeB");

        motorService.saveMotor(electro);
        motorService.saveMotor(dvs);

        electro.startMotor();
        dvs.stopMotor();
    }
}