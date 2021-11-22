package arquiweb.spring.demo.filler;

import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import arquiweb.spring.demo.repositories.Bill_productRepository;
import arquiweb.spring.demo.entities.Bill;
import arquiweb.spring.demo.entities.Bill_Product;
import arquiweb.spring.demo.entities.Client;
import arquiweb.spring.demo.entities.Product;
import arquiweb.spring.demo.repositories.BillRepository;
import arquiweb.spring.demo.repositories.ClientRepository;
import arquiweb.spring.demo.repositories.ProductRepository;

import java.sql.Date;

@Configuration
public class DbFiller {
	
	@Bean
	public CommandLineRunner initDb (ProductRepository products, ClientRepository clients, 
			Bill_productRepository bill_products, BillRepository bills) {
		return args->{
			IntStream.range(0, 10).forEach(i->{
				Client c = new Client(i, "Client"+i, "Lastname" +i, "street 00" +i);
				c = clients.save(c);
				
				Product p = new Product("product" +i, 10, (long) 10+i);
				p = products.save(p);
				
				int number = (int)(Math.random() * 3);
				Bill b = new Bill(c, Date.valueOf("2021-11-08"), (long) 10+i);
				b = bills.save(b);
				
				Bill_Product bp = new Bill_Product(p, b, p.getPrice() * number, number);
				bill_products.save(bp);
				
			});
		};
	}

}
