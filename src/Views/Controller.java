package Views;


import Controllers.GestorRegTurnoRecTec;
import Models.RecursoTecnologico;
import Models.TipoRecursoTecnologico;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.net.URL;
import java.util.*;

//el controlador debe inicializar de una fomra cuando arranque la pantalla entonces hacemos que implemente el metodo inizializable

public class Controller implements Initializable {
    //atributos de los panelel
    @FXML private AnchorPane reservaPane;
    @FXML private AnchorPane confirmarPane;

    @FXML private ComboBox<TipoRecursoTecnologico> comboBoxTipoRecurso;
    @FXML private TableView<TablaRecursosTec> tablaRecursos;
    @FXML private DatePicker calendarioTurnos;
    @FXML private ListView tunosLista;
    @FXML private TableColumn colId;
    @FXML private TableColumn colMarca;
    @FXML private TableColumn colModelo;
    @FXML private TableColumn colEstado;
    @FXML private TableColumn colSeleccion;
    GestorRegTurnoRecTec gestorRegTurnoRecTec;
    // aqui describimos que pasa cuando inicializa el controlador
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gestorRegTurnoRecTec = new GestorRegTurnoRecTec(this);
        gestorRegTurnoRecTec.opcionReservarTurnoDeRecursoTecnologico();
        pedirSeleccionTipoRecurso(gestorRegTurnoRecTec.getTipo());

        /*
        List<TipoRecursoTecnologico> tiposRecTecnologicos = new ArrayList<>();
        TipoRecursoTecnologico_DAO tipoRecTecDAO = new TipoRecursoTecnologico_DAO();
        try {
            tiposRecTecnologicos = tipoRecTecDAO.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // cargo el combobox
        comboBoxTipoRecurso.getItems().addAll(tiposRecTecnologicos);
        */

    }
    public void onReservaButtonClicked(MouseEvent event){
        reservaPane.setVisible(true);
    }
    public void onCancelButtonClicked(MouseEvent event){
        reservaPane.setVisible(false);
    }
    public void onConfirmarButtonClicked(MouseEvent event){
        reservaPane.setDisable(true);
        confirmarPane.setVisible(true);
    }public void onCancelarConfirmacionButtonClicked(MouseEvent event){
        reservaPane.setDisable(false);
        confirmarPane.setVisible(false);
    }
    //deberia ser el tomar selecion del combo
    @FXML
    public void comboBoxEvents() throws Exception {
        String
            tipo = comboBoxTipoRecurso.getSelectionModel().getSelectedItem().toString();

       tomarSeleccionTipoRecurso(tipo);
    }
// relleno el combo para que seleccione el tipo
   public void pedirSeleccionTipoRecurso(List<TipoRecursoTecnologico> tiposRecTecno){
        comboBoxTipoRecurso.getItems().addAll(tiposRecTecno);
    }
    // una vez que se selecciona el tipo de recurso
public void tomarSeleccionTipoRecurso(String tipoSeleccionado) throws Exception {
        List<TablaRecursosTec> tablaRecTecs = gestorRegTurnoRecTec.tomarSeleccionTipoRecurso(comboBoxTipoRecurso.getSelectionModel().getSelectedItem().toString());
        mostrarRecursos(tablaRecTecs);
    }

    public void mostrarRecursos(List tablaRecursos){


        ObservableList<TablaRecursosTec> rectecno = FXCollections.observableArrayList(tablaRecursos);
       this.tablaRecursos.setItems(rectecno);
        this.colId.setCellValueFactory(new PropertyValueFactory("idRec"));
        this.colMarca.setCellValueFactory(new PropertyValueFactory("marca"));
        this.colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        this.colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.colSeleccion.setCellValueFactory((new PropertyValueFactory<>("button")));

    }
//tablaRecursos.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) ->mostrarAlertError() ));

    public void selFila(MouseEvent event){
       TablaRecursosTec trecSelec = tablaRecursos.getSelectionModel().getSelectedItem();
       gestorRegTurnoRecTec.recursoTecnologicoSeeccionado(trecSelec);
        if(trecSelec != null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle(trecSelec.getMarca());
            alert.setContentText(trecSelec.getModelo());
            alert.showAndWait();
        }else
            mostrarAlertError();
    }

    @FXML
    private void mostrarAlertError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("Error en la aplicacion");
        alert.showAndWait();
    }



}
