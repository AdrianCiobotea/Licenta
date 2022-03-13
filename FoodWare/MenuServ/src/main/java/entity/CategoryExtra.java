package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "category_extra")
public class CategoryExtra {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private int id;
    @Column(name = "category_id")
    private int categoryId;
    @Column(name = "extra_id")
    private int extraId;
}
