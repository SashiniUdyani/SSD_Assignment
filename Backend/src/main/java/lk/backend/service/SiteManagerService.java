package lk.backend.service;

import java.util.List;
import lk.backend.entity.Material;
import lk.backend.entity.PurchaseOrder;

public interface SiteManagerService {

    Material addItem(Material material);

    List<Material> getItems();

    Material getItemById(String id);

    PurchaseOrder addPR(PurchaseOrder purchaseOrder);

    List<PurchaseOrder> getPRs();

    boolean removeItem(String id);

    boolean removeMaterial(String id);

    boolean editInfo(PurchaseOrder purchaseOrder);
}
