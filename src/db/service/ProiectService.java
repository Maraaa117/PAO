package db.service;

import db.repository.AngajatRepo;
import db.repository.ProiectRepo;
import objects.Angajat;
import objects.Proiect;

public class ProiectService {

    private static ProiectService instance;

    private final ProiectRepo repo = ProiectRepo.getInstance();

    private ProiectService() {
    }

    public static ProiectService getInstance() {
        if (instance == null) {
            instance = new ProiectService();
        }

        return instance;
    }

    public void saveProiect(int index, int i) {
        repo.saveProiect(index, i);
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