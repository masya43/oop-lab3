package org.example.model;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Motors")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Motor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public String name;
    public String ls;

    public void startMotor() {
        System.out.println("Мотор " + name + " запущен.");
    }

    public void stopMotor() {
        System.out.println("Мотор " + name + " остановлен.");
    }

    public void remont() {
        System.out.println("Мотор " + name + " на обслуживании.");
    }

    public void addEnergy() {
        System.out.println("Мотор " + name + " заправка/зарядка.");
    }

}
