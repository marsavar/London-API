package LondonAPI.London.UserClass;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL) // this is to avoid empty params such as 'city' when calling "/users"
public class User {

    @JsonProperty("id")
    private final long id;

    @JsonProperty("first_name")
    private final String first_name;

    @JsonProperty("last_name")
    private final String last_name;

    @JsonProperty("email")
    private final String email;

    @JsonProperty("ip_address")
    private final String ip_address;

    @JsonProperty("latitude")
    private final double latitude;

    @JsonProperty("longitude")
    private final double longitude;

    @JsonProperty("city")
    private final String city;

    @JsonCreator
    public User(@JsonProperty("id") long id, @JsonProperty("first_name") String first_name,
                @JsonProperty("last_name") String last_name, @JsonProperty("email") String email,
                @JsonProperty("ip_address") String ip_address, @JsonProperty("latitude") double latitude,
                @JsonProperty("longitude") double longitude, @JsonProperty("city")String city) {

        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.ip_address = ip_address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
    }


    public long getId() {
        return id;
    }


    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getIp_address() {
        return ip_address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCity() {
        return city;
    }

}
