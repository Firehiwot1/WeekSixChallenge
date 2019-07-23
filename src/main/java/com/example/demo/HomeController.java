package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

    @Controller
    public class HomeController {
        @Autowired
        CategoryRepository categoryRepository;

        @Autowired
        CarRepository carRepository;

        @RequestMapping("/")
        public String listCategory(Model model) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "categorylist";
        }

        @GetMapping("/addcategory")
        public String categoryForm(Model model) {
            model.addAttribute("car", carRepository.findAll());
            model.addAttribute("category", new Category());
            return "categoryform";
        }

        @PostMapping("/processcategory")
        public String processForm(@Valid Category category, BindingResult result, Model model) {
            if (result.hasErrors()) {
                model.addAttribute("cars", carRepository.findAll());
                return "categoryform";
            }
            categoryRepository.save(category);
            return "redirect:/addcars";
        }

        @RequestMapping("/detail/{id}")
        public String showCategory(@PathVariable("id") long id, Model model) {
            model.addAttribute("category", categoryRepository.findById(id).get());
            return "carlist";
        }

        @RequestMapping("/update/{id}")
        public String updateCategory(@PathVariable("id") long id, Model model) {
            model.addAttribute("cars", carRepository.findAll());
            model.addAttribute("category", categoryRepository.findById(id).get());
            return "categoryform";

        }

        @RequestMapping("/delete/{id}")
        public String delCategory(@PathVariable("id") long id) {
            categoryRepository.deleteById(id);
            return "redirect:/";
        }

        @RequestMapping("/addcar/{id}")
        public String addCars(@PathVariable("id") long id, Model model) {
            model.addAttribute("category", categoryRepository.findById(id).get());
            return "redirect:/addcars";
        }

        @GetMapping("/addcars")
        public String carForm(Model model) {
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("car", new Car());
            return "carform";
        }

        @RequestMapping("/detail/car/{id}")
        public String showCar(@PathVariable("id") long id, Model model){
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("car", carRepository.findById(id).get());
            return "carlist";
        }
        @RequestMapping("/update/car{id}")
        public String updateCar(@PathVariable("id") long id, Model model){
            model.addAttribute("car", carRepository.findById(id).get());
            return "carform";

        }
        @RequestMapping("/delete/car{id}")
        public  String delCar(@PathVariable("id") long id){
            carRepository.deleteById(id);
            return "redirect:/";
        }
    }
