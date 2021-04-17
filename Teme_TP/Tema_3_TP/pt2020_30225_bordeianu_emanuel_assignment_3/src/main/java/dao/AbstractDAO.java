package dao;

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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;



public class AbstractDAO<T> {
    private static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName() );
    private Class<T> type = null;

    @SuppressWarnings("unchecked")
    /***
     * constructor 1
     */
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


    /***
     * constructor 2
     * @param cls
     */
    public AbstractDAO(Class<T> cls) {
        this.type = cls;
    }


    /***
     * metoda pentru interogari - select
     * @param field
     * @return
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }


    /***
     * metoda pentru cautarea dupa id (daca este cazul)
     * @param id
     * @return
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return createObjects(resultSet).get(0);
        }
        catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        }
        finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }


    /***
     * metoda pentru crearea obiectelor
     * @param resultSet
     * @return
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();

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
    //---------------------------------------------------------------
    //---------------------------------------------------------------
    //---------------------------------------------------------------



    /***
     * metoda pentru afisarea tuturor informatiilor
     * @return
     */
    public List<T> findAll() {
        Connection connect = null;
        PreparedStatement stat = null;
        ResultSet result = null;
        StringBuilder sb = new StringBuilder();
        sb.append("select * from ");
        sb.append(type.getSimpleName());
        String query = sb.toString();
        try {
            connect = ConnectionFactory.getConnection();
            stat = connect.prepareStatement(query);
            result = stat.executeQuery();
            List<T> list = createObjects(result);
            if (list.isEmpty() == true)
                return null;
            else
                return list;
        }
        catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: findAll" + e.getMessage());
        }
        ConnectionFactory.close(result);
        ConnectionFactory.close(stat);
        ConnectionFactory.close(connect);
        return null;
    }


    /***
     * metoda de inserare
     * @param t
     */
    public void insert(T t) {
        int nr = 0;
        Connection connect = null;
        PreparedStatement stat = null;
        StringBuilder sb = new StringBuilder();
        sb.append("insert into ");
        sb.append(type.getSimpleName());
        sb.append(" (");
        for (Field fd : type.getDeclaredFields()) {
            if (nr != 0){
                sb.append(",");
            }
            sb.append(fd.getName() );
            nr++;
        }
        sb.append(") values (");
        for (int j = 0; j < nr; j++) {
            if (j != 0){
                sb.append(",");
            }
            sb.append("?");
        }
        sb.append(")");
        try {
            connect = ConnectionFactory.getConnection();
            stat = connect.prepareStatement( sb.toString() );
            Field[] fd2 = t.getClass().getDeclaredFields();
            for (int k = 0; k < fd2.length; k++) {
                fd2[k].setAccessible(true);
                Object value = fd2[k].get(t);
                stat.setObject(k+1, value);
            }
            stat.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
            LOGGER.log(Level.WARNING, type.getName() + "DAO: insert " + e.getMessage());
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        ConnectionFactory.close(stat);
        ConnectionFactory.close(connect);
    }


    /***
     * metoda pentru actualizarea informatiilor
     * @param t
     */
    public void update(T t) {
        Connection connect = null;
        PreparedStatement stat = null;
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("update ");
            sb.append(type.getSimpleName());
            sb.append(" set ");
            Field[] fd3 = t.getClass().getDeclaredFields();
            for (int l = 0; l < fd3.length - 1; l++) {
                fd3[l].setAccessible(true);
                Object obj = fd3[l].get(t);
                sb.append(fd3[l].getName() + " = '" +obj+ "', ");
            }
            fd3[fd3.length - 1].setAccessible(true);
            Object value = fd3[fd3.length - 1].get(t);
            sb.append(fd3[fd3.length - 1].getName() + " = '" + value + "' where (");
            Object objj2 = fd3[0].get(t);
            sb.append(fd3[0].getName() + " = '" +objj2+ "')");
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        /// proceduri standard
        try {
            connect = ConnectionFactory.getConnection();
            stat = connect.prepareStatement( sb.toString() );
            stat.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
            LOGGER.log(Level.WARNING, type.getName() + "DAO: update " + e.getMessage());
        }
        ConnectionFactory.close(stat);
        ConnectionFactory.close(connect);
    }


}
