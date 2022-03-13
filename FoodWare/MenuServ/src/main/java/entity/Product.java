package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private int id;
    @Column(nullable = false, name = "name")
    private String name;
    @Column(nullable = false, name = "price")
    private double price;
    @Column(nullable = false, name = "description")
    private String description;
    @OneToMany
    @JoinColumn(nullable = false, name = "category_id")
    private int categoryId;
    @Column(name = "image")
    private byte[] image;
}
