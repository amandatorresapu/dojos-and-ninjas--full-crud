package com.codingdojo.safetravels.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codingdojo.safetravels.models.Expense;
import com.codingdojo.safetravels.models.Owner;
import com.codingdojo.safetravels.services.ExpenseService;
import com.codingdojo.safetravels.services.OwnerService;


@Controller
public class HomeController {
	
	private final ExpenseService expenseservice;
	private final OwnerService ownerservice;
	
	@Autowired
	public HomeController(ExpenseService expenseservice, OwnerService ownerservice) {
		this.expenseservice = expenseservice;
		this.ownerservice = ownerservice;
	}
//	***if i need to display all the expenses on a different page
	//display all expense
//	@GetMapping("/dashboard")
//	public String showAllExpenses(Model model) {
//		model.addAttribute("expenses", expenseservice.allExpenses());
//		return "dashboard.jsp";
//	}
////	
//	
	
//	How to edit/update an expense

	
	@GetMapping("/editExpense/{id}")
	public String editExpenseForm(@PathVariable("id") Long id, @ModelAttribute("expense")Expense expense, Model model) {
		model.addAttribute("expense", expenseservice.findExpense(id));
		
		return "editExpense.jsp";
		
	}
	
//	Post of edit/update
	@RequestMapping(value="/updatingExpense/{id}", method=RequestMethod.PUT)
	public String updatingExpense(@Valid @ModelAttribute("expense") Expense expense, BindingResult result) {
		if(result.hasErrors()) {
			return "editExpense.jsp";
		} else {
			expenseservice.editExpense(expense);
			return "redirect:/";
		}
	}
	
	//Route to show one expense
	
	@GetMapping("/oneExpense/{id}")
	public String oneExpense(@PathVariable("id")Long id, Model model) {
		model.addAttribute("expense", expenseservice.findExpense(id));
		
		return "OneExpense.jsp";
	}
	
//	Route to delete an expense
	@GetMapping("/delete/{id}")
	public String deleteExpense(@PathVariable("id")Long id) {
		expenseservice.deleteExpense(id);
		return "redirect:/";
	}
	
	
		

	
	

	//	process to create new expense/ and display all on one index
	@RequestMapping("/")
    public String indexPage(@ModelAttribute("expense")Expense expense, Model model) {
		model.addAttribute("expenses", expenseservice.allExpenses());
		model.addAttribute("allOwners", ownerservice.allOwners());
    		return "index.jsp";
    
	
    }
	
	//Route to render New owner page
	@GetMapping("/newOwner")
	public String newOwnerForm(@ModelAttribute("owner") Owner owner) {
		return "newOwner.jsp";
	}
	
//process the post to create expense
	@PostMapping("/processExpense")
	public String postingExpense(@Valid @ModelAttribute("expense")Expense expense, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("expenses", expenseservice.allExpenses());
			model.addAttribute("allOwners", ownerservice.allOwners());
			return "index.jsp";
		} else {
			
			expenseservice.createExpense(expense);
			return "redirect:/";
		}
		
	
		
	}
	
	//Process the post to create owner
	@PostMapping("/processOwner")
	public String postingOwner(@Valid @ModelAttribute("owner")Owner owner, BindingResult result) {
		if(result.hasErrors()) {
			return "newOwner.jsp";
		} else {
			ownerservice.createOwner(owner);
			return "redirect:/";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
