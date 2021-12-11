package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService implements DatabaseInterface{

    Connection con;

    public DatabaseService(Connection con){
        this.con = con;
    }





    @Override
    public void add(Customer cus) throws ClassNotFoundException, SQLException {
        String quer1 = "INSERT INTO category VALUES ( ?, ? )";
        PreparedStatement query = con.prepareStatement(quer1);

        query.setString(1, cus.getCustno());
        query.setString(2, cus.getCustname());

        query.executeUpdate();
    }

    @Override
    public Customer edit(Customer cus, String custno) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void delete(String custno) throws SQLException {
        String quer1 = "Delete from Category where custno = ?";
        PreparedStatement query = con.prepareStatement(quer1);
        query.setString(1, custno);
        query.executeUpdate();

    }


    //    The search method
    public Customer search(String custno) throws SQLException, ClassNotFoundException{
        String quer1 = "Select * from custno where custname = ?";
        PreparedStatement query = con.prepareStatement(quer1);
        query.setString(1,custno);

        ResultSet rs= query.executeQuery();

        if(!rs.first()){
            System.out.print("Record not existing");
            return null;
        }

       Customer obj1=null;

        obj1 = new Customer(rs.getString("custno"), rs.getString("custname"), rs.getString("cdep"),
                rs.getString("nyears"), rs.getString("savtype") );


        return obj1;
    }

    @Override
    public List<Customer> display() throws ClassNotFoundException, SQLException {
        //create an array list that will contain the data recovered
        List<Customer> Catlist = new ArrayList<Customer>();

        String quer1 = "Select * from savingstable";
        PreparedStatement query = con.prepareStatement(quer1);
        ResultSet rs = query.executeQuery();

        Customer obj1;

        //display records if there is data

        while (rs.next()) {
            obj1 = new Customer(rs.getString("custno"), rs.getString("custname"), rs.getString("cdep"),
                    rs.getString("nyears"), rs.getString("savtype"));

            Catlist.add(obj1);
        }

        return Catlist;
    }



    //    Create another display method named display2 for the items table
    public List<Customer> display2(String custno) throws ClassNotFoundException, SQLException {
        //create an array list that will contain the data recovered
        List<Customer> Itemlist = new ArrayList<Customer>();

        String quer1 = "Select custno,custname, cdep, savtype, nyears from items where custno=?";
        PreparedStatement query = con.prepareStatement(quer1);
        query.setString(1, custno);
        ResultSet rs = query.executeQuery();

        Customer obj2;

        //display records if there is data;

        while (rs.next()) {

            obj2 = new Customer(rs.getString("custno"), rs.getString("custname"), rs.getString("cdep"),
                    rs.getString("nyears"), rs.getString("savtype"));

            Itemlist.add(obj2);
        }

        return Itemlist;
    }
}
