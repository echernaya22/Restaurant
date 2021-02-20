package Services;

import DAO.Implementations.OrderDetailsDAO;
import Models.Dish;
import Models.OrderDetails;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class OrderDetailsService {
    private OrderDetailsDAO orderDetailsDb = new OrderDetailsDAO();

    public OrderDetailsService() {

    }

    public List<OrderDetails> addToListOfOrderDetails(Dish dish, int quantity) throws SQLException {
        List<OrderDetails> orderDetailsList = new LinkedList<>();
//        Scanner in = new Scanner(System.in);
//
//        while (numberOfPositions > 0) {
//            System.out.println("IN SET LIST");
//            OrderDetails orderDetails = new OrderDetails();
//            DishServices dishServices = new DishServices();
//            System.out.println("enter dish id");
//            int dishId = in.nextInt();
//
//            Dish dish = dishServices.getDishById(dishId);
//
//            orderDetails.setDish(dish);
//
//            System.out.println("enter quantity");
//            int quantity = in.nextInt();
//            orderDetails.setQuantity(quantity);
//
//            orderDetailsList.add(orderDetails);
//
//            numberOfPositions--;
//        }
//
//        in.close();
        OrderDetails orderDetails = null;

        orderDetails.setDish(dish);
        orderDetails.setQuantity(quantity);
        orderDetailsList.add(orderDetails);

        return orderDetailsList;
    }
}
