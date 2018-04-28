package demo.controller;

import demo.entity.Cart;
import demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartService service;

    @RequestMapping(value = "/carts", method = RequestMethod.GET)
    public List<Cart> getAll() {
        return service.getAll();
    }
}
