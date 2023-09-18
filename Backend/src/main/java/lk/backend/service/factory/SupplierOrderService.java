package lk.backend.service.factory;

import java.util.ArrayList;
import java.util.List;

import lk.backend.entity.PurchaseOrder;
import lk.backend.entity.PurchaseOrderDetail;
import lk.backend.repository.PurchaseOrderDetailRepository;
import lk.backend.repository.PurchaseOrderRepository;
import org.springframework.stereotype.Component;

@Component
public class SupplierOrderService implements OrderService {

    @Override
    public List<PurchaseOrder> getOrders(PurchaseOrderRepository purchaseOrderRepository, String id) {
        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.getAllBySupplierId(id);
        List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            PurchaseOrder purchaseOrderObj = new PurchaseOrder(purchaseOrder, null, null, purchaseOrder.getSiteManager());
            List<PurchaseOrderDetail> purchaseOrderDetails = new ArrayList<>();
            for (PurchaseOrderDetail purchaseOrderDetail : purchaseOrder.getPurchaseOrderDetails()) {
                purchaseOrderObj.setPoTotal(purchaseOrderObj.getPoTotal() + (purchaseOrderDetail.getPoUnitPrice() * purchaseOrderDetail.getPoQuantity()));
                purchaseOrderDetails.add(new PurchaseOrderDetail(purchaseOrderDetail));
            }
            purchaseOrderObj.setPurchaseOrderDetailList(purchaseOrderDetails);
            purchaseOrderList.add(purchaseOrderObj);
        }
        return purchaseOrderList;
    }

    @Override
    public PurchaseOrderDetail updatePR(PurchaseOrderDetailRepository purchaseOrderDetailRepository, PurchaseOrderDetail purchaseOrderDetail) {
        return null;
    }

    @Override
    public boolean approveOrder(PurchaseOrderRepository purchaseOrderRepository, String orderId) {
        return false;
    }

    @Override
    public boolean removePR(PurchaseOrderDetailRepository purchaseOrderDetailRepository, String itemId) {
        return false;
    }

    @Override
    public boolean acceptOrder(PurchaseOrderRepository purchaseOrderRepository, String orderId) {
        return false;
    }

    @Override
    public PurchaseOrder finalizePurchaseOrder(PurchaseOrderRepository purchaseOrderRepository, PurchaseOrder purchaseOrder, String id) {
        return null;
    }

    @Override
    public PurchaseOrder addPR(PurchaseOrderRepository purchaseOrderRepository, PurchaseOrder purchaseOrder) {
        return null;
    }

    @Override
    public List<PurchaseOrder> getPRs(PurchaseOrderRepository purchaseOrderRepository) {
        return null;
    }

    @Override
    public boolean removeItem(PurchaseOrderDetailRepository purchaseOrderDetailRepository, String id) {
        return false;
    }

    @Override
    public boolean removePurchaseOrder(PurchaseOrderRepository purchaseOrderRepository, String orderId) {
        return false;
    }

    @Override
    public boolean rejectPurchaseOrder(PurchaseOrderRepository purchaseOrderRepository, String orderId) {
        return false;
    }

    @Override
    public boolean editInfo(PurchaseOrderRepository purchaseOrderRepository, PurchaseOrder purchaseOrder) {
        return false;
    }

    @Override
    public boolean deliverOrder(PurchaseOrderRepository purchaseOrderRepository, String orderId) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(orderId).get();
        purchaseOrder.setPoStatus("Delivered");
        purchaseOrder.setSoStatus("Delivered");
        purchaseOrderRepository.save(purchaseOrder);
        return true;
    }

    @Override
    public boolean payOrder(PurchaseOrderRepository purchaseOrderRepository, String orderId) {
        return false;
    }

}
