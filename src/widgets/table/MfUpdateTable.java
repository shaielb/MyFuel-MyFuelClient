package widgets.table;

import java.util.HashSet;
import java.util.Set;

import client.IClient;
import controls.button.MfUpdateButton;
import db.interfaces.IEntity;
import decorator.base.ControlDecorator;
import table.MfTable;

@SuppressWarnings({ "rawtypes" })
public class MfUpdateTable<TEntity extends IEntity> {

	private MfUpdateButton _updateButton;

	private MfTable<TEntity> _table;

	private Set<IEntity> _updateEntities = new HashSet<IEntity>();

	private Set<ControlDecorator> _updateControls = new HashSet<ControlDecorator>();

	public MfUpdateTable(Class<TEntity> entityClass, IClient client) throws Exception {
		_table = new MfTable<TEntity>(entityClass);
		initialize(client);
	}
	
	public MfUpdateTable(MfTable<TEntity> table, IClient client) throws Exception {
		_table = table;
		initialize(client);
	}

	private void initialize(IClient client) {
		_updateButton = new MfUpdateButton("Update All");

		_updateButton.setClient(client);
		_updateButton.setEntities(_updateEntities);
		_updateButton.setResponseCallBack((response) -> {
			if (!response.isIndicator()) {
				_updateButton.getInstance().setDisable(false);
			}
			else {
				clearUpdate();
			}
		});

		_table.setOnCellControlAction((entity, control) -> {
			_updateControls.add(control);
			control.setStyle("-fx-background-color: #FEF9E7;");
			_updateEntities.add(entity);
			_updateButton.getInstance().setDisable(false);
		});
	}

	protected void clearUpdate() {
		_updateEntities.clear();
		for (ControlDecorator control : _updateControls) {
			control.setStyle("");
		}
		_updateControls.clear();
		_updateButton.getInstance().setDisable(true);
	}

	public MfUpdateButton getUpdateButton() {
		return _updateButton;
	}
}
