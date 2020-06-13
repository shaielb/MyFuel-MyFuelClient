// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import annotations.Table;
import db.interfaces.IEntity;
import db.interfaces.IEntityBridge;
import db.interfaces.IEnum;
import db.services.Services;
import globals.Globals;
import handler.UiHandler;
import messages.Header.RequestType;
import messages.QueryContainer;
import messages.Request;
import messages.Response;
import messages.request.ICollect;
import messages.request.IFilter;
import messages.request.IInsert;
import messages.request.IRemove;
import messages.request.IRequest;
import messages.request.IUpdate;
import messages.response.IResponseCallBack;
import messages.response.ResponseEvent;
import ocsf.client.AbstractClient;
import utilities.Cache;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
/**
 * @author shaielb
 *
 */
@SuppressWarnings("unchecked")
public class Client extends AbstractClient implements IClient
{
	/**
	 * 
	 */
	private Long _sourceId = 0l;

	/**
	 * 
	 */
	private ConcurrentHashMap<Long, ResponseEvent> _responseEvents = new ConcurrentHashMap<Long, ResponseEvent>();

	//Constructors ****************************************************

	/**
	 * Constructs an instance of the chat client.
	 *
	 * @param host The server to connect to.
	 * @param port The port number to connect on.
	 * @param clientUI The interface type variable.
	 */
	/**
	 * @param host
	 * @param port
	 * @throws IOException
	 */
	public Client(String host, int port) 
			throws IOException 
	{
		super(host, port); //Call the superclass constructor
		openConnection();
	}

	//Instance methods ************************************************

	//Request methods ************************************************
	/**
	 *
	 */
	public IFilter getFilterRequest() {
		return generateRequest(RequestType.Filter);
	}

	/**
	 *
	 */
	public ICollect getCollectRequest() {
		return generateRequest(RequestType.Collect);
	}

	/**
	 *
	 */
	public IInsert getInsertRequest() {
		return generateRequest(RequestType.Insert);
	}

	/**
	 *
	 */
	public IUpdate getUpdateRequest() {
		return generateRequest(RequestType.Update);
	}

	/**
	 *
	 */
	public IRemove getRemoveRequest() {
		return generateRequest(RequestType.Remove);
	}

	/**
	 *
	 */
	public ResponseEvent sendRequest(IRequest request) throws IOException {
		Request req = (Request) request;
		// adding a response event to map
		ResponseEvent responseEvent = new ResponseEvent();
		_responseEvents.put(req.getHeader().getId(), responseEvent);

		RequestType type = req.getHeader().getType();
		System.out.println("#############################################");
		System.out.println(type);
		if (type.equals(RequestType.Filter)) {
			List<QueryContainer> queryContainers = req.getQueryContainers();
			for (QueryContainer entry : queryContainers) {
				System.out.println(entry.getQueryEntity());
				Map<String, String> signs = entry.getQueryMap();
				for (String field : signs.keySet()) {
					System.out.println("\t" + field + ": " + signs.get(field));
				}
			}
		}
		else if (type.equals(RequestType.Collect)) {
			for (String table : req.getTables()) {
				System.out.println(table);
			}
		}
		else {
			for (IEntity entity : req.getEntities()) {
				System.out.println(entity);
			}
		}
		System.out.println("#############################################");
		// sending request to server
		sendToServer(request);
		return responseEvent;
	}
	//End of Request Methods

	// Cache ************************************************
	/**
	 *
	 */
	public <TEntity extends IEntity> void cacheEntityEnums(Class<TEntity> entityClass, IResponseCallBack callback) throws Exception {
		List<String> enumTables = new ArrayList<String>();
		UiHandler.iterateFields(entityClass, 
				(field, columnName, columnIndex) -> {
					if (columnName.endsWith("_fk")) {
						Table tableAnnotation = field.getAnnotation(Table.class);
						if (tableAnnotation != null && tableAnnotation.Name().endsWith("_enum")) {
							enumTables.add(tableAnnotation.Name());
						}
					}
				});
		cacheTables(enumTables, callback);
	}

	/**
	 *
	 */
	@Override
	public void cacheTables(Collection<String> tables, IResponseCallBack callback) throws IOException {
		if (tables.size() > 0) {
			ICollect request = getCollectRequest();
			request.setTables(tables.toArray(new String[tables.size()]));
			ResponseEvent responseEvent = sendRequest(request);
			responseEvent.continueWith((response) -> {
				Map<String, List<IEntity>> enumsTables = (Map<String, List<IEntity>>) Cache.get(Globals.EnumTables);
				if (enumsTables == null) {
					Cache.put(Globals.EnumTables, enumsTables = new HashMap<String, List<IEntity>>());
				}
				enumsTables.putAll(response.getTablesMap());
				callback.execute(response);
			});
		}
	}

	/**
	 *
	 */
	@Override
	public IEntity getEnum(String enumTable, String keyValue) {
		Map<String, List<IEntity>> enumsTables = (Map<String, List<IEntity>>) Cache.get(Globals.EnumTables);
		List<IEntity> entities = enumsTables.get(enumTable);
		if (entities != null) {
			for (IEntity entity : entities) {
				if (entity instanceof IEnum) {
					if (((IEnum)entity).getKey().toLowerCase().equals(keyValue.toLowerCase())) {
						return entity;
					}
				}
			}
		}
		return  null;
	}
	// Cache ************************************************

	//Response methods ************************************************

	/**
	 * This method handles all data that comes in from the server.
	 *
	 * @param msg The message from the server.
	 */
	/**
	 *
	 */
	@Override
	public synchronized void handleMessageFromServer(Object msg) 
	{
		Response response = (Response) msg;
		Collection<IEntity> entities = response.getEntities();
		String table = null;
		IEntityBridge bridge = null;
		if (entities != null && entities.size() > 0) {
			for (IEntity entity : entities) {
				if (table == null) {
					table = entity.getClass().getAnnotation(Table.class).Name();
					bridge = Services.getBridge(table);
				}
				try {
					bridge.collectFromEntity(entity, 
							(index, name, value) -> {
								if (name.endsWith("_enum_fk")) {
									Map<String, List<IEntity>> enumsTables = (Map<String, List<IEntity>>) Cache.get(Globals.EnumTables);
									List<IEntity> list = enumsTables.get(name.subSequence(0, name.indexOf("_fk")));
								}
							});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		ResponseEvent responseEvent = _responseEvents.get(response.getHeader().getId());
		responseEvent.setResponse(response);
	}
	//End of Response Methods

	/**
	 * @param type
	 * @return
	 */
	private Request generateRequest(RequestType type) {
		Request request = new Request();
		request.getHeader().setId((_sourceId + 1) > Long.MAX_VALUE ? 0 : _sourceId++);
		request.getHeader().setType(type);
		return request;
	}

	/**
	 *
	 */
	@Override
	protected void connectionException(Exception exception) {
		System.out.println(exception);
	}

	/**
	 * This method terminates the client.
	 */
	/**
	 * 
	 */
	public void quit()
	{
		try
		{
			closeConnection();
		}
		catch(IOException e) {}
		System.exit(0);
	}
}
//End of class
