package Classes;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Component
@Entity
public class Student {

    @NotBlank(message = "Name is required")

    private String name;

    @Id
    @Min(value = 1, message = "Id cannot be less than 0")
    @Max(value = 100, message = "Id cannot be more than 100")
    private int roll;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Course> course = new ArrayList<>();

    public List<Course> getCourse() {
        return course;
    }

    public void setCourse(List<Course> course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }
}
