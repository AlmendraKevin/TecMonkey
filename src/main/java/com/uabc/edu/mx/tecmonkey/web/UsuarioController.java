package com.uabc.edu.mx.tecmonkey.web;

import com.uabc.edu.mx.tecmonkey.model.User;
import com.uabc.edu.mx.tecmonkey.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @RequestMapping
    public String getUsuarios(Model model){
        List<User> user = usuarioService.getUsuarios() ;
        model.addAttribute("user",user);
        return "ControlUsuariosAdmin";
    }
    @RequestMapping("/new")
    public String crearUsuario(Model model){
        model.addAttribute("user",new User());
        return "FormUsuarioAdmin";
    }
    @RequestMapping(path = "/save",method = RequestMethod.POST)
    public String guardarRegistro(@RequestParam(value = "id",required = true) Optional<Long> id,
                                  @RequestParam(value = "username",required = true) String username,
                                  @RequestParam(value = "password",required = true) String password,
                                  @RequestParam(value = "email",required = true) String email,
                                  @RequestParam(value = "fullname",required = true) String fullname,
                                  @RequestParam(value = "active",required = false) Boolean active,
                                  @RequestParam(value = "roles",required = true) String roles){
        User user;
        if(id.isPresent()){
            user = usuarioService.getUserById(id.get());
        }else{
            user = new User();
        }
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFullname(fullname);
        user.setRoles(roles);
        user.setActive(active);
        usuarioService.saveUser(user);

        return "redirect:/usuario";
    }

    @RequestMapping(path = { "/update/{id}" })
    public String editUserById(Model model, @PathVariable(value = "id", required = true) Long id) {
        User user = usuarioService.getUserById(id);
        model.addAttribute("user", user);
        return "FormUsuarioAdmin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        usuarioService.deleteUser(id);
        return "redirect:/usuario";
    }


    @RequestMapping("/nuevo")
    public String crearNuevo(){
        return "RegistroUsuario";
    }



}
