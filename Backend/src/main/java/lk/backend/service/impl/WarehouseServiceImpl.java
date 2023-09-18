package lk.backend.service.impl;

import java.util.List;

import lk.backend.entity.PurchaseOrder;
import lk.backend.repository.PurchaseOrderRepository;
import lk.backend.service.WarehouseService;
import lk.backend.service.factory.OrderFactory;
import lk.backend.util.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    private OrderFactory orderFactory = OrderFactory.getOrderFactory();

    @Override
    public List<PurchaseOrder> getFinalizedPurchaseOrders(String warehouseId) {
        return orderFactory.getOrderObj(CommonConstants.PURCHASE_ORDER).getOrders(purchaseOrderRepository, warehouseId);
    }

    @Override
    public PurchaseOrder finalizeSupplierOrder(PurchaseOrder purchaseOrder, String id, int rating) {
        PurchaseOrder purchaseOrderObj = purchaseOrderRepository.findById(id).get();
        purchaseOrderObj.setPoStatus("Completed");
        purchaseOrderObj.setSoStatus("Completed");
        purchaseOrderObj.setSupplierRate(rating);
        purchaseOrderRepository.save(purchaseOrderObj);
        return null;
    }
}
