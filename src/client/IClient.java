package client;

import java.io.IOException;
import java.util.Collection;

import db.interfaces.IEntity;
import messages.request.ICollect;
import messages.request.IFilter;
import messages.request.IInsert;
import messages.request.IRemove;
import messages.request.IRequest;
import messages.request.IUpdate;
import messages.response.IResponseCallBack;
import messages.response.ResponseEvent;

/**
 * @author shaielb
 *
 */
public interface IClient {
	/**
	 * @return
	 */
	public IFilter getFilterRequest();

	/**
	 * @return
	 */
	public ICollect getCollectRequest();

	/**
	 * @return
	 */
	public IInsert getInsertRequest();

	/**
	 * @return
	 */
	public IUpdate getUpdateRequest();

	/**
	 * @return
	 */
	public IRemove getRemoveRequest();

	/**
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public ResponseEvent sendRequest(IRequest request) throws IOException;

	/**
	 * @param <TEntity>
	 * @param entityClass
	 * @param callback
	 * @throws Exception
	 */
	public <TEntity extends IEntity> void cacheEntityEnums(Class<TEntity> entityClass, IResponseCallBack callback) throws Exception;

	/**
	 * @param tables
	 * @param callback
	 * @throws IOException
	 */
	public void cacheTables(Collection<String> tables, IResponseCallBack callback) throws IOException;
	
	/**
	 * @param enumTable
	 * @param keyValue
	 * @return
	 */
	public IEntity getEnum(String enumTable, String keyValue);
}
