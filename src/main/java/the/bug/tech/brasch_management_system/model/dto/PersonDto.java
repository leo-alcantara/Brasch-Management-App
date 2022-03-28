package the.bug.tech.brasch_management_system.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class PersonDto {

    private String personName;
    private String email;
    private String phoneNumber;

    @JsonCreator
    public PersonDto(@JsonProperty("firstName") String personName,
                     @JsonProperty("email") String email,
                     @JsonProperty("phoneNumber") String phoneNumber) {
        this.personName = personName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDto personDto = (PersonDto) o;
        return Objects.equals(getPersonName(), personDto.getPersonName()) && Objects.equals(getEmail(), personDto.getEmail()) && Objects.equals(getPhoneNumber(), personDto.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPersonName(), getEmail(), getPhoneNumber());
    }

    @Override
    public String toString() {
        return "PersonDto{" +
                "personName='" + personName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
