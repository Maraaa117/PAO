package db.service;

import db.repository.AngajatRepo;
import objects.Angajat;

import java.util.Date;

public class AngajatService {

    private static AngajatService instance;

    private final AngajatRepo userRepository = AngajatRepo.getInstance();

    private AngajatService() {
    }

    public static AngajatService getInstance() {
        if (instance == null) {
            instance = new AngajatService();
        }

        return instance;
    }

    public Angajat saveUser(Angajat angajat) {
        return userRepository.saveAngajat(angajat);
    }

    //public User findUser(String email) {
    //    return userRepository.findUser(email);
    //}

    //public User updateUser(User user) {
    //    return userRepository.updateUser(user);
    //}

    //public boolean deleteUser(User user) {
    //    return userRepository.deleteUser(user.getEmail());
    //}

    //public User findNewestMember() {
    //    return userRepository.findNewestMember();
    //}
}