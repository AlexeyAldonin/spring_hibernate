package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru",
              new Car("Lada", 2106)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru",
              new Car("BMW", 6)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }
      List<Car> cars = carService.listCars();
      for (Car car : cars) {
         System.out.println("car = " + car);
         System.out.println("userService.getByCar(car.getModel(), car.getSeries()) = " +
                 userService.getByCar(car.getModel(), car.getSeries()));
      }
      context.close();
   }
}
