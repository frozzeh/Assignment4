package com.airline.reservation_api.model;

import java.util.Objects;

public class Passenger {
    private Long id;
    private String name;
    private int age;

    public Passenger() {}

    public Passenger(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Passenger(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "Passenger{id=" + id + ", name='" + name + "', age=" + age + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Passenger)) return false;
        Passenger other = (Passenger) obj;
        return age == other.age && Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}