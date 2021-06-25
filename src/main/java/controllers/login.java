package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class login implements Initializable {

    @FXML
    ImageView logo;
    
    @FXML
    JFXButton btnLogin,btnRegister;
        

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image logo1 = new Image("/Images/Logo/itc.png");
        logo.setImage(logo1);
         btnLogin.setOnAction(eventHandler);
         btnRegister.setOnAction(eventHandler);
    }
    EventHandler<ActionEvent> eventHandler=new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(event.getSource()==btnLogin)
                {

                }else{
                    try {
                        initUser();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
    };

     /*

        private User searchUser()
        {
            User user=null;//CONSULTA PARA BUSCAR Y COMPROBAR USUARIO Y CONTRASEÑA

            return user;

        }
        private void openUserForm()
        {
            if(searchUser().getPrivilegies()==1)
            {
                //SE ABRE EL FORMULARIO DEL ADMIN Y ASI PARA C/U USUARIO
            }

        }
    */

    public void initGUI(){

    }

    public void initData(){
    }

    private void initUser() throws IOException {
            Stage login = new Stage();
            login.setTitle("Creating User");
            Parent root = FXMLLoader.load(getClass().getResource("/Accesos/General/register_form.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/css/DarkTheme2.css");
            //scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            login.setScene(scene);
            login.setResizable(false);
            login.show();
    }
}
