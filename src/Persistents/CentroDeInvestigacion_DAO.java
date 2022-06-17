package Persistents;

import Models.CentroDeInvestigacion;
import Models.Marca;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CentroDeInvestigacion_DAO extends Conexion{

    public CentroDeInvestigacion obtenerDatos(int idRecurso) throws SQLException {
        CentroDeInvestigacion ci = null;
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT ci.* FROM recursos_tecnologicos rt join centros_de_investigaciones ci on rt.idCentrosInvestigaciones=ci.idcentros_de_investigaciones where idrecursos_tecnologicos= ?");
       //     PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM centros_de_investigaciones ci join asignaciones_cientificos_del_ci asi on ci.idcentros_de_investigaciones = asi.idCentrosDeInvestigaciones where ci.idrecursos_tecnologicos= ?");



            st.setInt(1,idRecurso);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                ci = new CentroDeInvestigacion();
                ci.setNombre(rs.getString("nombre"));
                ci.setSigla(rs.getString("sigla"));

                // hay que completar con todos los datos
            }
            rs.close();
            st.close();

        } catch (Exception e) {
            throw e;
        } finally {
            this.deconectar();
        }
        return ci;
    }
}
