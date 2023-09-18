import lk.backend.dto.PurchaseOrderDTO;
import lk.backend.entity.PurchaseOrder;
import lk.backend.entity.PurchaseOrderDetail;
import lk.backend.service.factory.OrderFactory;
import lk.backend.service.factory.OrderService;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class TestSupplier {

    @Test
    public void getPOItemTotalAmount() {
        PurchaseOrderDTO purchaseOrder = new PurchaseOrderDTO();
        purchaseOrder.setPurchaseOrderDetails(new HashSet<>());
        Set<PurchaseOrderDetail> purchaseOrderDetails = purchaseOrder.getPurchaseOrderDetails();

        PurchaseOrderDetail purchaseOrderDetail1 = new PurchaseOrderDetail();
        purchaseOrderDetail1.setPoQuantity(8);
        purchaseOrderDetail1.setPoUnitPrice(50);
        purchaseOrderDetails.add(purchaseOrderDetail1);

        PurchaseOrderDetail purchaseOrderDetail2 = new PurchaseOrderDetail();
        purchaseOrderDetail1.setPoQuantity(6);
        purchaseOrderDetail1.setPoUnitPrice(60);
        purchaseOrderDetails.add(purchaseOrderDetail2);

        PurchaseOrderDetail purchaseOrderDetail3 = new PurchaseOrderDetail();
        purchaseOrderDetail1.setPoQuantity(3);
        purchaseOrderDetail1.setPoUnitPrice(70);
        purchaseOrderDetails.add(purchaseOrderDetail3);

        Assert.assertEquals("Check the item quantity", purchaseOrder.getPOItemTotalAmount(), 490.0, 0.1);
    }

    @Test
    public void testFactory() {
        OrderFactory orderFactory1 = OrderFactory.getOrderFactory();
        OrderService supplierOrder1 = orderFactory1.getOrderObj("SupplierOrder");
        OrderFactory orderFactory2 = OrderFactory.getOrderFactory();
        OrderService supplierOrder2 = orderFactory2.getOrderObj("SupplierOrder");
        Assert.assertNotEquals("Check the object difference", supplierOrder1.hashCode(), supplierOrder2.hashCode());
    }

    @Test
    public void checkOrderDetailId() {
        PurchaseOrderDetail purchaseOrderD = new PurchaseOrderDetail();
        purchaseOrderD.setId(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss")));
        Assert.assertEquals("Check the purchase order detail id creation", purchaseOrderD.getFormattedId(), "OD" + purchaseOrderD.getId());
    }
}
