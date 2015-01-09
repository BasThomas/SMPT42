package smpt42.nl.printmanager.view;

/**
 * Created by Pieter on 19-12-2014.
 */
public class HistoryItemRow
{
    //TODO: use get/set
    public int itemId;
    public String Title;
    public String Company;

    public HistoryItemRow(int itemId, String Title, String Company)
    {
        this.itemId = itemId;
        this.Title = Title;
        this.Company = Company;
    }
}