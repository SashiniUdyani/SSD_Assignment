package lk.backend.service.command;

import lk.backend.entity.PurchaseOrder;
import lk.backend.service.SupplierService;

public class FinalizePurchaseOrder implements CommandOrder {

    private SupplierService supplierService;

    public FinalizePurchaseOrder(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public PurchaseOrder finalizeOrder(PurchaseOrder purchaseOrder, String id, int rating) {
        return supplierService.finalizePurchaseOrder(purchaseOrder, id, rating);
    }
}
