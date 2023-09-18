package lk.backend.repository;

import lk.backend.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, String> {

    List<PurchaseOrder> getAllBySupplierId(String supplierId);

    List<PurchaseOrder> getAllBySiteManagerCompanyCompanyIdAndPoFinalized(String company, boolean finalized);
}
