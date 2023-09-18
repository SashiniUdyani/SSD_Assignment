package lk.backend.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
public class PurchaseOrder implements IDCreator {

    @Id
    private String id;
    @Transient
    private String idFormatted;
    private String orderReference;
    private String deliverNote;
    @ManyToOne
    private AppUser warehouseManager;
    @ManyToOne
    private AppUser supplier;
    @ManyToOne
    private AppUser procumentOfficer;
    @ManyToOne
    private AppUser siteManager;
    @Transient
    private int poQuantity;
    @Transient
    private int soQuantity;
    @Transient
    private double poTotal;
    @Transient
    private double soTotal;
    private String poStatus;
    private String soStatus;
    private boolean poFinalized;
    private boolean soFinalized;
    private boolean poAccepted;
    private boolean poApproved;
    private LocalDate addedAt;
    @Transient
    private String addedAtFormatted;
    private String siteName;
    private int priority;
    private String note;
    private String handlingInstruction;
    private int supplierRate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchaseOrder")
    private Set<PurchaseOrderDetail> purchaseOrderDetails;
    @Transient
    private List<PurchaseOrderDetail> purchaseOrderDetailList;

    public PurchaseOrder(PurchaseOrder purchaseOrder) {
        this.id = purchaseOrder.id;
        this.poStatus = purchaseOrder.poStatus;
        this.soStatus = purchaseOrder.soStatus;
        this.poQuantity = purchaseOrder.poQuantity;
        this.soQuantity = purchaseOrder.soQuantity;
        this.poFinalized = purchaseOrder.poFinalized;
        this.soFinalized = purchaseOrder.soFinalized;
        this.orderReference = purchaseOrder.orderReference;
        this.deliverNote = purchaseOrder.deliverNote;
        this.poAccepted = purchaseOrder.poAccepted;
        this.poApproved = purchaseOrder.poApproved;
        this.addedAt = purchaseOrder.addedAt;
        this.siteName = purchaseOrder.siteName;
        this.priority = purchaseOrder.priority;
        this.note = purchaseOrder.note;
        this.handlingInstruction = purchaseOrder.handlingInstruction;
        this.supplierRate = purchaseOrder.supplierRate;
    }

    public PurchaseOrder(PurchaseOrder purchaseOrder, AppUser warehouseManager, AppUser supplier, AppUser siteManager) {
        this(purchaseOrder);
        if (supplier != null) {
            this.supplier = new AppUser(supplier);
        }
        if (warehouseManager != null) {
            this.warehouseManager = new AppUser(warehouseManager);
        }
        if (siteManager != null) {
            this.siteManager = new AppUser(siteManager);
        }
    }

    public String getFormattedId() {
        return "PO" + id;
    }

}
