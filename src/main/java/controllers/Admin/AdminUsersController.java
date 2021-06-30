package controllers.Admin;

import Database.MySQLConnection;
import Database.UsuarioDAO;
import Models.Usuario;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminUsersController implements Initializable {

    @FXML
    TextField tfUserEmail,tfUserPassword,tfName,tfLastName;

    @FXML
    ComboBox cmbGenero,cmbPriv,cmbEdoSal;

    @FXML
    CheckBox chkEdoCuen;

    @FXML
    TableView<Usuario> tblUsers;

    @FXML
    Button btnSaveUser,btnCancel;

    @FXML
    DatePicker dpNaci;


    UsuarioDAO userDAO= new UsuarioDAO(MySQLConnection.getConnection());


    ContextMenu contextMenuUser= new ContextMenu();

    Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initdata();
        initGUI();
        initcombobox();
        initbuttons();
        initMenus();
    }

    private void initMenus()
    {
        MenuItem menuItemUserEnable = new MenuItem("Enable");
        MenuItem menuItemUserDisable = new MenuItem("Disable");
        MenuItem menuItemUserDelete = new MenuItem("Delete");

        FontAwesomeIconView iconTrash = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
        iconTrash.setFill(Color.RED);
        menuItemUserDelete.setGraphic(iconTrash);

        FontAwesomeIconView iconList = new FontAwesomeIconView(FontAwesomeIcon.LIST);
        iconList.setFill(Color.GREEN);
        menuItemUserEnable.setGraphic(iconList);

        FontAwesomeIconView iconList2 = new FontAwesomeIconView(FontAwesomeIcon.LIST);
        iconList2.setFill(Color.GREEN);
        menuItemUserDisable.setGraphic(iconList2);


        menuItemUserEnable.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Enablecount(tblUsers.getSelectionModel().getSelectedItem().getEmail());
                update();
            }
        });

        menuItemUserDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteuser(tblUsers.getSelectionModel().getSelectedItem().getEmail());
                update();
            }
        });

        menuItemUserDisable.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Disableacount(tblUsers.getSelectionModel().getSelectedItem().getEmail());
                update();
            }
        });

        contextMenuUser.getItems().addAll(menuItemUserEnable,menuItemUserDelete,menuItemUserDisable);

        tblUsers.setContextMenu(contextMenuUser);
    }
    private void initGUI()
    {
        TableColumn colEmail= new TableColumn("Email");
        TableColumn colPass= new TableColumn("Password");
        TableColumn colName= new TableColumn("Nombre");
        TableColumn colApellidos= new TableColumn("Apellidos");
        TableColumn colGenero= new TableColumn("Genero");
        TableColumn colFechaNa= new TableColumn("Fecha de nacimiento");
        TableColumn colEdo = new TableColumn("Estado de Cuenta");

        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPass.setCellValueFactory(new PropertyValueFactory<>("password"));
        colName.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colFechaNa.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colEdo.setCellValueFactory(new PropertyValueFactory<>("edocuenta"));

        tblUsers.getColumns().addAll(colName,colApellidos,colEmail,colPass,colGenero,colFechaNa,colEdo);
    }

    private void initdata(){
        tblUsers.getItems().addAll(userDAO.getAll());
    }

    private EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if(event.getSource()==btnCancel){
                final Node source = (Node) event.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
            else if(event.getSource()==btnSaveUser){
                Addusser();
                update();
            }
        }
    };

    private void initbuttons(){
        btnCancel.setOnAction(handler);
        btnSaveUser.setOnAction(handler);
    }

    private void Addusser(){
        Usuario usuario = new Usuario();
        int aux;
        int aux2=0;
        int aux3=0;
        usuario.setNombre(tfName.getText());
        usuario.setApellidos(tfLastName.getText());
        usuario.setEmail(tfUserEmail.getText());
        usuario.setPassword(tfUserPassword.getText());
        usuario.setGenero(cmbGenero.getSelectionModel().getSelectedItem().toString().charAt(0));
        usuario.setEdad(dpNaci.getValue());
        if(chkEdoCuen.isSelected()){
             aux=0;
        }else{ aux=1;}
        usuario.setEdocuenta(aux);
        if(cmbPriv.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("normal")){
            aux2=0;
        }else if(cmbPriv.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("monitor")){
            aux2=1;
        }else if(cmbPriv.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("administrador")){
            aux2=2;
        }
        usuario.setEdosalud(aux2);
        if(cmbEdoSal.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("normal")){
            aux3=0;
        }else if(cmbEdoSal.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("sospechoso")){
            aux3=1;
        }else if(cmbEdoSal.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("positivo")){
            aux3=2;
        }
        usuario.setPriv(aux3);
        if(userDAO.insert(usuario)){
            showMessage("Usuario creado","Usuario creado con exito");
            update();
            clearinfo();
        }
    }

    private void deleteuser(String correo){
        if(tblUsers.getSelectionModel().getSelectedIndex()>0){
            if(userDAO.delete(correo)){
                showMessage("Delete user","Usuario eliminado");
                update();
            }
        }
    }

    private void initcombobox(){
        cmbGenero.getItems().addAll("F","M");
        cmbEdoSal.getItems().addAll("Normal","Sospechoso","Positivo");
        cmbPriv.getItems().addAll("normal","monitor","administrador");
    }

    private  void  showMessage(String tittle, String message)
    {
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(tittle);
        alert.setContentText(message);
        alert.show();
    }

    private void clearinfo(){
        tfUserEmail.setText("");
        tfName.setText("");
        cmbGenero.getSelectionModel().clearSelection();
        tfLastName.setText("");
        tfUserPassword.setText("");
        dpNaci.getEditor().clear();
        cmbPriv.getSelectionModel().clearSelection();
        cmbEdoSal.getSelectionModel().clearSelection();
        chkEdoCuen.setSelected(false);
    }

    private void update(){
        tblUsers.getItems().clear();
        tblUsers.setItems(userDAO.getAll());
    }

    private void Enablecount(String Correo){
        if(tblUsers.getSelectionModel().getSelectedIndex()>0){
            if(userDAO.updateenable(Correo)){
                showMessage("Update acount","Edo de Cuenta actualizado");
                update();
            }
        }
    }

    private void Disableacount(String Correo){
        if(tblUsers.getSelectionModel().getSelectedIndex()>0){
            if(userDAO.updateDisable(Correo)){
                showMessage("Update acount","Edo de Cuenta actualizado");
                update();
            }
        }
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

}
