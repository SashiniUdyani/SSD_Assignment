package lk.backend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import lk.backend.util.IDCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderDetail implements IDCreator {

    @Id
    private String id;
    @Transient
    private String idFormatted;
    @ManyToOne
    private PurchaseOrder purchaseOrder;
    @ManyToOne
    private Material material;
    private double poUnitPrice;
    private int poQuantity;
    private double soUnitPrice;
    private int soQuantity;
    private String status;
    @Transient
    private String quotationDetailId;

    public PurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail) {
        this.id = purchaseOrderDetail.id;
        this.material = purchaseOrderDetail.material;
        this.poUnitPrice = purchaseOrderDetail.poUnitPrice;
        this.poQuantity = purchaseOrderDetail.poQuantity;
        this.soUnitPrice = purchaseOrderDetail.soUnitPrice;
        this.soQuantity = purchaseOrderDetail.soQuantity;
        this.status = purchaseOrderDetail.status;
    }

    public PurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail, PurchaseOrder purchaseOrder, AppUser warehouseManager, AppUser supplier, AppUser siteManager) {
        this(purchaseOrderDetail);
        if (purchaseOrder != null && warehouseManager != null && supplier != null) {
            this.purchaseOrder = new PurchaseOrder(purchaseOrder, warehouseManager, supplier, siteManager);
        }
    }

    public String getFormattedId() {
        return "OD" + id;
    }
}
