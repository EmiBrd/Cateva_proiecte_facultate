package dao;

import connection.ConnectionFactory;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AbstractDAO2<T> {

    private String nume_client_ordine;
    private String nume_produs_ordine;
    private int cantit;

    private static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName() );
    private Class<T> type = null;


    @SuppressWarnings("unchecked")
    /***
     * constructor 1
     */
    public AbstractDAO2() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


    /***
     * constructor 2
     * @param cls
     */
    public AbstractDAO2(Class<T> cls) {
        this.type = cls;
    }


    /***
     * metoda pentru crearea unui obiect
     * @param resultSet
     * @return
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new Vector<T>();
        try {
            while (resultSet.next()) {
                T instance = type.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }


    /***
     * metoda pentru gasirea numelui
     * @param field
     * @param name
     * @return
     */
    public T find_name(String field, Object name) {
        Connection connect = null;
        PreparedStatement stat = null;
        ResultSet result = null;
        StringBuilder sb = new StringBuilder();
        sb.append("select * from ");
        sb.append(type.getSimpleName());
        sb.append(" where " + field + " = ?");
        String query = sb.toString();
        /// procedura standard
        try {
            connect = ConnectionFactory.getConnection();
            stat = connect.prepareStatement(query);
            stat.setString(1, (String) name);
            result = stat.executeQuery();
            List<T> list = createObjects(result);
            if (list.isEmpty() == true)
                return null;
            else
                return list.get(0);
        }
        catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: findByName " + e.getMessage());
        }
        ConnectionFactory.close(result);
        ConnectionFactory.close(stat);
        ConnectionFactory.close(connect);
        return null;
    }


    /***
     * metoda pentru stergere (delete)
     * @param field
     * @param name
     */
    public void delete(String field, String name) {
        Connection connect = null;
        PreparedStatement stat = null;
        StringBuilder sb = new StringBuilder();
        sb.append("delete from ");
        sb.append(type.getSimpleName());
        sb.append(" where " + field + " = ?");
        /// procedura standard
        try {
            connect = ConnectionFactory.getConnection();
            stat = connect.prepareStatement( sb.toString() );
            stat.setString(1, name);
            stat.execute();
        }
        catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: delete " + e.getMessage());
        }
        ConnectionFactory.close(stat);
        ConnectionFactory.close(connect);
    }


}
