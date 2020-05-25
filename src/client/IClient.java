package client;

import java.io.IOException;

import db.interfaces.IEntity;
import messages.request.ICollect;
import messages.request.IFilter;
import messages.request.IInsert;
import messages.request.IRemove;
import messages.request.IRequest;
import messages.request.IUpdate;
import messages.response.IResponseCallBack;
import messages.response.ResponseEvent;

public interface IClient {
	public IFilter getFilterRequest();
	
	public ICollect getCollectRequest();
	
	public IInsert getInsertRequest();
	
	public IUpdate getUpdateRequest();
	
	public IRemove getRemoveRequest();
	
	public ResponseEvent sendRequest(IRequest request) throws IOException;
	
	public <TEntity extends IEntity> void cacheEntityEnums(Class<TEntity> entityClass, IResponseCallBack callback) throws Exception;
}
