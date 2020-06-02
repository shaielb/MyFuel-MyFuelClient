package widgets.table;

import java.util.ArrayList;
import java.util.List;

import controls.MfCheckBox;
import db.interfaces.IEntity;
import handler.ControlsHandler;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;

@SuppressWarnings({ "unchecked" })
public class MfMultiCapability<TEntity extends IEntity> extends TableDecorator<TEntity> {

	private List<IEntity> _selectedEntities = new ArrayList<IEntity>();

	@Override
	protected void apply() {
		MfCheckBox rb = new MfCheckBox();
		_table.addColumn(ControlsHandler.createColumn(rb, 
				(entity, control) -> {
					CheckBox cb = (CheckBox) control.getInstance();
					if (cb.isSelected()) {
						_selectedEntities.add(entity);
					}
					else {
						_selectedEntities.remove(entity);
					}
				}));
	}

	public void setEntities(List<TEntity> entities) throws Exception {
		ObservableList<TEntity> list = _table.getObservableList();
		for (TEntity entity : entities) {
			list.add(entity);
		}
	}

	public List<IEntity> getSelectedEntities() {
		return _selectedEntities;
	}
}
