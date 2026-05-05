package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.model.DTO.ProdDTO;
import test.repo.ProdRepository;

import java.io.IOException;
import java.util.List;

@Service
public class ProdService {
   private final ProdRepository prodRepository;

    @Autowired
    public ProdService(ProdRepository ProdRepository) {this.prodRepository= ProdRepository;}

    public List<ProdDTO> getAllProducts(){
        return prodRepository.findAll();}

    public ProdDTO getProductByID(Integer id){
        return prodRepository.getProductById(id);
    }

    public boolean deleteProductById(Integer id) {
        return prodRepository.deleteProductById(id);

    }

    public ProdDTO updateProduct(ProdDTO prodDTO) throws IOException  {
         boolean result = prodRepository.updateProduct(prodDTO);
         if (result) {
             return getProductByID(prodDTO.getId());
         }
            throw  new IOException("update failed");
        }

    }

