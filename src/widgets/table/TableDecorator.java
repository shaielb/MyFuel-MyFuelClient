package widgets.table;

import db.interfaces.IEntity;
import table.MfTable;

/**
 * @author shaielb
 *
 * @param <TEntity>
 */
public abstract class TableDecorator<TEntity extends IEntity> {

	/**
	 * 
	 */
	protected MfTable<TEntity> _table;
	
	/**
	 * @param table
	 */
	public void setTable(MfTable<TEntity> table) {
		_table = table;
		apply();
	}
	
	/**
	 * 
	 */
	protected abstract void apply();
}
