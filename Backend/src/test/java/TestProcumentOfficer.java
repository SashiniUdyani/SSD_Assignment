import lk.backend.entity.AppUser;
import lk.backend.entity.PurchaseOrder;
import lk.backend.entity.PurchaseOrderDetail;
import lk.backend.service.factory.OrderFactory;
import lk.backend.service.factory.OrderService;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class TestProcumentOfficer {

    @Test
    public void checkReturnObject() {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setPurchaseOrderDetails(new HashSet<>());

        PurchaseOrder newPurchaseOrder = new PurchaseOrder(purchaseOrder);

        Assert.assertNotEquals("Check object difference", purchaseOrder.getPurchaseOrderDetails(), newPurchaseOrder.getPurchaseOrderDetails());
    }

    @Test
    public void checkPOWithAppUser() {
        PurchaseOrder purchaseOrder = new PurchaseOrder();

        AppUser supplier = new AppUser();
        supplier.setName("Kamal");
        purchaseOrder.setSupplier(supplier);

        AppUser wareHouseManager = new AppUser();
        wareHouseManager.setName("Nimal");
        purchaseOrder.setWarehouseManager(wareHouseManager);

        Assert.assertEquals("Check supplier name", purchaseOrder.getSupplier().getName(), "Kamal");
    }

    @Test
    public void testSingleton() {
        OrderFactory orderFactory1 = OrderFactory.getOrderFactory();
        OrderFactory orderFactory2 = OrderFactory.getOrderFactory();
        Assert.assertEquals("Check the object difference", orderFactory1.hashCode(), orderFactory2.hashCode());
    }

}
