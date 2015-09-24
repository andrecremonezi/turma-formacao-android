package br.com.cast.turmaformacao.taskmanager.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address implements Parcelable{

    @JsonProperty("cep")
    private String zipCode;

    @JsonProperty("tipoDeLogradouro")
    private String type;

    @JsonProperty("logradouro")
    private String street;

    @JsonProperty("bairro")
    private String neighborhood;

    @JsonProperty("cidade")
    private String city;

    @JsonProperty("estado")
    private String state;

    public Address(String zipCode, String type, String street, String neighborhood, String city, String state) {
        this.zipCode = zipCode;
        this.type = type;
        this.street = street;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
    }

    public Address(){
        super();
    }

    public Address(Parcel in){
        super();
        readToParcel(in);
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address adress = (Address) o;

        if (!zipCode.equals(adress.zipCode)) return false;
        if (!type.equals(adress.type)) return false;
        if (!street.equals(adress.street)) return false;
        if (!neighborhood.equals(adress.neighborhood)) return false;
        if (!city.equals(adress.city)) return false;
        return state.equals(adress.state);

    }

    @Override
    public int hashCode() {
        int result = zipCode.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + street.hashCode();
        result = 31 * result + neighborhood.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + state.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(zipCode == null ? "" : zipCode);
        dest.writeString(type == null ? "" : type);
        dest.writeString(street == null ? "" : street);
        dest.writeString(neighborhood == null ? "" : neighborhood);
        dest.writeString(city == null ? "" : city);
        dest.writeString(state == null ? "" : state);
    }

    public void readToParcel(Parcel in) {
        zipCode = in.readString();
        type = in.readString();
        street = in.readString();
        neighborhood = in.readString();
        city = in.readString();
        state = in.readString();
    }

    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
        public Address createFromParcel(Parcel source) {
            return new Address(source);
        }

        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
}
