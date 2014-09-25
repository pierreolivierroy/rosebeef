package ca.etsmtl.gti710.controllers;
import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ca.etsmtl.gti710.models.Produit;
import ca.etsmtl.gti710.providers.IProvider;
import ca.etsmtl.gti710.providers.StubProvider;

@Controller
@RequestMapping("/")
public class ProductsController {
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Collection<Produit> printProducts(ModelMap model) {
		IProvider stub = new StubProvider();
		return stub.getProducts();
	}	
}