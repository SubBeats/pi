package com.example.webapp.Product.comtroller;

import com.example.webapp.Mod.Order;
import com.example.webapp.Mod.models;
import com.example.webapp.servis.ListServis;
import com.example.webapp.servis.OrderServis;
import com.example.webapp.servis.Services;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class controller {
    private final Services control;
    private final ListServis listServis;

    public controller( Services control, ListServis listServis) {
        this.control = control;
        this.listServis = listServis;
    }
    @GetMapping ("/models")
    public String prod (@RequestParam(name = "title", required = false)String title, Model mdl,Principal principal,Order order){
        mdl.addAttribute("products", control.listprod(title));
        mdl.addAttribute("user",control.getUserByPrincipal(principal));
        //mdl.addAttribute("orderProducts",order.getProducts());
        return "products";
    }

    @GetMapping("/models/{ID}")
    public String productInf(@PathVariable Long ID,Model model){
        models mdl = control.getProd(ID);
        model.addAttribute("models",mdl);
        model.addAttribute("images",mdl.getImages());
        return "product-inf";
    }
    @GetMapping("/modelsOrder/{ID}")
    public String productInf2(@PathVariable Long ID,Model model){
        models mdl = control.getProd(ID);
        model.addAttribute("models",mdl);
        model.addAttribute("images",mdl.getImages());
        return "product-inf2";
    }




/*    @PostMapping ("/models/create")
    public String createProduct( Principal principal, Order order) throws IOException {
        control.Save(principal,order);
        return "redirect:/";
    }*/

/*    @PostMapping("/models/delete/{ID}")
    public String deleteProd(@PathVariable Long ID){
        control.delete(ID);
        return "redirect:/";
    }*/

}
