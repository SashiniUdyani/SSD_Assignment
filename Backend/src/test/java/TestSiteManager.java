import lk.backend.dto.PurchaseOrderDTO;
import lk.backend.entity.PurchaseOrder;
import lk.backend.entity.PurchaseOrderDetail;
import lk.backend.repository.PurchaseOrderRepository;
import lk.backend.service.WarehouseService;
import lk.backend.service.factory.OrderFactory;
import lk.backend.service.factory.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class TestSiteManager {

    @Test
    public void checkPRId() {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss")));
        Assert.assertEquals("Check the purchase order id creation", purchaseOrder.getFormattedId(), "PO" + purchaseOrder.getId());
    }

    @Test
    public void getPOItemQuantity() {
        PurchaseOrderDTO purchaseOrder = new PurchaseOrderDTO();
        purchaseOrder.setPurchaseOrderDetails(new HashSet<>());
        Set<PurchaseOrderDetail> purchaseOrderDetails = purchaseOrder.getPurchaseOrderDetails();

        PurchaseOrderDetail purchaseOrderDetail1 = new PurchaseOrderDetail();
        purchaseOrderDetail1.setPoQuantity(5);
        purchaseOrderDetails.add(purchaseOrderDetail1);

        PurchaseOrderDetail purchaseOrderDetail2 = new PurchaseOrderDetail();
        purchaseOrderDetail2.setPoQuantity(10);
        purchaseOrderDetails.add(purchaseOrderDetail2);

        PurchaseOrderDetail purchaseOrderDetail3 = new PurchaseOrderDetail();
        purchaseOrderDetail3.setPoQuantity(15);
        purchaseOrderDetails.add(purchaseOrderDetail3);

        Assert.assertEquals("Check the item quantity", purchaseOrder.getPOItemQuantity(), 30);
    }

    @Test
    public void testFactory() {
        OrderFactory orderFactory1 = OrderFactory.getOrderFactory();
        OrderService purchaseOrder1 = orderFactory1.getOrderObj("PurchaseOrder");
        OrderFactory orderFactory2 = OrderFactory.getOrderFactory();
        OrderService purchaseOrder2 = orderFactory2.getOrderObj("PurchaseOrder");
        Assert.assertNotEquals("Check the object difference", purchaseOrder1.hashCode(), purchaseOrder2.hashCode());
    }
}
