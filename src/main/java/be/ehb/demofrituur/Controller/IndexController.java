package be.ehb.demofrituur.Controller;

import be.ehb.demofrituur.model.Snack;
import be.ehb.demofrituur.model.Snackdao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@Controller
public class IndexController {

    @Autowired
    Snackdao dao;

    @ModelAttribute(value = "alleSnacks")
        public Iterable<Snack>getAllSnacks(){
        return dao.findAll();
    }
    @ModelAttribute(value = "nieuweSnack")
            public Snack snackToSave(){
                return new Snack();
            }

    @RequestMapping(value = {"","/","/index"},method = RequestMethod.GET)
    public String showIndex(ModelMap map) {
        return "index";
    }

    //code om een snack toe toe voegen
     @RequestMapping(value = {"","/","/index"},method = RequestMethod.POST)
             public String saveSnack( @ModelAttribute(name="nieuweSnack") @Valid Snack nieuweSnack, BindingResult bindingResult){
                if(bindingResult.hasErrors())
                    return "index";
                dao.save(nieuweSnack);
                return "redirect:/index";
     }
     // hiermee gaan we een snack verwijderen
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String deleteSnack(@PathVariable(value = "id") int id){
        dao.deleteById(id);
        return "redirect:/index";

    }
    }


