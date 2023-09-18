package lk.backend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.util.List;
import lk.backend.util.IDCreator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AppUser implements IDCreator {

    @Id
    private String id;
    @Transient
    private String idFormatted;
    @Transient
    private boolean sent;
    private String name;
    private String email;
    private String password;
    private String userType;
    private String contactNumber;
    @ManyToOne
    private Company company;
    @Transient
    private List<PurchaseOrderDetail> purchaseOrderDetailList;

    public AppUser(AppUser appUser) {
        this.id = appUser.id;
        this.name = appUser.name;
        this.email = appUser.email;
        this.password = appUser.password;
        this.userType = appUser.userType;
        this.contactNumber = appUser.contactNumber;
        this.company = appUser.company;
    }

    public String getFormattedId() {
        return "U" + id;
    }
}
