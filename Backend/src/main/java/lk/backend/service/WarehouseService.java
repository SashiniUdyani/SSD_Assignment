package lk.backend.service;

import java.util.List;

import lk.backend.entity.PurchaseOrder;

public interface WarehouseService {

    List<PurchaseOrder> getFinalizedPurchaseOrders(String warehouseId);

    PurchaseOrder finalizeSupplierOrder(PurchaseOrder purchaseOrder, String id, int rating);
}
