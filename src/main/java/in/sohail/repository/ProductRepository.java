package in.sohail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sohail.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
