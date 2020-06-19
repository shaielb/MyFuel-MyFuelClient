package widgets.table;

import java.util.List;

import controls.MfRadioButton;
import db.interfaces.IEntity;
import handler.UiHandler;
import javafx.collections.ObservableList;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * @author shaielb
 *
 * @param <TEntity>
 */
@SuppressWarnings({ "unchecked" })
public class MfSingleDecorator<TEntity extends IEntity> extends TableDecorator<TEntity> {

	/**
	 * 
	 */
	private ToggleGroup _toggleGroup = new ToggleGroup();

	/**
	 * 
	 */
	private IEntity _selectedEntity;

	/**
	 *
	 */
	@Override
	protected void apply() {
		MfRadioButton mfRb = new MfRadioButton();
		mfRb.setColumnName("Select");
		_table.addColumn(0, UiHandler.createColumn(mfRb, () -> { 
			MfRadioButton mrb = new MfRadioButton();
			((RadioButton) mrb.getInstance()).setToggleGroup(_toggleGroup);
			return mrb;
			},
				(entity, control) -> {
					RadioButton rb = (RadioButton) control.getInstance();
					if (rb.isSelected()) {
						_selectedEntity = entity;
					}
				}));
	}

	/**
	 * @param entities
	 * @throws Exception
	 */
	public void setEntities(List<TEntity> entities) throws Exception {
		ObservableList<TEntity> list = _table.getObservableList();
		for (TEntity entity : entities) {
			list.add(entity);
		}
	}

	/**
	 * @return
	 */
	public IEntity getSelectedEntity() {
		return _selectedEntity;
	}
}
