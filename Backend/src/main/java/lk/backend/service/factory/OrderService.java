package lk.backend.service.factory;

import java.util.List;
import lk.backend.entity.PurchaseOrder;
import lk.backend.entity.PurchaseOrderDetail;
import lk.backend.repository.PurchaseOrderDetailRepository;
import lk.backend.repository.PurchaseOrderRepository;

public interface OrderService {

    List<PurchaseOrder> getOrders(PurchaseOrderRepository purchaseOrderRepository, String id);

    PurchaseOrderDetail updatePR(PurchaseOrderDetailRepository purchaseOrderDetailRepository, PurchaseOrderDetail purchaseOrderDetail);

    boolean approveOrder(PurchaseOrderRepository purchaseOrderRepository, String orderId);

    boolean removePR(PurchaseOrderDetailRepository purchaseOrderDetailRepository, String itemId);

    boolean acceptOrder(PurchaseOrderRepository purchaseOrderRepository, String orderId);

    PurchaseOrder finalizePurchaseOrder(PurchaseOrderRepository purchaseOrderRepository, PurchaseOrder purchaseOrder, String id);

    PurchaseOrder addPR(PurchaseOrderRepository purchaseOrderRepository, PurchaseOrder purchaseOrder);

    List<PurchaseOrder> getPRs(PurchaseOrderRepository purchaseOrderRepository);

    boolean removeItem(PurchaseOrderDetailRepository purchaseOrderDetailRepository, String id);

    boolean removePurchaseOrder(PurchaseOrderRepository purchaseOrderRepository, String orderId);

    boolean rejectPurchaseOrder(PurchaseOrderRepository purchaseOrderRepository, String orderId);

    boolean editInfo(PurchaseOrderRepository purchaseOrderRepository, PurchaseOrder purchaseOrder);

    boolean deliverOrder(PurchaseOrderRepository purchaseOrderRepository, String orderId);

    boolean payOrder(PurchaseOrderRepository purchaseOrderRepository, String orderId);
}
