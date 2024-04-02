package ca.uottawa.csi2132.group196.spaghetti.DataClasses;

import ca.uottawa.csi2132.group196.spaghetti.Annotations.Autofill;
import ca.uottawa.csi2132.group196.spaghetti.Annotations.MappedField;
import ca.uottawa.csi2132.group196.spaghetti.Utils.AutoFillableClass;

import java.util.List;

public class HotelChain extends AutoFillableClass<HotelChain> {
    @MappedField("chain_name")
    @Autofill
    private String chainName = null;

    private int hotelCount = 0;

    private List<Address> addresses = null;

    private List<Contact> contacts = null;

    public String getChainName() {
        return chainName;
    }

    public void setChainName(String chainName) {
        this.chainName = chainName;
    }

    public int getHotelCount() {
        return hotelCount;
    }

    public void setHotelCount(int hotelCount) {
        this.hotelCount = hotelCount;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
