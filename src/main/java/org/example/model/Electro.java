package org.example.model;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Electro")
@Data
public class Electro extends Motor {
    private int kVt;
    private String type;

    public void malieOborot() {
        System.out.println("Мотор " + getName() + " - Режим малых оборотов");
    }

    public void obratnaZaryad() {
        System.out.println("Мотор " + getName() + " - Режим обратной зарядки");
    }

    @Override
    public void remont() {
        System.out.println("Мотор " + getName() + " - Обслуживание / Замена катушек");
    }

    @Override
    public void addEnergy() {
        System.out.println("Мотор " + getName() + " - Зарядка");
    }
}