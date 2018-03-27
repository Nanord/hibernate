package service;

/**
 * Created by Nanord on 06.03.2018.
 */
public class Factory {
    private static UserService userService = null;
    private static OrderService orderService = null;
    private static CategoryService categoryService = null;
    private static ProductService productService = null;
    private static Factory instance = null;

    public static synchronized Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public static CategoryService getCategoryService() {
        if(categoryService == null) {
            categoryService = new CategoryService();
        }
        return categoryService;
    }

    public static UserService getUserService() {
        if(userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public static ProductService getProductService() {
        if(productService == null) {
            productService= new ProductService();
        }
        return productService;
    }

    public static OrderService getOrderService() {
        if(orderService == null) {
            orderService = new OrderService();
        }
        return orderService;
    }


}
