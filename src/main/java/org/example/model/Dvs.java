package org.example.model;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Dvs")
@Data
public class Dvs extends Motor {
    private int rashod;
    private String type;

    public void videlGazov() {
        System.out.println("Мотор " + name + " выделение газов.");
    }

    public void videlShyma() {
        System.out.println("Мотор " + name + " выделение шума.");
    }

    @Override
    public void remont() {
        System.out.println("Мотор " + name + " Обслуживание / замена поршней.");
    }

    @Override
    public void addEnergy() {
        System.out.println("Мотор " + name + " заправка топливом.");
    }
}