package com.coderhouse.ecommerce.service.customer;

import com.coderhouse.ecommerce.model.Customer;
import com.coderhouse.ecommerce.model.Invoice;
import com.coderhouse.ecommerce.model.auth.Session;
import com.coderhouse.ecommerce.model.cart.Cart;
import com.coderhouse.ecommerce.model.cart.CartItem;
import com.coderhouse.ecommerce.model.product.Product;
import com.coderhouse.ecommerce.repository.CustomerRepository;
import com.coderhouse.ecommerce.repository.InvoiceRepository;
import com.coderhouse.ecommerce.repository.ProductRepository;
import com.coderhouse.ecommerce.repository.cart.CartRepository;
import com.coderhouse.ecommerce.service.auth.SessionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private CartRepository cartRepository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private SessionService sessionService;

  @Autowired
  private InvoiceRepository invoiceRepository;

  public Cart addProductToCart(String token, String productId, int quantity) {

    Session session = sessionService.getSession(token.replace("Bearer ", ""));

    if (!sessionService.isSessionValid(session)) {
      throw new RuntimeException("Session is not valid");
    }

    Customer customer = customerRepository.findById(session.getOwnerId())
            .orElseThrow(() -> new RuntimeException("Customer not found"));

    Cart cart = cartRepository.findByCustomer(customer)
            .orElseGet(() -> {
              Cart newCart = new Cart();
              newCart.setCustomer(customer);
              return newCart;
            });

    Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found"));

    Optional<CartItem> existingItem = cart.getItems().stream()
            .filter(item -> item.getProduct().equals(product))
            .findFirst();

    if (existingItem.isPresent()) {
      CartItem cartItem = existingItem.get();
      cartItem.setQuantity(cartItem.getQuantity() + quantity);
    } else {
      CartItem newItem = new CartItem();
      newItem.setProduct(product);
      newItem.setQuantity(quantity);
      cart.getItems().add(newItem);
    }

    double total = cart.getItems().stream()
            .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
            .sum();
    cart.setTotal(total);

    return cartRepository.save(cart);
  }

  public Cart removeProductFromCart(String token, String productId) {

    Session session = sessionService.getSession(token.replace("Bearer ", ""));

    if (!sessionService.isSessionValid(session)) {
      throw new IllegalArgumentException("Session is not valid");
    }

    Customer customer = customerRepository.findById(session.getOwnerId())
            .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

    Cart cart = cartRepository.findByCustomer(customer)
            .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

    Product product = productRepository.findById(productId)
            .orElseThrow(() -> new IllegalArgumentException("Product not found"));

    Optional<CartItem> existingItem = cart.getItems().stream()
            .filter(item -> item.getProduct().equals(product))
            .findFirst();

    if (existingItem.isPresent()) {
      CartItem cartItem = existingItem.get();
      cart.getItems().remove(cartItem);
      double total = cart.getItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
      cart.setTotal(total);
      return cartRepository.save(cart);
    }
    else {
      throw new RuntimeException("Product not found in cart");
    }
  }

  public Cart getCartByCustomer(String token) {

    Session session = sessionService.getSession(token.replace("Bearer ", ""));

    if (!sessionService.isSessionValid(session)) {
      throw new RuntimeException("Session is not valid");
    }

    Customer customer = customerRepository.findById(session.getOwnerId())
            .orElseThrow(() -> new RuntimeException("Customer not found"));

    return cartRepository.findByCustomer(customer)
            .orElseThrow(() -> new RuntimeException("Cart not found"));
  }

  @Transactional
  public Invoice checkout(String token) {
    Session session = sessionService.getSession(token.replace("Bearer ", ""));

    if (!sessionService.isSessionValid(session)) {
      throw new IllegalArgumentException("Session is not valid");
    }

    Customer customer = customerRepository.findById(session.getOwnerId())
            .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

    Cart cart = cartRepository.findByCustomer(customer)
            .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

    if (cart.getItems().isEmpty()) {
      throw new IllegalArgumentException("Cart is empty");
    }

    for (CartItem item : cart.getItems()) {
      Product product = item.getProduct();
      if (product.getStock() < item.getQuantity()) {
        throw new RuntimeException("Insufficient stock for product: " + product.getTitle());
      }
      product.setStock(product.getStock() - item.getQuantity());
      productRepository.save(product);
    }

    Invoice invoice = new Invoice();
    invoice.setCustomer(customer);
    invoice.setTotal(cart.getTotal());
    invoice.setProducts(cart.getItems().stream()
            .map(CartItem::getProduct)
            .collect(Collectors.toList()));

    invoice = invoiceRepository.save(invoice);

    cart.setItems(new ArrayList<>());
    cart.setTotal(0);
    cartRepository.save(cart);

    return invoice;

  }

}


