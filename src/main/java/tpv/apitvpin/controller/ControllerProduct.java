package tpv.apitvpin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import tpv.apitvpin.controllerException.ExcepcionesController;
import tpv.apitvpin.modelos.Product;
import tpv.apitvpin.repository.RepositoryProduct;
import tpv.apitvpin.storage.StorageException;
import tpv.apitvpin.storage.StorageFileNotFoundException;
import tpv.apitvpin.storage.StorageService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;

import org.apache.commons.io.IOUtils;

@RestController
@RequestMapping("/productos")
public class ControllerProduct {
    @Autowired
    private RepositoryProduct repositoryProduct;
    @Autowired
    private StorageService storageService;

    @GetMapping("/{id}")
    public  String idProduct(@PathVariable("id") Integer id){
        if(repositoryProduct.findById(id).isPresent()){
            Optional<Product> myProduct = repositoryProduct.findById(id);
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new Jdk8Module());
            String result;
            try {
                result = mapper.writeValueAsString(myProduct);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
                result = "Error Json";
            }
          return result;
        }else {
            return "There is not product with this id.";
        }
        
    }

    @GetMapping()
    public String allProducts() {
        if (repositoryProduct.count() > 0) {
            ArrayList<Product> listProduct = (ArrayList<Product>) repositoryProduct.findAll();

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String productToJson;
            try {
                productToJson = objectMapper.writeValueAsString(listProduct);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
                productToJson = "Error Json";
            }
            return productToJson;
        } else {
            return "There is not product in this bbdd";
        }
    }

    @GetMapping(value = "/imagen/{nameImage}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource getImageProduct(@PathVariable ("nameImage") String filename) {
        //Tienes que cambiar StorageProperties la ruta
        Resource file = null;
        try {
            file = storageService.loadAsResource(filename);
            return file;
        } catch (StorageFileNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
                    , "El archivo "+filename+" no se encuentra en nuestro repositorio", e);
        }
    }


    @PostMapping()
    public int addNewProduct(@RequestBody Product product) {
        Product p = product;
        repositoryProduct.save(p);
        int idProducto = p.getId();
        if(p.getRouteImage().equals("nulo.jpeg")){
            repositoryProduct.save(p);
        }else{
            p.setRouteImage(String.valueOf(idProducto)+".jpeg");
            repositoryProduct.save(p);
        }

        return idProducto;
    }

    @PostMapping(value = "/imagen",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<String> uploadImage(MultipartFile file, String texto) {
        System.out.println(texto);
        if(!file.isEmpty()){
            Path directorioImagen = Paths.get("Images");
            String rutaAbsoluta = directorioImagen.toFile().getAbsolutePath();
            try {
                byte[] bytes = file.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta+"//"+file.getOriginalFilename());
                Files.write(rutaCompleta,bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.ok("Imagen y producto guardado");
                //ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en peticion");

    }

    @DeleteMapping()
    public String deleteProduct(@RequestParam Integer id) {
        if (repositoryProduct.findById(id).isPresent()) {
            repositoryProduct.deleteById(id);
            return "Delete product";
        } else {
            return "There is not product with this id.";
        }
    }

    @PutMapping()
    public String updateProduct(@RequestBody Product p) {
        Integer id = p.getId();
        if (repositoryProduct.findById(id).isPresent()) {
            repositoryProduct.save(p);

            return "Update product";
        } else {
            return "There is not product with this id.";
        }
    }


}
