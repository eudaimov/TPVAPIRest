package tpv.apitvpin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tpv.apitvpin.modelos.Product;
import tpv.apitvpin.modelos.TableServices;
import tpv.apitvpin.repository.RepositoryServices;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/servicios")
public class ControllerServices {
    @Autowired
    private RepositoryServices repositoryServices;
    @PostMapping()
    public String addService(@RequestBody TableServices tableServices){
        TableServices t = tableServices;
        repositoryServices.save(t);
        return "Save service";
    }
    @GetMapping()
    public String allTableServices(){
        List<TableServices> listServices = repositoryServices.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String listToJson;
        try {
            listToJson = objectMapper.writeValueAsString(listServices);
            return listToJson;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
           return listToJson = "Error Json";
        }
    }
    @GetMapping("/busquedaFiltrada")
    public String findServices(@RequestBody TableServices tableServices){
        int numeroComensales = tableServices.getNumberOfDiners();
        int idMesa =  tableServices.getIdTable();
        Date fechaComienzo = tableServices.getBeginService();
        Date fechaFinal = tableServices.getFinishService();
        List<TableServices> listServices;
        listServices =  repositoryServices.findAll();
        List<TableServices> listaFiltrada = listServices.stream()
                .filter(servicio -> idMesa!=0 ? servicio.getIdTable()==idMesa : servicio.getIdTable()!=null)
                .filter(servicio -> numeroComensales!=0 ? servicio.getNumberOfDiners()==numeroComensales : servicio.getNumberOfDiners()!=null)
                .filter(servicio -> servicio.getBeginService().after(fechaComienzo))
                .filter(servicio -> servicio.getFinishService() == null || servicio.getFinishService().before(fechaFinal))
                .collect(Collectors.toList());


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String listToJson;
        try {
            listToJson = objectMapper.writeValueAsString(listaFiltrada);
            return listToJson;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return listToJson = "Error Json";
        }
    }
    @PutMapping()
    public  String updateTableServices(@RequestBody TableServices tableServices){
        Integer id = tableServices.getId();
        if (repositoryServices.existsById(id)) {
            repositoryServices.save(tableServices);
            return "Update product";
        } else {
            return "There is not product with this id.";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteServices (@PathVariable("id") int id) {
        if(repositoryServices.findById(id).isPresent()) {
            repositoryServices.deleteById(id);
            return "Delete service";
        }
        else {
            return "There is not service with this id.";
        }
    }

}
