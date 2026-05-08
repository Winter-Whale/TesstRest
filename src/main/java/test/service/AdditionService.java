package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import test.model.DTO.ProdDTO;
import test.model.Product;
import test.repo.ProdRepository;

import java.time.LocalDateTime;

@Service
public class AdditionService {
    private final ProdRepository prodRepository;
    private final TransactionTemplate transactionTemplate;

    @Autowired
    public AdditionService(ProdRepository prodRepository, TransactionTemplate transactionTemplate) {
        this.prodRepository = prodRepository;
        this.transactionTemplate = transactionTemplate;
    }

    public ProdDTO addition(ProdDTO prodDTO) {
        return transactionTemplate.execute(action -> {
            Product product = new Product();
            product.setName(prodDTO.getName());
            product.setPrice(prodDTO.getPrice());
            product.setWeight(prodDTO.getWeight());
            product.setCreated(LocalDateTime.now());
            Product savedProduct = prodRepository.saveProduct(product);

            ProdDTO prodDto1 = new ProdDTO();
            prodDto1.setName(savedProduct.getName());
            prodDto1.setPrice(savedProduct.getPrice());
            prodDto1.setWeight(savedProduct.getWeight());
            prodDto1.setId(savedProduct.getId());
            return prodDto1;
        });
    }
}
