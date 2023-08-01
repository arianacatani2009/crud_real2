import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class miprimerselect {
    private JButton GUARDARButton;
    private JButton DELETEButton;
    private JButton IMPRIMIRButton;
    private JTextField textID;
    private JTextField textNombre;
    private JTextField textEdad;
    private JTextField textCiudad;
    private JTextField textCorreo;
    private JPanel Jpanel;
    private JTextField textborrar;
    static final String DB_URL = "jdbc:mysql://localhost/Poo_estu";
    static final String USER = "root";
    static final String PASS = "root_bas3";
    public miprimerselect() {
        IMPRIMIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*QUERY LINEA DE COMANDO QUE  QUIERO EJECUTAR DEL SQL*/
                String QUERY="SELECT * FROM ESTUDIANTES";
                try(Connection conn= DriverManager.getConnection(DB_URL,USER,PASS);
                        /*que es lo que quiero hacer con los datos*/
                    Statement statement = conn.createStatement();/*mientraseste activo va ejecutarse un resultado*/
                    ResultSet resultSet = statement.executeQuery(QUERY);)
                {
                    while(resultSet.next()){
                        System.out.println("ID:"+resultSet.getInt("id"));
                        System.out.println("Nombre:"+resultSet.getString("nombre"));
                        System.out.println("Edad:"+resultSet.getInt("Edad"));
                        System.out.println("Ciudad:"+resultSet.getString("Ciudad"));
                        System.out.println("Correo:"+resultSet.getString("correo"));
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        GUARDARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int contador;
                int cont = 0;
                cont++;
                contador=cont;
                String id,nombre,Edad,Ciudad,Correo;
                nombre=textNombre.getText();
                Edad= textEdad.getText();
                Ciudad=textCiudad.getText();
                Correo=textCorreo.getText();
                textID.getText();
                id= String.valueOf(Integer.parseInt(textID.getText()));
                System.out.println(id);
                System.out.println(nombre);
                System.out.println(Edad);
                System.out.println(Ciudad);
                System.out.println(Correo);
                String QUERY="INSERT INTO Estudiantes(id,nombre,Edad,Ciudad,Correo)"+"VALUES(?,?,?,?,?)";
                try(Connection conn=DriverManager.getConnection(DB_URL,USER,PASS))
                {
                    PreparedStatement statement =  conn.prepareStatement(QUERY);
                    //Establecer valores para los parametros de la sentenciaSQL
                    statement.setInt(1, Integer.parseInt(id));
                    statement.setString(2,nombre);
                    statement.setInt(3, Integer.parseInt(Edad));
                    statement.setString(4,Ciudad);
                    statement.setString(5,Correo);
                    //EJECUTAR sentencia SQL row inserted en qe columna se van a insertar los datos
                    int rowInserted = statement.executeUpdate();
                    if(rowInserted > 0){
                        System.out.println("Se genero correctamente la informacion");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int borrar;
                borrar = Integer.parseInt(textborrar.getText());
                String BORRAR_QUERY="DELETE FROM Estudiantes where id=?";
                try(Connection conn=DriverManager.getConnection(DB_URL,USER,PASS))
                {
                    PreparedStatement statement =  conn.prepareStatement(BORRAR_QUERY);
                    statement.setInt(1,borrar);
                    int rowsDeleted = statement.executeUpdate();
                    if(rowsDeleted > 0){
                        System.out.println("Se elimino la fila");
                    }else{
                        System.out.println("No se pudo eliminar la fila");
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public static void main(String[]args){
        JFrame frame = new JFrame("Miprimerselect");
        frame.setContentPane(new miprimerselect().Jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
