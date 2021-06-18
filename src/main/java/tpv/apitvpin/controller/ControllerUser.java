package tpv.apitvpin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tpv.apitvpin.modelos.User;
import tpv.apitvpin.repository.RepositoryUser;

@RestController
@RequestMapping("/usuarios")
public class ControllerUser {
    @Autowired
    RepositoryUser repositoryUser;

    @PostMapping("/identificacion")
    public String validation(@RequestBody User user){
        String nick = user.getNick();
        String password = user.getPassword();
        if(repositoryUser.findUsuarioByNickAndPassword(nick, password)!=null){
            User miUser = repositoryUser.findUsuarioByNickAndPassword(nick,password);
            miUser.setPassword("*****");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String usuarioToJson;
            try {
                usuarioToJson = objectMapper.writeValueAsString(miUser);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                usuarioToJson= "error Json";
            }
            return usuarioToJson;
        }else{
            return "No identificado";
        }

    }
    @PostMapping()
    public String addNewUser (@RequestBody User user) {
        User u = user;
        repositoryUser.save(u);
        return "User saved";
    }
    @DeleteMapping("/{id}")
    public String deleteUser (@PathVariable("id") int id) {
        if(repositoryUser.findById(id).isPresent()) {
            repositoryUser.deleteById(id);
            return "Delete user";
        }
        else {
            return "There is not user with this id.";
        }
    }

    @PutMapping()
    public String updateUser (@RequestBody User user) {
        Integer id = user.getId();
        if(repositoryUser.findById(id).isPresent()) {
                repositoryUser.save(user);
                return "Update user";


        }else{
            return "There is not product with this id.";
        }
    }


}
