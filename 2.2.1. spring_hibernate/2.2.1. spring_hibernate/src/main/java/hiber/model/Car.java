package hiber.model;

import javax.persistence.*;

@Entity (name = "cars")
@Table (name = "cars")
public class Car {

    @Id
    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "model")
    private String model;

    @Column(name = "series")
    private int series;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car() {}

    public Car(String model, int series){
        this.model = model;
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(long id) {
        this.user_id = id;
    }
}