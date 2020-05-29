/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrapper;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Marvin Moran
 */
public class ListadoLoginModel implements Serializable{
    private ArrayList<LoginModel> Login;

    public ArrayList<LoginModel> getLogin() {
        return Login;
    }

    public void setLogin(ArrayList<LoginModel> Login) {
        this.Login = Login;
    }

    
    
    
    
}
