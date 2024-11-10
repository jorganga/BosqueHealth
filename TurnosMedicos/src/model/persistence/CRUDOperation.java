package model.persistence;

import java.util.ArrayList;

public interface CRUDOperation<D, E> {

	/**
	 * Muestra todas las entradas.
	 */
	public String showAll();

	/**
	 * Obtiene todas las entradas.
	 */
	public ArrayList<D> getAll();

	/**
	 * Añade una nueva entrada.
	 */
	public boolean add(D newData);

	/**
	 * Elimina una entrada.
	 */
	public boolean delete(D toDelete);

	/**
	 * Busca una entrada.
	 */
	public E find(E toFind);

	/**
	 * Actualiza una entrada.
	 */
	public boolean update(D previous, D newData);
}
