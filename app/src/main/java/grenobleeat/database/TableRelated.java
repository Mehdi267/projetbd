package grenobleeat.database;

import java.util.Map;

public class TableRelated extends Table{
    private Map<String, String> relatedTable;

    public TableRelated(String tableName, String[] fields){
       super(tableName, fields);
    }

    public void setRelatedTable(Map<String, String> related){
        this.relatedTable = related;
        this.setBdContents();
    }

    @Override
    protected void setBdContents(){
        Map<Integer, Map<String, String>> contents = JavaConnectorDB.fetchDataFromDB(this.name, this.fields, relatedTable.keySet().stream().findFirst(), relatedTable.values().stream().findFirst());
        this.bdContents = contents;
    }
}
