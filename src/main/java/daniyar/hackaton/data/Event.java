package daniyar.hackaton.data;

import java.util.List;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "event")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Column(name = "name", length = 35)
    private String name;

    @Column(name = "description")
    @Length(min = 3, max = 1000)
    private String description;

    @Column(name = "price")
    private int price;

    @Column(name = "isPaid")
    private boolean isPaid;

    @Column(name = "min_people")
    private int min_people;

    @Column(name = "max_people")
    private int max_people;

    @Column(name = "cases")
    private List<String> cases;

    @Column(name = "isPrivate")
    private boolean isPrivate;
}
