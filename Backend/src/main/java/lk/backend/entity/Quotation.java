package lk.backend.entity;

import javax.persistence.*;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Quotation {

    @Id
    private String id;
    @Transient
    private String idFormatted;
    @ManyToOne
    private PurchaseOrder purchaseOrder;
    @ManyToOne
    private AppUser supplier;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quotation")
    private Set<QuotationDetail> quotationDetails;

}
