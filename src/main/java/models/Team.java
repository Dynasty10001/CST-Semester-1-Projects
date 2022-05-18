package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Team
{
    @DatabaseField(generatedId = true)
    public int teamID;

    @Override
    public String toString(){
        return "" + teamID;
    }
}
