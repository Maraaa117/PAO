package db.service;

import db.repository.AngajatRepo;
import db.repository.LiderRepo;
import objects.Angajat;
import objects.Lider;

import java.util.List;

public class LiderService {

    private static LiderService instance;

    private final LiderRepo liderRepo = LiderRepo.getInstance();

    private LiderService() {
    }

    public static LiderService getInstance() {
        if (instance == null) {
            instance = new LiderService();
        }

        return instance;
    }

    public Lider saveLider(Lider lider) {
        return liderRepo.saveLider(lider);
    }

    public List<Lider> getLideri() {
        return liderRepo.getLideri();
    }

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