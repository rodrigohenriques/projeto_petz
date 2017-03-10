package br.com.projeto.pets.model;

import java.io.Serializable;

/**
 * @author srolemberg
 * @since  13/02/17
 * @version 0.0.5
 */

public class User implements Serializable {

    private Long id;//pk nn
    private String name;//80 nn
    private String address;//90
    private String email;//100nn
    private String password;//
    private String addressNumber;//10
    private String state;//80
    private String city;//80
    private String zipCode;//8
    private String phone;//12
    private String registerAt;//nn
    private String lastUpdate;//
    private Boolean active;//nn
    private Session session;


    private User() {
    }

    private User(Builder builder) {
        id = builder.id;
        name = builder.name;
        address = builder.address;
        email = builder.email;
        password = builder.password;
        addressNumber = builder.addressNumber;
        state = builder.state;
        city = builder.city;
        zipCode = builder.zipCode;
        phone = builder.phone;
        registerAt = builder.registerAt;
        lastUpdate = builder.lastUpdate;
        active = builder.active;
        session = builder.session;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(User copy) {
        Builder builder = new Builder();
        builder.id = copy.id;
        builder.name = copy.name;
        builder.address = copy.address;
        builder.email = copy.email;
        builder.password = copy.password;
        builder.addressNumber = copy.addressNumber;
        builder.state = copy.state;
        builder.city = copy.city;
        builder.zipCode = copy.zipCode;
        builder.phone = copy.phone;
        builder.registerAt = copy.registerAt;
        builder.lastUpdate = copy.lastUpdate;
        builder.active = copy.active;
        builder.session = copy.session;
        return builder;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getRegisterAt() {
        return registerAt;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public Boolean getActive() {
        return active;
    }

    public Session getSession() {
        return session;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (address != null ? !address.equals(user.address) : user.address != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null)
            return false;
        if (addressNumber != null ? !addressNumber.equals(user.addressNumber) : user.addressNumber != null)
            return false;
        if (state != null ? !state.equals(user.state) : user.state != null) return false;
        if (city != null ? !city.equals(user.city) : user.city != null) return false;
        if (zipCode != null ? !zipCode.equals(user.zipCode) : user.zipCode != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        if (registerAt != null ? !registerAt.equals(user.registerAt) : user.registerAt != null)
            return false;
        if (lastUpdate != null ? !lastUpdate.equals(user.lastUpdate) : user.lastUpdate != null)
            return false;
        if (active != null ? !active.equals(user.active) : user.active != null) return false;
        return session != null ? session.equals(user.session) : user.session == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (addressNumber != null ? addressNumber.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (registerAt != null ? registerAt.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (session != null ? session.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "session=" + session +
                ", active=" + active +
                ", lastUpdate='" + lastUpdate + '\'' +
                ", registerAt='" + registerAt + '\'' +
                ", phone='" + phone + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", addressNumber='" + addressNumber + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }


    public static final class Builder {
        private Long id;
        private String name;
        private String address;
        private String email;
        private String password;
        private String addressNumber;
        private String state;
        private String city;
        private String zipCode;
        private String phone;
        private String registerAt;
        private String lastUpdate;
        private Boolean active;
        private Session session;

        private Builder() {
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withAddress(String val) {
            address = val;
            return this;
        }

        public Builder withEmail(String val) {
            email = val;
            return this;
        }

        public Builder withPassword(String val) {
            password = val;
            return this;
        }

        public Builder withAddressNumber(String val) {
            addressNumber = val;
            return this;
        }

        public Builder withState(String val) {
            state = val;
            return this;
        }

        public Builder withCity(String val) {
            city = val;
            return this;
        }

        public Builder withZipCode(String val) {
            zipCode = val;
            return this;
        }

        public Builder withPhone(String val) {
            phone = val;
            return this;
        }

        public Builder withRegisterAt(String val) {
            registerAt = val;
            return this;
        }

        public Builder withLastUpdate(String val) {
            lastUpdate = val;
            return this;
        }

        public Builder withActive(Boolean val) {
            active = val;
            return this;
        }

        public Builder withSession(Session val) {
            session = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
