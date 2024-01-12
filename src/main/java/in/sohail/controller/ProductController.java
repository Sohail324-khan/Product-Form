package in.sohail.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.sohail.entity.Product;
import in.sohail.repository.ProductRepository;

@Controller
public class ProductController {
	@Autowired
	private ProductRepository repo;

	@GetMapping("/edit")
	public String edit(@RequestParam("pid") Integer pid, Model model) {
		Optional<Product> findbyId = repo.findById(pid);
		if (findbyId.isPresent()) {
			Product product = findbyId.get();
			model.addAttribute("product", product);
		}

		return "index";
	}

	@GetMapping("/")
	public String loadForm(Model model) {
		model.addAttribute("product", new Product());
		return "index";
	}

	
	// for this method validation is used and BindingResult should come 2nd parameter then it will work
	@PostMapping("/product")
	public String saveProduct(@Validated @ModelAttribute("product") Product p,BindingResult result, Model model) {

		if (result.hasErrors()) 
		{
			System.out.println("grgrgrgwergrgwr");
			return "index";
		}

		p = repo.save(p);
		if (p.getPid() != null) {
			model.addAttribute("msg", "Product Saved");
		}

		return "index";
	}

	@GetMapping("/products")
	public String ViewProducts(Model model) {
		List<Product> list = repo.findAll();
		model.addAttribute("products", list);
		return "Data";
	}

	@GetMapping("/delete")
	public String DeleteProduct(@RequestParam("pid") Integer pid, Model model) {

		repo.deleteById(pid);
		model.addAttribute("msg", "Product Deleted");
		model.addAttribute("products", repo.findAll());

		return "Data";
	}

}
