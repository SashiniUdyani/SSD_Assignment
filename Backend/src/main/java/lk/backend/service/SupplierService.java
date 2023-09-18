package lk.backend.service;

import java.util.List;

import lk.backend.entity.PurchaseOrder;

public interface SupplierService {

    List<PurchaseOrder> getSupplierOrders(String supplierId);

    boolean acceptOrder(String orderId);

    PurchaseOrder finalizePurchaseOrder(PurchaseOrder purchaseOrder, String id, int rating);

    List<PurchaseOrder> getQuotations(String supplierId);

    boolean finalizeQuotation(PurchaseOrder purchaseOrder, String supplierId);

    boolean deliverOrder(String orderId);
}
