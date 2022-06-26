package ru.gb.market.repositories;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import ru.gb.market.model.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
@NoArgsConstructor
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class CartProductRepository {
    List<Product> listProductsInCart;

    @PostConstruct
    public void setListProductsInCart() {
        this.listProductsInCart = new ArrayList<>();
    }

    public List<Product> findAllInCart() {
        return listProductsInCart;
    }
    public void addProduct(Product product) {
        listProductsInCart.add(product);
    }
    public void deleteProductInCart(Long id) {
        if (id != null) {
            Iterator<Product> productIterator = listProductsInCart.iterator();
            while (productIterator.hasNext()) {
                Product nextProduct = productIterator.next();
                if (id == nextProduct.getId()) {
                    productIterator.remove();
                }
            }
        }
    }

    public List<Product> getListProductsInCart() {
        return listProductsInCart;
    }
}
