package lk.backend.service;

import java.util.List;
import lk.backend.entity.AppUser;
import lk.backend.entity.PurchaseOrder;
import lk.backend.entity.PurchaseOrderDetail;

public interface ProcumentOfficerService {

    boolean removePR(String itemId);

    PurchaseOrderDetail updatePR(PurchaseOrderDetail purchaseOrderDetail);

    boolean approveOrder(String orderId);

    List<AppUser> getSuppliers();

    boolean sendSupplier(String purchaseOrder, String supplier);

    List<PurchaseOrder> viewQuotations(String companyId);

    List<AppUser> quotationSuppliers(String poId);

    List<PurchaseOrderDetail> quotationDetails(String poId, String supplierId);

    boolean finalizeSupplier(String poId, String supplierId);

    boolean removePurchaseOrder(String orderId);

    boolean rejectPurchaseOrder(String orderId);

    boolean payOrder(String orderId);
}
