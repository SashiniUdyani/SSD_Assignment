package lk.backend.service.factory;

public class OrderFactory {

    private static OrderFactory orderFactory;

    private OrderFactory() {
    }

    public OrderService getOrderObj(String orderType) {
        if (orderType.equals("PurchaseOrder")) {
            return new PurchaseOrderService();
        } else if (orderType.equals("SupplierOrder")) {
            return new SupplierOrderService();
        }
        return null;
    }

    public static OrderFactory getOrderFactory() {
        if (orderFactory == null) {
            orderFactory = new OrderFactory();
        }
        return orderFactory;
    }
}
