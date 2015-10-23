package br.com.hudson;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hudson.sales
 * @verison 1.0.0
 */
public class Bs {

    private List<Contato> blank;

    public Bs() {
        blank = new ArrayList<>();
        Contato c = new Contato();
        c.setId("");
        c.setNome("");
        c.setTel("");
        c.setEmail("");
        blank.add(c);
    }

    public List<Contato> getContatos(String param) {
        if (param != null && !"".equals(param)) {
            return new Dao().getContatos(param);
        }
        return blank;
    }
     public List<Contato> getContatos(String param, int page) {

        if (param != null && !"".equals(param)) {
            return new Dao().getContatos(param,page);
        }
        return blank;
    }
     
    public int getNumber(String param){
        return new Dao().getNumberOfResults(param);
    }
     

    public List<String> autoComplete(String param) {
        return new Dao().getAutoComplete(param);
    }
}
