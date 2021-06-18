package tpv.apitvpin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import tpv.apitvpin.modelos.TableRequestServices;
import tpv.apitvpin.modelos.TableServices;
import tpv.apitvpin.repository.RepositoryRequestServices;
import tpv.apitvpin.repository.RepositoryServices;

@RestController
@RequestMapping("/peticiones")
public class ControllerRequestServices {
    @Autowired
    private RepositoryRequestServices repositoryRequestServices;
    @Autowired
    private RepositoryServices repositoryServices;

    @PostMapping()
    public String addTableRequest(@RequestBody TableRequestServices tableRequestServices){
        TableRequestServices t = tableRequestServices;
        if (t.getTableServices().getId() != null) {
            try {
                repositoryRequestServices.save(t);
                return "Save request";
            } catch (Exception e) {
                return "No existe este servicio para esta peticion";
            }
        }
        else{
            return "Every request must to have one services";
        }

    }
    @GetMapping()
    public String allTableRequest(){
        List<TableRequestServices> listRequest =  repositoryRequestServices.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String listToJson;
        try {
            listToJson = objectMapper.writeValueAsString(listRequest);
            return listToJson;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
           return listToJson = "Error Json";
        }
    }
    @GetMapping("/servicio/{id}")
    public String TableRequestXServices(@PathVariable("id") int id){

        if(repositoryServices.findById(id).isPresent()){
           TableServices tableServices = repositoryServices.findById(id).get();
            List<TableRequestServices> listRequest =  repositoryRequestServices.findByTableServices(tableServices);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String listToJson;
            try {
                listToJson = objectMapper.writeValueAsString(listRequest);
                return listToJson;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return listToJson = "Error Json";
            }
        }
        else{
            return "No existe servicio";
        }

    }

    @PutMapping()
    public  String updateTableRequest(@RequestBody TableRequestServices tableRequestServices){
        Integer id = tableRequestServices.getId();
        if (repositoryRequestServices.existsById(id)) {
            repositoryRequestServices.save(tableRequestServices);
            return "Update request";
        } else {
            return "There is not product with this id.";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteRequest (@PathVariable("id") int id) {
        if(repositoryRequestServices.findById(id).isPresent()) {
            repositoryRequestServices.deleteById(id);
            return "Delete request";
        }
        else {
            return "There is not request with this id.";
        }
    }

}
