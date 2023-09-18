package lk.backend.dto;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import lk.backend.entity.AppUser;
import lk.backend.entity.PurchaseOrder;
import lk.backend.entity.PurchaseOrderDetail;
import lk.backend.util.IDCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderDTO implements IDCreator {

    private String id;
    private String idFormatted;
    private String orderReference;
    private String deliverNote;
    private AppUser warehouseManager;
    private AppUser supplier;
    private int poQuantity;
    private int soQuantity;
    private double poTotal;
    private double soTotal;
    private String poStatus;
    private String soStatus;
    private boolean poFinalized;
    private boolean soFinalized;
    private boolean poAccepted;
    private LocalDate addedAt;
    private String addedAtFormatted;
    private String siteName;
    private int priority;
    private String note;
    private String handlingInstruction;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchaseOrder")
    private Set<PurchaseOrderDetail> purchaseOrderDetails;
    @Transient
    private List<PurchaseOrderDetail> purchaseOrderDetailList;

    public PurchaseOrderDTO(PurchaseOrderDTO purchaseOrder) {
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
        this.addedAt = purchaseOrder.addedAt;
        this.siteName = purchaseOrder.siteName;
        this.priority = purchaseOrder.priority;
        this.note = purchaseOrder.note;
        this.handlingInstruction = purchaseOrder.handlingInstruction;
    }

    public PurchaseOrderDTO(PurchaseOrderDTO purchaseOrder, AppUser warehouseManager, AppUser supplier) {
        this(purchaseOrder);
        if (supplier != null) {
            this.supplier = new AppUser(supplier);
        }
        if (warehouseManager != null) {
            this.warehouseManager = new AppUser(warehouseManager);
        }
    }

    public String getFormattedId() {
        return "PO" + id;
    }

    public int getSOItemQuantity() {
        int total = 0;
        for (PurchaseOrderDetail purchaseOrderDetail : purchaseOrderDetails) {
            total += purchaseOrderDetail.getSoQuantity();
        }
        return total;
    }

    public int getPOItemQuantity() {
        int total = 0;
        for (PurchaseOrderDetail purchaseOrderDetail : purchaseOrderDetails) {
            total += purchaseOrderDetail.getPoQuantity();
        }
        return total;
    }

    public double getSOItemTotalAmount() {
        double total = 0;
        for (PurchaseOrderDetail purchaseOrderDetail : purchaseOrderDetails) {
            total += (purchaseOrderDetail.getSoQuantity() * purchaseOrderDetail.getSoUnitPrice());
        }
        return total;
    }

    public double getPOItemTotalAmount() {
        double total = 0;
        for (PurchaseOrderDetail purchaseOrderDetail : purchaseOrderDetails) {
            total += (purchaseOrderDetail.getPoQuantity() * purchaseOrderDetail.getPoUnitPrice());
        }
        return total;
    }

    public String getFormattedDate() {
        return addedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void poDetailsToPoMapper(PurchaseOrder purchaseOrder) {
        for (PurchaseOrderDetail purchaseOrderDetail : purchaseOrderDetails) {
            purchaseOrderDetail.setPurchaseOrder(purchaseOrder);
        }
    }
}
