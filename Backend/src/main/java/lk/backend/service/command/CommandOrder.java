package lk.backend.service.command;

import lk.backend.entity.PurchaseOrder;

public interface CommandOrder {

    PurchaseOrder finalizeOrder(PurchaseOrder purchaseOrder, String id, int rating);

}
