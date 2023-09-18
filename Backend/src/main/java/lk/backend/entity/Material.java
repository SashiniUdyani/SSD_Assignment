package lk.backend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
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
public class Material implements IDCreator {

    @Id
    private String id;
    @Transient
    private String idFormatted;
    private String itemName;
    private String itemType;

    public String getFormattedId() {
        return "MAT" + id;
    }
}
