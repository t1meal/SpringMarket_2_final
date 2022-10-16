package ru.gb.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.market.models.ShoppingCart;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<ShoppingCart, Long> {

    Optional<ShoppingCart> findCartByCartOwner(String userName);

//    List<CartItem> cartItems;
//
//    public void addItem(CartItem cartItem) {
//        cartItems.add(cartItem);
//    }
//
//    @PostConstruct
//    public void setListProductsInCart() {
//        this.cartItems = new ArrayList<>();
//    }
//
//    public List<CartItem> findAllInCart() {
//        return cartItems;
//    }
//
//
//    public void deleteProductInCart(Long id) {
//        if (id != null) {
//            cartItems.removeIf(nextProduct -> id.equals(nextProduct.getId()));
//        }
//    }
//    public List<Product> getCartItems() {
//        return cartItems;
//    }
}
