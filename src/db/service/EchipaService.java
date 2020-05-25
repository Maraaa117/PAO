package db.service;

import db.repository.EchipaRepo;
import db.repository.ProiectRepo;

public class EchipaService {

    private static EchipaService instance;

    private final EchipaRepo repo = EchipaRepo.getInstance();

    private EchipaService() {
    }

    public static EchipaService getInstance() {
        if (instance == null) {
            instance = new EchipaService();
        }

        return instance;
    }

    public void saveEchipa(int index) {
        repo.saveEchipa(index);
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