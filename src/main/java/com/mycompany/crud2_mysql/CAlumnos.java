/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud2_mysql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Personal
 */
public class CAlumnos {
    int codigo;
    String nombreAlumnos;
    String apellidoAlumnos;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreAlumnos() {
        return nombreAlumnos;
    }

    public void setNombreAlumnos(String nombreAlumnos) {
        this.nombreAlumnos = nombreAlumnos;
    }

    public String getApellidoAlumnos() {
        return apellidoAlumnos;
    }

    public void setApellidoAlumnos(String apellidoAlumnos) {
        this.apellidoAlumnos = apellidoAlumnos;
    }
    
    public void InsertarAlumnos (JTextField paramNombres, JTextField paramApellidos) {
        setNombreAlumnos(paramNombres.getText());
        setApellidoAlumnos(paramApellidos.getText());
        
        CConexion objetoCConexion = new CConexion();
        String consulta = "insert into Alumnos (nombres , apellidos) values (?,?);";
        
        try {
            CallableStatement cs = objetoCConexion.establecerConexion().prepareCall(consulta);
            cs.setString(1, getNombreAlumnos());
            cs.setString(2, getApellidoAlumnos());
            
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se inserto correctamente el alumno");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se inserto correctamente el alumno, error:" + e.toString());
        }
    }
    
    public void MostrarAlumno(JTable paramTotalAlumnos) {
    CConexion objectConexion = new CConexion();
    
        DefaultTableModel modelo = new DefaultTableModel();
    
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        paramTotalAlumnos.setRowSorter(OrdenarTabla);
    
    String sql = "";
    
    modelo.addColumn("ID");
    modelo.addColumn("Nombres");
    modelo.addColumn("Apellidos");
    
    sql = "select* from Alumnos";
    
    String[] datos = new String[3];
        Statement st;
    
    try {
        st = objectConexion.establecerConexion().createStatement();
        ResultSet rs = st.executeQuery(sql);
        
        while (rs.next()) {
            datos[0] = rs.getString(1);
            datos[1] = rs.getString(2);
            datos[2] = rs.getString(3);
            
            modelo.addRow(datos);
        }
        
        paramTotalAlumnos.setModel(modelo);
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros error: "+e.toString());
    }
}
    /**seleccionar alumnos*/
    public void SeleccionarAlumno(JTable paramTableAlumnos, JTextField paramId, JTextField paramNombres, JTextField paramApellidos) {
    try {
        int fila = paramTableAlumnos.getSelectedRow();
        
        if (fila>0) {
            
            paramId.setText((String) (paramTableAlumnos.getValueAt(fila, 0)));
            paramNombres.setText((String) (paramTableAlumnos.getValueAt(fila, 1)));
            paramApellidos.setText((String) (paramTableAlumnos.getValueAt(fila, 2)));
        }
        else{
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error : " + e.toString());
    }
} 
    public void ModificarAlumnos (JTextField paramCodigo, JTextField paramNombres, JTextField paramApellidos) {
    setCodigo(Integer.parseInt(paramCodigo.getText()));
    setNombreAlumnos(paramNombres.getText());
    setApellidoAlumnos(paramApellidos.getText());
    
    CConexion objectConexion = new CConexion(); //Preparamos la conexion
    
    String consulta = "UPDATE Alumnos SET alumnos.nombres = ?, alumnos.apellidos = ? WHERE alumnos.id=?";
    
    try {
        CallableStatement cs = objectConexion.establecerConexion().prepareCall(consulta);
        
        cs.setString(1, getNombreAlumnos());
        cs.setString(2, getApellidoAlumnos());
        cs.setInt(3, getCodigo());
        
        cs.execute();
        
        JOptionPane.showMessageDialog(null, "Modificación Exitosa");
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al modificar Alumno error : " + e.toString());
    }
}
    public void EliminarAlumnos (JTextField paramCodigo) {
        
    setCodigo(Integer.parseInt(paramCodigo.getText()));
    
    CConexion objectConexion = new CConexion();
    
    String consulta = "DELETE FROM Alumnos WHERE alumnos.id=?";
    
    try {
        CallableStatement cs = objectConexion.establecerConexion().prepareCall(consulta);
        cs.setInt(1, getCodigo());
        cs.execute();
        
        JOptionPane.showMessageDialog(null, "Se elimino correctamente el Alumno");
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "No se pudo eliminar, error : " + e.toString());
    }
}
    
}
