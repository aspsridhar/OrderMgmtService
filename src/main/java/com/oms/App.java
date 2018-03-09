package com.oms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.oms.business.dao.repo.OrderRepository;
import com.oms.business.model.OrderDataBean;

/**
 * 
 * @author asridhar2
 *
 */
@SpringBootApplication

/*(scanBasePackages={"com.oms.business.dao","com.oms.business.dao.repo"
		,"com.oms.business.domain","com.oms.business.model"
		,"com.oms.business.service","com.oms.common.util"
		,"com.oms.controller","com.oms.exception"})
*/
public class App 
{
	private static final Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	public CommandLineRunner setup(OrderRepository orderRepo) {
		/*public CommandLineRunner setup(OrderRepo orderRepo) {*/
			return (args) -> {

				orderRepo.create(new OrderDataBean(1001L, 10.10,10, "Bricks", "In Process", false, "Order total of 10",
						1234578));
				orderRepo.create(new OrderDataBean(1002L, 10.10,5, "Bricks", "Delevered", true, "Order total of 5",
						44567892));
				orderRepo.create(new OrderDataBean(1003L, 10.10,20, "Bricks", "Pending", false, "Order total of 20",
						1276890));
				orderRepo.create(new OrderDataBean(1004L, 10.10,6, "Bricks", "Que", false, "Order total of 6",
						123434343));
				logger.info("The sample data has been generated");
			};
		}
	
	/*@Bean
	public CommandLineRunner setup(OrderRepo orderRepo) {
		return (args) -> {

			orderRepo.save(new OrderDO(1001L, 33.21, "Bricks", "In Process", false, "Order total of 20",
					1234578));
			orderRepo.save(new OrderDO(1002L, 43.23, "Bricks", "Delevered", true, "Order total of 20",
					44567892));
			orderRepo.save(new OrderDO(1003L, 12.12, "Bricks", "Pending", false, "Order total of 20",
					1276890));
			orderRepo.save(new OrderDO(1004L, 10.10, "Bricks", "Que", false, "Order total of 20",
					123434343));
			logger.info("The sample data has been generated");
		};
	}*/
}
