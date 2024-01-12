package in.sohail.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Product {
	@Id
	@GeneratedValue()
	private Integer pid;
	
	@NotBlank(message ="Name is mandatory")
	//@Size(min =3 , max =10 ,message ="Name should have 3 to 10 character")
	private String name;
	
	private Double price;
	
	private Integer qty;
}
