package Views;


import Controllers.GestorRegTurnoRecTec;
import Models.RecursoTecnologico;
import Models.TipoRecursoTecnologico;
import com.mysql.cj.result.LocalDateTimeValueFactory;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Callback;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
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


    public void pedirSeleccionTurno(List<HashMap> turnosRecTecnologicoSeleccionado) {
        Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell(){
            @Override
            public void updateItem(LocalDate item, boolean empty) {

                super.updateItem(item, empty);

                this.setDisable(false);
                this.setBackground(null);
                this.setTextFill(Color.BLACK);

                // deshabilitar las fechas anteriores a la actual
                if (item.isBefore(LocalDate.now())) {
                    this.setDisable(true);
                }
/*
                for (int i = 0; i < turnosRecTecnologicoSeleccionado.size(); i++) {
                    // pintar de verde los dias con turnos disponibles.
                    if(item.equals((Date)turnosRecTecnologicoSeleccionado.get(i).get("horaFechaInicio"))){
                        Paint color = Color.GREEN;
                        BackgroundFill fill = new BackgroundFill(color,null,null);
                        this.setBackground(new Background(fill));
                        this.setTextFill(Color.WHITESMOKE);
                    }


                }
*/

                // marcar los dias de quincena
                int day = item.getDayOfMonth();
                if (day == 15 || day == 30) {

                    Paint color = Color.RED;
                    BackgroundFill fill = new BackgroundFill(color, null, null);

                    this.setBackground(new Background(fill));
                    this.setTextFill(Color.WHITESMOKE);
                }
                //marcar dias disponibles en azul


                // fines de semana en color negro
                DayOfWeek dayweek = item.getDayOfWeek();
                if (dayweek == DayOfWeek.SATURDAY || dayweek == DayOfWeek.SUNDAY) {
                    this.setTextFill(Color.BLACK);
                }
            }
        };
        this.calendarioTurnos.setDayCellFactory(dayCellFactory);
        this.tunosLista.getItems().addAll(turnosRecTecnologicoSeleccionado);
    }
}
