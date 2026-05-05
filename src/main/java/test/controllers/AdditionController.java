package test.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.model.DTO.ProdDTO;
import test.service.AdditionService;

@RestController
@RequestMapping("/addition")
public class AdditionController {

    private final AdditionService additionService;
@Autowired
    public AdditionController(AdditionService additionService) {
    this.additionService = additionService;
    }

    @PostMapping("/additionProd")
    public ResponseEntity <ProdDTO> additionProd(
            @RequestBody @Valid ProdDTO prodDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ProdDTO createdProduct = additionService.addition(prodDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }
}
