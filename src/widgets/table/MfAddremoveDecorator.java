package widgets.table;

import java.util.ArrayList;
import java.util.List;

import db.interfaces.IEntity;
import handler.UiHandler;
import javafx.collections.ObservableList;

@SuppressWarnings({ "unchecked" })
public class MfAddremoveDecorator<TEntity extends IEntity> extends TableDecorator<TEntity> {

	private List<IEntity> _entities = new ArrayList<IEntity>();

	@Override
	protected void apply() {
		_table.addColumn(UiHandler.createButtonColumn(
				(entity) -> {
					return entity.getId().equals(-1) ? "+" : "X";
				}, "X", 
				(entity, control) -> {
					String value = (String) control.getValue();
					if ("X".equals(value)) {
						_entities.remove(entity);
						_table.getObservableList().remove(entity);
					}
					else if ("+".equals(value)) {
						_entities.add(entity);
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

	public List<IEntity> getEntities(){
		return _entities;
	}
}
