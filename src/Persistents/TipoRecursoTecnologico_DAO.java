package Persistents;

import Models.Marca;
import Models.TipoRecursoTecnologico;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoRecursoTecnologico_DAO extends Conexion {

    public TipoRecursoTecnologico obtenerTipo(int id ) throws SQLException {
        TipoRecursoTecnologico tipoRecursoTecnologico = null;
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM tipos_recursos_tecnologicos t where  t.idtipos_recursos_tecnologicos = ?");
            st.setInt(1,id);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                tipoRecursoTecnologico = new TipoRecursoTecnologico();
                tipoRecursoTecnologico.setNombre(rs.getNString("nombre"));
                tipoRecursoTecnologico.setDescripcion(rs.getNString("descripcion"));
            }
            rs.close();
            st.close();

        } catch (Exception e) {
            throw e;
        } finally {
            this.deconectar();
        }
        return tipoRecursoTecnologico;
    }

    public List<TipoRecursoTecnologico> listar() throws Exception{
        List<TipoRecursoTecnologico> lista = null;
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM tipos_recursos_tecnologicos");
            lista = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                TipoRecursoTecnologico tipoRecursoTecnologico = new TipoRecursoTecnologico();
                tipoRecursoTecnologico.setNombre(rs.getNString("nombre"));
                tipoRecursoTecnologico.setDescripcion(rs.getNString("descripcion"));
                lista.add(tipoRecursoTecnologico);
            }
            rs.close();
            st.close();

        } catch (Exception e) {
            throw e;
        } finally {
            this.deconectar();
        }
        return lista;
    }

}
