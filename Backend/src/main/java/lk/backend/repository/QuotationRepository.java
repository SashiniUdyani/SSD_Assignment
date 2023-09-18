package lk.backend.repository;

import lk.backend.entity.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuotationRepository extends JpaRepository<Quotation, String> {

    List<Quotation> getAllByPurchaseOrderSiteManagerCompanyCompanyId(String company);

    List<Quotation> getAllByPurchaseOrderId(String orderId);

    Quotation getAllByPurchaseOrderIdAndSupplierId(String orderId, String supplierId);

    List<Quotation> getAllBySupplierId(String supplierId);
}
