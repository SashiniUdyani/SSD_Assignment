import lk.backend.dto.PurchaseOrderDTO;
import lk.backend.entity.PurchaseOrder;
import lk.backend.entity.PurchaseOrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class TestWarehouseManager {

    @Test
    public void getFormattedDate() {
        PurchaseOrderDTO purchaseOrder = new PurchaseOrderDTO();
        purchaseOrder.setAddedAt(LocalDate.of(2022, 2, 5));
        Assert.assertEquals("Check the purchase order formatted date", purchaseOrder.getFormattedDate(), "2022-02-05");
    }

    @Test
    public void getSOItemTotalAmount() {
        PurchaseOrderDTO purchaseOrder = new PurchaseOrderDTO();
        purchaseOrder.setPurchaseOrderDetails(new HashSet<>());
        Set<PurchaseOrderDetail> purchaseOrderDetails = purchaseOrder.getPurchaseOrderDetails();

        PurchaseOrderDetail purchaseOrderDetail1 = new PurchaseOrderDetail();
        purchaseOrderDetail1.setSoQuantity(5);
        purchaseOrderDetail1.setSoUnitPrice(50);
        purchaseOrderDetails.add(purchaseOrderDetail1);

        PurchaseOrderDetail purchaseOrderDetail2 = new PurchaseOrderDetail();
        purchaseOrderDetail1.setSoQuantity(6);
        purchaseOrderDetail1.setSoUnitPrice(60);
        purchaseOrderDetails.add(purchaseOrderDetail2);

        PurchaseOrderDetail purchaseOrderDetail3 = new PurchaseOrderDetail();
        purchaseOrderDetail1.setSoQuantity(7);
        purchaseOrderDetail1.setSoUnitPrice(70);
        purchaseOrderDetails.add(purchaseOrderDetail3);

        Assert.assertEquals("Check the item quantity", purchaseOrder.getSOItemTotalAmount(), 490.0, 0.1);
    }

    @Test
    public void getSOItemQuantity() {
        PurchaseOrderDTO purchaseOrder = new PurchaseOrderDTO();
        purchaseOrder.setPurchaseOrderDetails(new HashSet<>());
        Set<PurchaseOrderDetail> purchaseOrderDetails = purchaseOrder.getPurchaseOrderDetails();

        PurchaseOrderDetail purchaseOrderDetail1 = new PurchaseOrderDetail();
        purchaseOrderDetail1.setSoQuantity(15);
        purchaseOrderDetails.add(purchaseOrderDetail1);

        PurchaseOrderDetail purchaseOrderDetail2 = new PurchaseOrderDetail();
        purchaseOrderDetail2.setSoQuantity(20);
        purchaseOrderDetails.add(purchaseOrderDetail2);

        PurchaseOrderDetail purchaseOrderDetail3 = new PurchaseOrderDetail();
        purchaseOrderDetail3.setSoQuantity(15);
        purchaseOrderDetails.add(purchaseOrderDetail3);

        Assert.assertEquals("Check the item quantity", purchaseOrder.getSOItemQuantity(), 50);
    }
}
