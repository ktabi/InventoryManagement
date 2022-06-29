package com.gm.inventorymanagement.controllers;


import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gm.inventorymanagement.models.AuthUser;
import com.gm.inventorymanagement.models.Item;
import com.gm.inventorymanagement.models.User;
import com.gm.inventorymanagement.services.ItemService;
import com.gm.inventorymanagement.services.UserService;

@Controller
public class MainController {

	private UserService userServ;
	private final ItemService itemServ;
	public MainController(UserService userServ, ItemService itemServ) {
		this.userServ = userServ;
		this.itemServ = itemServ;
	}

//	=================================
//	Render Login/Register Route
//	=================================
//	==================================================================================
//	We use the model model to be able instanciate the register and login on same page
//	==================================================================================
	@GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new AuthUser());
        return "authenticate.jsp";
    }
    
//		=================================
//		Process Register Route
//		=================================
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser,  BindingResult result, 
    		Model model, HttpSession session) {
        userServ.register(newUser, result);
        if(result.hasErrors()) {
            model.addAttribute("newLogin", new AuthUser());
            return "authenticate.jsp";
        }
//      User id Put into Session
        session.setAttribute("user_id", newUser.getId());
        return "redirect:/dashboard";
    }
    
//	=================================
//	Process Login Route
//	=================================
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") AuthUser newLogin,BindingResult result, 
    		Model model, HttpSession session) {
        User user = userServ.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "authenticate.jsp";
        }
//        User id Put into Session
        session.setAttribute("user_id", user.getId());
        return "redirect:/dashboard";
    }
    
    
    
//	=================================
//	Logout Route
//	=================================
    @GetMapping("logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
    
    
    
//	=================================
//	Render Dashboard Route / Show all
//	=================================
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model, RedirectAttributes flash) {
    	Long userId = (Long) session.getAttribute("user_id");
//    	Return to login/Register if there is no userId in session
    	if(userId == null) {
//    		Manually using flash Attribute
    		flash.addFlashAttribute("login", "Please Log In to Continue!");
    		return "redirect:/";
    		}
    	
    	User user = userServ.getUserInfo(userId);
    	model.addAttribute("loggedUser", user);
    	
    	List<Item> items = itemServ.getAllItems();
//    	Staging to domain model.
    	model.addAttribute("items", items);
    	
    	return "index.jsp";
    }
    
    
    
//	=================================
//	Add New Item Render Route
//	=================================
    
    @GetMapping("/add")
    public String newItem(HttpSession session, RedirectAttributes flash, Model model) {
    	Long userId = (Long) session.getAttribute("user_id");
//    	Return to login/Register if there is no userId in session
    	if(userId == null) {
//    		Manually using flash Attribute
    		flash.addFlashAttribute("login", "Please Log In to Continue!");
    		return "redirect:/";
    		}
    	
//    	user pulled out of session
    	model.addAttribute("userId", userId);
//    	Instanciating Item to pass forward to jsp.
    	model.addAttribute("item", new Item());
    
    	return "addItem.jsp";
    }
    
//	=================================
//	Add New Item Process Route
//	=================================
    
    @PostMapping("/create")
    public String createItem(@Valid @ModelAttribute("item") Item item, BindingResult result, HttpSession session, Model model) {
    	if(result.hasErrors()) {
        	Long userId = (Long) session.getAttribute("user_id");
        	model.addAttribute("userId", userId);
    		return "addItem.jsp";
    	} else {
    		itemServ.saveItem(item);
    		return "redirect:/dashboard";
    	}
    	
    }
    
    
    
    
//	=================================
//	Show One/ Details Route
//	=================================
    
    @GetMapping("/details/{id}")
    public String showItem(@PathVariable("id") Long itemId, HttpSession session, Model model, RedirectAttributes flash) {
    	Long userId = (Long) session.getAttribute("user_id");
//    	Return to login/Register if there is no userId in session
    	if(userId == null) {
//    		Manually using flash Attribute
    		flash.addFlashAttribute("login", "Please Log In to Continue!");
    		return "redirect:/";
    		}
    	
    		Item item = itemServ.findOneItem(itemId);
    		model.addAttribute("item", item);
    	
    	return "details.jsp";
    }
    
    
    
//	=================================
//	Edit Item Routes Render Route
//	=================================
    @GetMapping("/edit/{id}")
    public String editItem(@PathVariable("id") Long itemId, HttpSession session, Model model, RedirectAttributes flash) {
    	Long userId = (Long) session.getAttribute("user_id");
//    	Return to login/Register if there is no userId in session
    	if(userId == null) {
//    		Manually using flash Attribute
    		flash.addFlashAttribute("login", "Please Log In to Continue!");
    		return "redirect:/";
    		}
    	
    		Item item = itemServ.findOneItem(itemId);
    		if(item.getUser().getId().equals(userId)) {
    			model.addAttribute("item", item);
    			return "edit.jsp";
    		}
    		flash.addFlashAttribute("user", "Access Denied!");
    		return "redirect:/dashboard";
    }
    
//	=================================
//	Edit Item Routes Process Route
//	=================================
    
    @PutMapping("/update/{id}") 
    public String updateItem(@Valid @ModelAttribute("item") Item item, BindingResult result) {
    	if(result.hasErrors()) {
    		return "edit.jsp";
    	} else {
    		itemServ.saveItem(item);
    		return "redirect:/dashboard";
    	}
    }
    
    
    
//	=================================
//	Delete Route
//	=================================
    
    @DeleteMapping("/delete/{id}")
    public String deleteItem(@PathVariable("id") Long itemId,HttpSession session,Model model, RedirectAttributes flash  ) {
    	Long userId = (Long) session.getAttribute("user_id");
    	Item item = itemServ.findOneItem(itemId);
		if(item.getUser().getId().equals(userId)) {
//			model.addAttribute("item", item);
			itemServ.deleteItem(itemId);
			return "redirect:/dashboard";
			}
		flash.addFlashAttribute("user", "Access Denied!");
		return "redirect:/dashboard";
    	
    	
    }
    

}
