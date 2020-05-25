package widgets.table;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import client.IClient;
import db.interfaces.IEntity;
import handler.ControlsHandler;
import javafx.collections.ObservableList;
import table.MfTable;

@SuppressWarnings({ "unchecked" })
public class MfAddRemoveTable<TEntity extends IEntity> {

	private MfTable<TEntity> _table;

	private Set<IEntity> _addEntities = new HashSet<IEntity>();
	private Set<IEntity> _removeEntities = new HashSet<IEntity>();

	public MfAddRemoveTable(Class<TEntity> entityClass, IClient client) throws Exception {
		_table = new MfTable<TEntity>(entityClass);
		initialize(client);
	}

	public MfAddRemoveTable(MfTable<TEntity> table, IClient client) throws Exception {
		_table = table;
		initialize(client);
	}

	private void initialize(IClient client) {
		_table.addColumn(ControlsHandler.createButtonColumn(
				(entity) -> {
					return entity.getId().equals(-1) ? "+" : "X";
				}, "X", 
				(entity, control) -> {
					String value = (String) control.getValue();
					if ("X".equals(value)) {
						_addEntities.remove(entity);
						_removeEntities.add(entity);
						_table.getObservableList().remove(entity);
					}
					else if ("+".equals(value)) {
						_addEntities.add(entity);
						_removeEntities.remove(entity);
						_table.getObservableList().add((TEntity) entity);
						entity.setId(0);
						control.setValue("X");
						addAddRow();
					}
				}));
	}

	public void setEntities(List<TEntity> entities) throws Exception {
		ObservableList<TEntity> list = _table.getObservableList();
		for (TEntity entity : entities) {
			list.add(entity);
		}
		addAddRow();
	}
	
	private void addAddRow() throws Exception {
		Class<TEntity> entityClass = _table.getEntityClass();
		TEntity entity = entityClass.newInstance();
		entity.setId(-1);
		_table.getObservableList().add(entity);
	}
}
