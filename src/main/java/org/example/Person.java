package org.example;

import java.util.OptionalInt;

public class Person {
    protected final String name;
    protected final String surname;
    protected int age;
    protected String address;


    public Person(final PersonBuilder personBuilder) {
        this.name = personBuilder.name;
        this.surname = personBuilder.surname;
        setAddress(personBuilder.address);
        setAge(personBuilder.age);
    }

    public boolean hasAge() {
        return age > 0;
    }

    public boolean hasAddress() {
        return address != null && !address.isEmpty();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public OptionalInt getAge() {
        if (hasAge()) {
            return OptionalInt.of(age);
        } else {
            return OptionalInt.empty();
        }
    }

    public String getAddress() {
        if (hasAddress()) {
            return address;
        } else {
            return "";
        }
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void happyBirthday() {
        age++;
    }

    public void setAge(int age) {
        if (hasAge()) {
            happyBirthday();
        } else {
            this.age = age;
        }
    }

    @Override
    public String toString() {
        return "Имя - " + getName() +
                "\n Фамилия - " + getSurname() +
                "\n Возраст - " + getAge().orElse(0) +
                "\n Адрес - " + getAddress();
    }

    @Override
    public int hashCode() {
        return 31 * 17 * (getName().hashCode() + getSurname().hashCode());
    }

    public PersonBuilder newChildBuilder() {
        PersonBuilder childBuilder = new PersonBuilder();
        childBuilder.setAddress(this.getAddress());
        childBuilder.setSurname(this.getSurname());
        childBuilder.setName(childBuilder.name);
        return childBuilder;
    }
}