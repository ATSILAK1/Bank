package presentation.vue.palette;

import metier.form.LoginFormValidator;
import org.w3c.dom.ls.LSOutput;
import presentation.modele.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class LoginPanel extends JPanel {

    private int width , height ;

    private JButton btn_login , btn_forgot ;
    private JLabel label_login , label_password ;
    private JPasswordField fld_password ;
    private JTextField fld_login ;
    private LoginFormValidator loginFormValidator = new LoginFormValidator();

    public JButton getBtn_login() {
        return btn_login;
    }

    public void setBtn_login(JButton btn_login) {
        this.btn_login = btn_login;
    }

    public JButton getBtn_forgot() {
        return btn_forgot;
    }

    public void setBtn_forgot(JButton btn_forgot) {
        this.btn_forgot = btn_forgot;
    }

    public JLabel getLabel_login() {
        return label_login;
    }

    public void setLabel_login(JLabel label_login) {
        this.label_login = label_login;
    }

    public JLabel getLabel_password() {
        return label_password;
    }

    public void setLabel_password(JLabel label_password) {
        this.label_password = label_password;
    }

    public JPasswordField getFld_password() {
        return fld_password;
    }

    public void setFld_password(JPasswordField fld_password) {
        this.fld_password = fld_password;
    }

    public JTextField getFld_login() {
        return fld_login;
    }

    public void setFld_login(JTextField fld_login) {
        this.fld_login = fld_login;
    }

    public LoginFormValidator getLoginFormValidator() {
        return loginFormValidator;
    }

    public void setLoginFormValidator(LoginFormValidator loginFormValidator) {
        this.loginFormValidator = loginFormValidator;
    }

    private void initButtons()
    {
        btn_login  = new  JButton("login");
        btn_forgot = new JButton("Forgot password");
        label_login = new JLabel("Login");
        label_password = new JLabel("Forgot password");
        fld_password = new JPasswordField("Password");
        fld_login = new JTextField("Login",10);

        label_login.setBounds(width/2-75,height/2-120,150,20);
        fld_login.setBounds(width/2-75,height/2-90,150,20);
        label_password.setBounds(width/2-75,height/2-60,150,20);
        fld_password.setBounds(width/2-75,height/2-30,150,20);
        btn_login.setBounds(width/2-75,height/2,150,20);
    }

    private void initPanel()
    {
        initButtons();
        add(label_login);
        add(fld_login);
        add(label_password);
        add(fld_password);
        add(btn_login);

    }

    public LoginPanel(int width , int height)
    {
    this.width = width ;
    this.height = height;
    initPanel();
    }

}
