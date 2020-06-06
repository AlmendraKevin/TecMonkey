package com.uabc.edu.mx.tecmonkey.service;

import com.uabc.edu.mx.tecmonkey.model.User;
import com.uabc.edu.mx.tecmonkey.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UserRepository userRepository;

    public List<User> getUsuarios(){
        return (List<User>) userRepository.findAll();
    }

    public User getUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return userRepository.findById(id).get();
        }
       return user.get();
    }
    public User saveUser(User u){
        if (u.getId()==null){
            u=userRepository.save(u);
            return u;
        }else{
            Optional<User> user = userRepository.findById(u.getId());
            if(user.isPresent()){
                User newUser = user.get();
                newUser.setId(u.getId());
                newUser.setFullname(u.getFullname());
                newUser.setEmail(u.getEmail());
                newUser.setUsername(u.getUsername());
                newUser.setPassword(u.getPassword());
                newUser.setActive(u.isActive());
                newUser.setRoles(u.getRoles());
                newUser = userRepository.save(newUser);
                return newUser;
            }else{
                u = userRepository.save(u);
                return u;
            }
        }
    }
    public void deleteUser(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.deleteById(id);
        }
    }



    //Llave de clase
}
