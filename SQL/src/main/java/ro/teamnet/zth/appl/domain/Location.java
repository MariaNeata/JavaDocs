package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

/**
 * Created by MN on 4/28/2015.
 */
@Table(name="Locations")
public class Location {
    @Id(name="LOCATION_ID")
    private int id;
    @Column(name="street_address")
    private String street_address;
    @Column(name="postal_code")
    private String postalCode;
    @Column(name="city")
    private String city;
    @Column(name="state_province")
    private String stateProvince;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (!city.equals(location.city)) return false;
        if (!(id == location.id)) return false;
        if (!postalCode.equals(location.postalCode)) return false;
        if (!stateProvince.equals(location.stateProvince)) return false;
        if (!street_address.equals(location.street_address)) return false;

        return true;
    }
}
