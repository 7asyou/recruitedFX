/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techaholic.recruited.CRUD.Service;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author sanabenfadhel
 */
public interface IService<T> {
	public void create(T t) throws SQLException;

	public void update(T t, int id) throws SQLException;

	public void delete(int id) throws SQLException;

	public T findById(int id) throws SQLException;

	ArrayList<T> getAll() throws SQLException;

}
