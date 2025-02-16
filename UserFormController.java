import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.table.DefaultTableModel;

public class UserFormController implements DBCallback{
    private DBConnect db;
    private ResultSet rs;
    private UserFormCallback callback;
    public UserFormController(){
        try{
            db=new DBConnect();

        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void submitUserData(UserValues user, UserFormCallback callback){
        try {
            this.callback = callback;
            db.createUser(user,this);
        } catch (Exception e) {
            callback.onFailure(String.valueOf(e));
        }
    }
    @Override
    public void dbSucess() {
       callback.onSucess();
    }
    @Override
    public void dbError(String error) {
        callback.onFailure(error);
    }
    public DefaultTableModel retrieveUsers(){
        DefaultTableModel model= new DefaultTableModel();
        try {
            model.addColumn("ID");
            model.addColumn("Name");
            model.addColumn("Email");
            model.addColumn("Height");
            ResultSet rs= db.retrieveUsers();
        while(rs.next()){
            model.addRow(new Object[]{
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getFloat(4)
            });
        }

        } catch (Exception e) {
           System.out.println(e);
        }
        return model;
    }
    public DefaultTableModel retrieveUserMetaData(){
        DefaultTableModel model =new DefaultTableModel();
        try{
            ResultSet rs= db.retrieveMetaData();
            ResultSetMetaData md= rs.getMetaData();
            for(int i=1; i<=md.getColumnCount(); i++){
                model.addColumn(md.getColumnName(i));
            }
            while(rs.next()){
                model.addRow(new Object[]{
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getFloat(4)
                    
                });
        }
    }catch(Exception e){
        System.out.println(e);
    }
    return model;
    }
    public void updateTableDAtas(UserValues user){
        try {
            rs.moveToInsertRow();
            rs.updateInt(1, 5);
            rs.updateString(2, user.getName());
            rs.updateString(3, user.getEmail());
            rs.updateFloat(4, user.getHeight());
            rs.insertRow();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void deleteRow(){
        try {
            rs.last();
            rs.deleteRow();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}