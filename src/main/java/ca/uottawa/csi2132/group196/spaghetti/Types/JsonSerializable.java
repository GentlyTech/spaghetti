package ca.uottawa.csi2132.group196.spaghetti.Types;

import com.google.gson.Gson;

public class JsonSerializable {
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
    
    public String toJson() {
        return this.toString();
    }
}
