package lk.backend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuotationDetail {

    @Id
    private String id;
    @Transient
    private String idFormatted;
    @ManyToOne
    private Quotation quotation;
    @ManyToOne
    private PurchaseOrderDetail purchaseOrderDetail;
    private double soUnitPrice;
    private int soQuantity;
    private String status;

    public QuotationDetail(QuotationDetail purchaseOrderDetail) {
        this.id = purchaseOrderDetail.id;
        this.soUnitPrice = purchaseOrderDetail.soUnitPrice;
        this.soQuantity = purchaseOrderDetail.soQuantity;
        this.status = purchaseOrderDetail.status;
    }

}
