package widgets.table;

import java.util.HashSet;
import java.util.Set;

import action.ActionControl;
import action.UpdateCapability;
import adapter.base.ControlAdapter;
import client.IClient;
import db.interfaces.IEntity;
import table.MfTable;

/**
 * @author shaielb
 *
 * @param <TEntity>
 */
@SuppressWarnings({ "rawtypes" })
public class MfUpdateDecorator<TEntity extends IEntity> extends TableDecorator<TEntity> {

	/**
	 * 
	 */
	protected ActionControl _updateAction;

	/**
	 * 
	 */
	private IClient _client;

	/**
	 * 
	 */
	private Set<IEntity> _updateEntities = new HashSet<IEntity>();

	/**
	 * 
	 */
	private Set<ControlAdapter> _updateControls = new HashSet<ControlAdapter>();

	/**
	 * @param table
	 * @param client
	 * @throws Exception
	 */
	public void setTable(MfTable<TEntity> table, IClient client) throws Exception {
		_client = client;
		super.setTable(table);
	}

	/**
	 *
	 */
	@Override
	protected void apply() {
		_updateAction = new ActionControl();

		_updateAction.setClient(_client);

		UpdateCapability updateCapability = new UpdateCapability();
		updateCapability.setEntities(_updateEntities);

		_updateAction.addCapability(updateCapability);

		_updateAction.setCallback((response) -> {
			if (!response.isPassed()) {
				_updateAction.getControl().getInstance().setDisable(false);
			}
			else {
				clearUpdate();
			}
		});

		_table.setOnCellControlAction((entity, control) -> {
			_updateControls.add(control);
			control.setStyle("-fx-background-color: #FEF9E7;");
			_updateEntities.add(entity);
			_updateAction.getControl().getInstance().setDisable(false);
		});
	}

	/**
	 * 
	 */
	protected void clearUpdate() {
		_updateEntities.clear();
		for (ControlAdapter control : _updateControls) {
			control.setStyle("");
		}
		_updateControls.clear();
		_updateAction.getControl().getInstance().setDisable(true);
	}

	/**
	 * @return
	 */
	public ActionControl getUpdateAction() {
		return _updateAction;
	}
}
