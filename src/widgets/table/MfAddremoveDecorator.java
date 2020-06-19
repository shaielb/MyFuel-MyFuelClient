package widgets.table;

import java.util.ArrayList;
import java.util.List;

import controls.MfButton;
import db.interfaces.IEntity;
import handler.UiHandler;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

/**
 * @author shaielb
 *
 * @param <TEntity>
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MfAddremoveDecorator<TEntity extends IEntity> extends TableDecorator<TEntity> {

	public interface RemoveListener {
		public void onRemove(IEntity entity);
	}
	
	public interface AddListener {
		public void onAdd(IEntity entity);
	}

	public RemoveListener _removeListener;
	public AddListener _addListener;
	
	/**
	 * 
	 */
	private List<IEntity> _entities = new ArrayList<IEntity>();

	/**
	 *
	 */
	@Override
	protected void apply() {
		//TableColumn column = column.setGraphic(button); 
		TableColumn column = UiHandler.createButtonColumn("X", "",
				(entity, control) -> {
					_entities.remove(entity);
					_table.getObservableList().remove(entity);
					_removeListener.onRemove(entity);
				});
		MfButton btn = new MfButton("+");
		btn.addEvent((event) -> {
			try {
				addAddRow();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		column.setGraphic(btn.getInstance()); 
		_table.addColumn(column);
		/*try {
			addAddRow();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
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
		//addAddRow();
	}

	/**
	 * @throws Exception
	 */
	private void addAddRow() throws Exception {
		Class<TEntity> entityClass = _table.getEntityClass();
		TEntity entity = entityClass.newInstance();
		_entities.remove(entity);
		_entities.add(entity);
		_table.getObservableList().add(0, entity);
		_addListener.onAdd(entity);
	}

	/**
	 * @return
	 */
	public List<IEntity> getEntities(){
		return _entities;
	}
	
	public void setRemoveListener(RemoveListener rl) {
		_removeListener = rl;
	}
	
	public void setAddListener(AddListener al) {
		_addListener = al;
	}
}
