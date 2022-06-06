package Persistents;

import Models.Marca;
import Models.Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Marca_DAO extends Conexion {
     private int idMarca;



     public int getIdMarca() {
          return idMarca;
     }

     public void setIdMarca(int idMarca) {
          this.idMarca = idMarca;
     }

     public void registrar(Marca marca) throws Exception {
          try {
               this.conectar();
               PreparedStatement st = this.conexion.prepareStatement("INSERT INTO Marcas(nombre) VALUES(?)");
               st.setString(1, marca.getNombre());
               st.executeUpdate();

          } catch (Exception e) {
               throw e;
          } finally {
               this.deconectar();
          }
     }

     public void modificar(Marca marca) throws Exception {
          try {
               this.conectar();
               PreparedStatement st = this.conexion.prepareStatement("UPDATE Marcas set nombre where id=?");
               st.setString(1, marca.getNombre());
               st.executeUpdate();

          } catch (Exception e) {
               throw e;
          } finally {
               this.deconectar();
          }
     }

     public void eliminar(Marca marca) throws Exception {
          try {
               this.conectar();
               PreparedStatement st = this.conexion.prepareStatement("DELETE form Marcas where id=?");
               st.setString(1, marca.getNombre());
               st.executeUpdate();

          } catch (Exception e) {
               throw e;
          } finally {
               this.deconectar();
          }
     }

     public List<Marca> listar() throws Exception {
          List<Marca> lista = null;
          try {
               this.conectar();
               PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM marcas");
               lista = new ArrayList<>();
               ResultSet rs = st.executeQuery();
               while (rs.next()){
                    Marca marca = new Marca();
                    marca.setNombre(rs.getNString("nombre"));
                    lista.add(marca);
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


     public Marca obtenerDatos(String nombreMarca) throws SQLException {
          Marca marca
                  = null;
          try {
               this.conectar();
               PreparedStatement st = this.conexion.prepareStatement("SELECT ma.nombre FROM marcas ma join modelos mo on ma.idmarcas = mo.idMarcas where mo.nombre= ?");
               st.setString(1,nombreMarca);
               ResultSet rs = st.executeQuery();
               while (rs.next()){
                    marca = new Marca();
                    marca.setNombre(rs.getString("nombre"));
               }
               rs.close();
               st.close();

          } catch (Exception e) {
               throw e;
          } finally {
               this.deconectar();
          }
          return marca;
     }
}
