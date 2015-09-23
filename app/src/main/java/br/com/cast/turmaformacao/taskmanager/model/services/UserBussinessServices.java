package br.com.cast.turmaformacao.taskmanager.model.services;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Task;
import br.com.cast.turmaformacao.taskmanager.model.entities.User;
import br.com.cast.turmaformacao.taskmanager.model.persistence.TaskRepository;
import br.com.cast.turmaformacao.taskmanager.model.persistence.UserRepository;

/**
 * Created by Administrador on 21/09/2015.
 */
public class UserBussinessServices {


    private UserBussinessServices() {
        super();
    }

    public static List<User> findAll() {
        return UserRepository.getAll();
    }

    public static void save(User user) {
        UserRepository.save(user);
    }

    public static void delete(User user){
        UserRepository.delete(user.getId());
    }

}
