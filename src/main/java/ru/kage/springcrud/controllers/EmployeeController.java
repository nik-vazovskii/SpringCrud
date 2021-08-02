package ru.kage.springcrud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kage.springcrud.dao.EmployeeDAO;
import ru.kage.springcrud.models.Employee;

import javax.validation.Valid;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeController(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @GetMapping
    public String show(Model model) {
        model.addAttribute("employees", employeeDAO.show() );
        return "employee/showAll";
    }

    @GetMapping("/{id}")
    public String index(@PathVariable("id") long id, Model model ) {
        model.addAttribute("employee", employeeDAO.index( id ) );
        return "employee/showOne";
    }

    @GetMapping("/new")
    public String newEmployee(@ModelAttribute("employee") Employee employee ) {
        return "employee/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("employee") @Valid Employee employee,
                         BindingResult bindingResult ) {
        if( bindingResult.hasErrors() ) {
            return "employee/new";
        }
        employeeDAO.save( employee );
        return "redirect:/employee";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("employee", employeeDAO.index( id ) );
        return "employee/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("employee") @Valid Employee employee,
                         BindingResult bindingResult,
                         @PathVariable("id") long id ) {
        if( bindingResult.hasErrors() ) {
            return "employee/edit";
        }
        employeeDAO.update( id, employee );
        return "redirect:/employee";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        employeeDAO.delete( id );
        return "redirect:/employee";
    }
}
