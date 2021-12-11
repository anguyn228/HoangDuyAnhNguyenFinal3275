package sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SessionAttributes({"id","desc","errMsg"})
@RequestMapping
@Controller
public class CustomerController{

    //    Step 16. Lab 6. update controller to link it with real database
    DatabaseService service1;

    @Autowired
    CustomerService service;

    @Autowired
    Connection123 connect;


    //a mapping when someone enters file
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String showCategorypage(ModelMap model,@RequestParam(defaultValue = "") String id) throws ClassNotFoundException, SQLException {

        service1 = new DatabaseService(connect.connect());


        model.addAttribute("todos", service1.display());


        List<Customer> filteredTodos = new ArrayList<Customer>();


        filteredTodos = (List) model.get("todos");

        model.put("id",filteredTodos.get(0).getCustno());

        model.put("desc",filteredTodos.get(0).getCustname());


        return "customer";

    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showCategoryPage2(ModelMap model) throws ClassNotFoundException, SQLException{

        service1 = new DatabaseService(connect.connect());

        model.addAttribute("todos", service1.display());


        List<Customer> filteredTodos = new ArrayList<Customer>();

        filteredTodos = (List) model.get("todos");

        model.put("id",filteredTodos.get(0).getCustno());


        model.put("desc",filteredTodos.get(0).getCustname());

        return "customer";


    }

    @RequestMapping(value ="/add-todo", method = RequestMethod.GET)
    public String showtodopage(){
        return "catser";
    }


    @RequestMapping(value ="/add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @RequestParam String custno, @RequestParam String custname, @RequestParam String cdep, @RequestParam String nyears,
                          @RequestParam String savtype) throws SQLException, ClassNotFoundException{

        if(!((service1.search(custno))==null)){
            model.put("errorMessage", "Record Existing");
            return "redirect:/customer";
        }

        Customer cc = new Customer(custno, custname, cdep, nyears, savtype);

        service1.add(cc);

        model.clear();
        return "redirect:/customer";
    }

    //
    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(ModelMap model,  @RequestParam(defaultValue = "") String id) throws SQLException, ClassNotFoundException{

        model.put("id", id);

        Customer cc = service1.search(id);

        model.put("id",cc.getCustno());
        model.put("desc", cc.getCustname());

        return "cusedit";
    }



    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String showUpdate(ModelMap model,  @RequestParam String custno, @RequestParam String custname, @RequestParam String cdep, @RequestParam String nyears, @RequestParam String savtype) throws SQLException, ClassNotFoundException {

//        get the old catcode

        String iid = (String) model.get("id");

        Customer cc = new Customer(custno, custname, nyears, cdep, savtype);

        service1.edit(cc,iid);


        return "redirect:/";
    }


    //Step 21. Lab 6. Map your delete method
    @RequestMapping(value ="/delete-todo", method = RequestMethod.GET)
    public String deleteTodo(ModelMap model, @RequestParam String id) throws SQLException, ClassNotFoundException {

        service1.delete(id);

        model.clear();
        return "redirect:/";
    }





    //Step 22. Map the method that will call your items.jsp
    @RequestMapping(value = "/see-todo", method = RequestMethod.GET)
    public String seetodo(ModelMap model, @RequestParam(defaultValue = "") String id) throws SQLException, ClassNotFoundException{
        model.put("id", id);

        service1 = new DatabaseService(connect.connect());

        String iid = (String) model.get("id");

        List<Customer> Itemlist = new ArrayList<>();

        Itemlist = service1.display2(iid);

        if(Itemlist.size()==0){
            model.put("errorMessage", "There are not items for this category");
            return "redirect:/";
        } else {
            model.put("errorMessage","");
        }

        model.put("desc",iid);

        System.out.println(iid);
        model.addAttribute("todos1", service1.display2(iid));

        return "items";
    }

    //another calling Items.jsp to Post method
    @RequestMapping(value ="/see-todo", method = RequestMethod.POST)
    public String seetodo2(ModelMap model) throws SQLException, ClassNotFoundException {


        return "redirect:/";
    }


}



