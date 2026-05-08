package test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.model.DTO.ProdDTO;
import test.service.ProdService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProdController {
    private final ProdService prodService;

    @Autowired
    public ProdController(ProdService prodService) {this.prodService = prodService;}

    @GetMapping
    public List<ProdDTO> getAllProduct() {return prodService.getAllProducts();}

    @GetMapping("/{id}")
    public ResponseEntity<ProdDTO> getUserById(@PathVariable Integer id) {
        ProdDTO userDto = prodService.getProductByID(id);
        if (userDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ProdDTO> updateProduct(@RequestBody ProdDTO prodRequest) throws  IOException {
        ProdDTO prod = prodService.updateProduct(prodRequest);
        return new ResponseEntity<>(prod, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable Integer id) {
        boolean result = prodService.deleteProductById(id);
        if (result) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
