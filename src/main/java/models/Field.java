package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Field
{
    @DatabaseField(id = true)
    private String address = "Here";

    public void setAddress(String address){
        address = address;
    }

    public Field(){

    }
}
