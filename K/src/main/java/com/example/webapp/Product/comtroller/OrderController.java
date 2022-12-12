package com.example.webapp.Product.comtroller;

import com.example.webapp.Mod.List;
import com.example.webapp.Mod.Order;
import com.example.webapp.Mod.models;
import com.example.webapp.repozitory.OrderRepository;
import com.example.webapp.repozitory.ProjectRepository;
import com.example.webapp.servis.ListServis;
import com.example.webapp.servis.OrderServis;
import com.example.webapp.servis.Services;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.Principal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    Order ord = new Order();
    String title = null;
    private final OrderRepository orderRepository;
    private final OrderServis servis;
    private final Services modelServis;
    private final ListServis listServis;


    @GetMapping ("/")
    public String prod (Model mdl,Principal principal){
        mdl.addAttribute("orderProducts",servis.listOrder(principal));
        mdl.addAttribute("user",servis.getUserByPrincipal(principal));
        return "main";
    }

    @GetMapping("/order")
    public String model( Model model, Principal principal){
        log.info("model{}");
        model.addAttribute("product",modelServis.listprod(title));
        model.addAttribute("user",servis.getUserByPrincipal(principal));
        model.addAttribute("list",servis.getListModels(ord));
        return "products";
    }

    @GetMapping("/order/apply")
    public String ApplyProduct(Model model){
        model.addAttribute("products",listServis.listprod(ord));
        log.info("prodSklad");
        model.addAttribute("Sum",listServis.Sum(ord));
        //log.info("Raznica{}",);
        return "final";
    }
    @PostMapping("/oplata")
    public String Apply(Model mdl,Principal principal) {
        log.info("vse");
        mdl.addAttribute("orderProducts",servis.listOrder(principal));
        mdl.addAttribute("user",servis.getUserByPrincipal(principal));
        return "main";
    }
    @GetMapping("/order/{ID}")
    public String OrderList(@PathVariable Long ID, Model mdl) {
        log.info("Order{}",ID);
        mdl.addAttribute("Lists",listServis.listprod(orderRepository.findByID(ID)));
        mdl.addAttribute("Sums",listServis.Sum(orderRepository.findByID(ID)));
        return "Order-inf";
    }

    @PostMapping("/order/create")
    public String CreateOrder(Principal principal, Order order,Model model){
        servis.saveOrder(order,principal);
        model.addAttribute("product",modelServis.listprod(title));
        model.addAttribute("user",servis.getUserByPrincipal(principal));
        model.addAttribute("list",servis.getListModels(order));
        model.addAttribute("order",order);
        ord = order;
        return "products";
    }
    @PostMapping("/order/createList/{ID}")
    public String CreateOrder(@PathVariable Long ID, List list
            ,@RequestParam(name = "times" , required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime in
            , @RequestParam(name = "times2", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime out)
    {
        listServis.save2(ID,ord, list,in,out);
        return "redirect:/order/";
    }
    @PostMapping("/order/delete/{ID}")
    public String DeletePosition(@PathVariable Long ID){
        log.info("delete");
        listServis.delete(ID,ord);
        return "redirect:/order/";
    }
    @PostMapping ("/order/example")
    public  String Show(@RequestParam String example){
                                  log.info("Comboox{}",example);
                                  title = example;
                                  return "redirect:/order/";
    }
}

