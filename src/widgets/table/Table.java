package widgets.table;

import java.util.ArrayList;
import java.util.List;

import db.interfaces.IEntity;
import table.MfTable;

/**
 * @author shaielb
 *
 * @param <TEntity>
 */
public class Table<TEntity extends IEntity> {

	/**
	 * 
	 */
	private MfTable<TEntity> _table;
	
	/**
	 * 
	 */
	private List<TableDecorator<TEntity>> _decorators = new ArrayList<TableDecorator<TEntity>>();
	
	/**
	 * @param table
	 */
	public void setTable(MfTable<TEntity> table) {
		_table = table;
		for (TableDecorator<TEntity> decorator : _decorators) {
			decorator.setTable(table);
		}
	}
	/**
	 * @param decorator
	 */
	public void addDecorator(TableDecorator<TEntity> decorator) {
		if (_table != null) {
			decorator.setTable(_table);
		}
		_decorators.add(decorator);
	}
}
