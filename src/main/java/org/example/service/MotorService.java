package org.example.service;
import org.example.dao.MotorDao;
import org.example.model.Motor;
import java.util.List;

public class MotorService {
    private MotorDao motorDao;

    public MotorService() {
        motorDao = new MotorDao();
    }

    public void saveMotor(Motor motor) {
        motorDao.save(motor);
    }

    public Motor getMotorByName(String name) {
        return motorDao.getByName(name);
    }

    public List<Motor> getAllMotors() {
        return motorDao.getAll();
    }

    public Motor getMotorById(int id) {
        return motorDao.getById(id);
    }

    public void deleteMotorById(int id) {
        motorDao.delete(id);
    }
}