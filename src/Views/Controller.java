package Views;


import Controllers.GestorRegTurnoRecTec;
import Models.TipoRecursoTecnologico;
import Models.Turno;
import com.sun.xml.internal.ws.api.server.Adapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.net.URL;
import java.security.PrivateKey;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

//el controlador debe inicializar de una fomra cuando arranque la pantalla entonces hacemos que implemente el metodo inizializable

public class Controller implements Initializable {

    //atributos de la pantallas
    @FXML private AnchorPane reservaPane;
    @FXML private AnchorPane confirmarPane;
    @FXML private ComboBox<String> comboBoxTipoRecurso;
    @FXML private TableView<TablaRecursosTec> tablaRecursos;
    @FXML private DatePicker calendarioTurnos;
    @FXML private ListView tunosLista;
    @FXML private TableColumn colId;
    @FXML private TableColumn colMarca;
    @FXML private TableColumn colModelo;
    @FXML private TableColumn colEstado;
    @FXML private TableColumn colInstitucion;
    @FXML private TextArea mostrarDatosDeReserva;
    @FXML private Button cancelarVtaConfirmacion;
    @FXML private Button confiramrConfiramacion;
    @FXML private Button cerrarConfirmacion;
    GestorRegTurnoRecTec gestorRegTurnoRecTec;
    //Aqui se habilitaria la pantalla.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gestorRegTurnoRecTec = new GestorRegTurnoRecTec(this);
        gestorRegTurnoRecTec.nuevaReservaTurnoDeRecursoTecnologico();
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
        mostrarDatosDeReserva();
    }

     public void onCancelarConfirmacionButtonClicked(MouseEvent event){
        reservaPane.setDisable(false);
        confirmarPane.setVisible(false);
    }

    // Relleno el combo para que seleccione el tipo
   public void pedirSeleccionTipoRecurso(List<String> tiposRecTecno){
        comboBoxTipoRecurso.getItems().addAll(tiposRecTecno);
    }

    //Tomamos la seleccion del comboBox
    @FXML
    public void tomarSeleccionTipoRecurso() throws Exception {
        String tipoRecSeleccionado = comboBoxTipoRecurso.getSelectionModel().getSelectedItem().toString();
        gestorRegTurnoRecTec.tomarSeleccionTipoRecurso(tipoRecSeleccionado);
    }

    public void pedirSeleccionRecursoTecnologico(List<TablaRecursosTec> tablaRecursos) {

        // establecer los colores para los diferentes tipos de estados.
        ObservableList<TablaRecursosTec> rectecno = FXCollections.observableArrayList(tablaRecursos);
        this.tablaRecursos.setItems(rectecno);
        this.colId.setCellValueFactory(new PropertyValueFactory("idRec"));
        this.colMarca.setCellValueFactory(new PropertyValueFactory("marca"));
        this.colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        this.colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.colInstitucion.setCellValueFactory((new PropertyValueFactory<>("centroInvestigacion")));

    }
//tablaRecursos.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) ->mostrarAlertError() ));
public String recursoSeleccionado;

    public void seleccionRecursoTecnologico(MouseEvent event){
       TablaRecursosTec trecSelec = tablaRecursos.getSelectionModel().getSelectedItem();
       recursoSeleccionado = "Recurso: " + trecSelec.getMarca() + " " + trecSelec.getModelo() +"\n"+ "Centro de Investigacvion: "+  trecSelec.getCentroInvestigacion();
        try {
            gestorRegTurnoRecTec.recursoTecnologicoSeeccionado(trecSelec);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        calendarioTurnos.setDisable(false);
        /*if(trecSelec != null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle(trecSelec.getMarca());
            alert.setContentText(trecSelec.getModelo());
            alert.showAndWait();
        }else
            mostrarAlertError();*/
    }

    @FXML
    private void mostrarAlertError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("Error en la aplicacion");
        alert.showAndWait();
    }

private List<List<String>> tuenos;
    public void pedirSeleccionTurno(List<List<String>> turnosRecTecnologicoSeleccionado) {
        this.tuenos = turnosRecTecnologicoSeleccionado;
        Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell(){
            @Override
            public void updateItem(LocalDate item, boolean empty) {

                super.updateItem(item, empty);

                this.setDisable(true);
                this.setBackground(null);
                this.setTextFill(Color.BLACK);

                // deshabilitar las fechas anteriores a la actual
                if (item.isBefore(LocalDate.now())) {
                    this.setDisable(true);
                }

                for (int i = 0; i < turnosRecTecnologicoSeleccionado.size(); i++) {
                    // pintar de verde los dias con turnos disponibles.
                        String sDate = turnosRecTecnologicoSeleccionado.get(i).get(0).substring(0,10);
                        if(item.toString().equals(sDate)){
                            Paint color = Color.BLUE;
                            BackgroundFill fill = new BackgroundFill(color,null,null);
                            this.setDisable(false);
                            this.setBackground(new Background(fill));
                            this.setTextFill(Color.WHITESMOKE);
                    }
                }


                // ejemplo marcar los dias en rojo para los que no haya turno
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
        //this.tunosLista.getItems().addAll(turnosRecTecnologicoSeleccionado);
    }

    public String fechaseleccionada;

    public void seccionFecha(){
        fechaseleccionada = calendarioTurnos.getValue().toString();
        this.tunosLista.setDisable(false);
        tunosLista.getItems().clear();
        for (int i = 0; i < tuenos.size(); i++) {
            if(tuenos.get(i).get(0).substring(0,10).equals(fechaseleccionada)){
                this.tunosLista.getItems().add(tuenos.get(i).get(0).substring(11,16) +" a "+ tuenos.get(i).get(1).substring(11,16) +  tuenos.get(i).get(2));

            }
        }

    }
    public String turnoSele;

    public void turnoSeleccionado(){
        String itemSeleccioandoTurno = this.tunosLista.getSelectionModel().getSelectedItem().toString();
        List<String> tunoSeleccionado = new ArrayList<>();
        //fechahora desde
        tunoSeleccionado.add(fechaseleccionada+"T"+itemSeleccioandoTurno.substring(0,5));
        //fechahora hasta
        tunoSeleccionado.add(fechaseleccionada+"T"+itemSeleccioandoTurno.substring(8,13));
        turnoSele = tunoSeleccionado.get(0) + tunoSeleccionado.get(1);

        gestorRegTurnoRecTec.turnoSeleccionado(tunoSeleccionado);

    }

    public void pedirConfirmacionReserva() {
    }
    public void sconfirmacionReserva() throws SQLException {
        gestorRegTurnoRecTec.reservarRecursoTecnologico();
    }

    public void avisarflujoA1() {
        mostrarAlertError();
    }
    private void mostrarDatosDeReserva() {
        String mensaje = this.turnoSele;
        String mensaje2 = this.recursoSeleccionado;
        this.mostrarDatosDeReserva.setText(mensaje2 +"\n"+ mensaje);
    }
    @FXML
    public void confirmarReserva(){
        this.mostrarDatosDeReserva.clear();
        mostrarDatosDeReserva.setText("CONFIRMADO. MAIL ENVIADO CON LOS DATOS");
        cancelarVtaConfirmacion.setVisible(false);
        confiramrConfiramacion.setVisible(false);
        cerrarConfirmacion.setVisible(true);


    }
    @FXML
    public void closeConfirmacion(){
        reservaPane.setVisible(false);
        confirmarPane.setVisible(false);

    }
}
