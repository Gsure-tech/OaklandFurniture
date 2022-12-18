package com.decagon.OakLandv1be.services.serviceImpl;

import com.decagon.OakLandv1be.dto.SignupResponseDto;
import com.decagon.OakLandv1be.dto.cartDtos.AddItemToCartDto;
import com.decagon.OakLandv1be.dto.cartDtos.CartItemResponseDto;
import com.decagon.OakLandv1be.entities.*;
import com.decagon.OakLandv1be.enums.Role;
import com.decagon.OakLandv1be.exceptions.AlreadyExistsException;
import com.decagon.OakLandv1be.exceptions.NotAvailableException;
import com.decagon.OakLandv1be.exceptions.UnauthorizedException;
import com.decagon.OakLandv1be.exceptions.UserNotFoundException;
import com.decagon.OakLandv1be.repositries.*;
import com.decagon.OakLandv1be.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;
    private final CustomerRepository customerRepository;
    private final PersonRepository personRepository;

    @Override
    public CartItemResponseDto addItemToCart(Long productId, AddItemToCartDto addItemToCartDto) throws AlreadyExistsException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotAvailableException("Product not available"));

        if(product.getAvailableQty() <= 0)
            throw new NotAvailableException("Product out of stock");
        if(addItemToCartDto.getOrderQty() >= product.getAvailableQty())
            throw new NotAvailableException("Requested quantity more than available quantity, for this product");

        Person loggedInUser = personRepository.findByEmail(currentUserEmail()).get();
        if(loggedInUser.getRole() != Role.CUSTOMER)
            throw new UnauthorizedException("Please login as a customer to carryout this operation");

        Customer loggedInCustomer = loggedInUser.getCustomer();
        System.out.println(loggedInCustomer);

        List<Item> cartItemsList = new ArrayList<>();
        cartItemsList.addAll(loggedInCustomer.getCart().getItems());

        for(int i=0; i<cartItemsList.size(); i++){
            if(product.getName().equalsIgnoreCase(cartItemsList.get(i).getProductName())){
                throw new AlreadyExistsException("Product has already been added to cart");
            }
        }

        Item newCartItem = getNewCartItem(addItemToCartDto.getOrderQty(), product, loggedInCustomer, cartItemsList);

        SignupResponseDto signupResponseDto = new SignupResponseDto();
        BeanUtils.copyProperties(loggedInCustomer,signupResponseDto);

        CartItemResponseDto cartItemResponseDto = new CartItemResponseDto();
        BeanUtils.copyProperties(newCartItem, cartItemResponseDto);

        cartItemResponseDto.setCart(loggedInCustomer.getCart());
        cartItemResponseDto.setCustomer(signupResponseDto);

        itemRepository.save(newCartItem);

        return cartItemResponseDto;
    }

    @Override
    public Item getNewCartItem(int itemQuantity, Product product, Customer loggedInCustomer, List<Item> cartItemsList) {
        Item newCartItem = new Item();
        BeanUtils.copyProperties(product, newCartItem);
        newCartItem.setOrderQty(itemQuantity);
        newCartItem.setSubTotal(itemQuantity * product.getPrice());
        //newCartItem.setCustomer(loggedInCustomer);

        cartItemsList.add(newCartItem);
        Set<Item> cartItemsSet = new HashSet<>();
        cartItemsSet.addAll(cartItemsList);

        Cart cart = getLoggedInCustomerCart(loggedInCustomer, cartItemsList, cartItemsSet);
        newCartItem.setCart(cart);
        return newCartItem;
    }

    @Override
    public Cart getLoggedInCustomerCart(Customer loggedInCustomer, List<Item> cartItemsList, Set<Item> allCartItems) {
        Double cartTotal = 0.0;
        Cart cart = new Cart();
        cart.setItems(allCartItems);

        for(int i = 0; i<cartItemsList.size(); i++){
            cartTotal += cartItemsList.get(i).getSubTotal();
        }

        cart.setTotal(cartTotal);
        cart.setCustomer(loggedInCustomer);
        loggedInCustomer.setCart(cart);

        customerRepository.save(loggedInCustomer);
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public String currentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUser;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            loggedInUser = authentication.getName();
            return loggedInUser;
        }
        throw new UserNotFoundException("Please login to access your cart");
    }
}