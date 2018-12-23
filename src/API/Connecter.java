package API;

import com.franz.agraph.repository.AGCatalog;
import com.franz.agraph.repository.AGRepository;
import com.franz.agraph.repository.AGRepositoryConnection;
import com.franz.agraph.repository.AGServer;

public class Connecter {
	private String serverURL;
	private String user;
	private String password;
	private AGServer server;
	private AGCatalog catalog;
	private AGRepository repository;
	private AGRepositoryConnection connection;
	
	private Connecter() {
		this.serverURL = Configue.SERVER_URL;
		this.user = Configue.USERNAME;
		this.password = Configue.PASSWORD;
		this.server = new AGServer(this.serverURL,this.user,this.password);
		this.catalog = server.getRootCatalog();
	}
	
	private Connecter(String serverURL,String user ,String pass) {
		this.serverURL = serverURL;
		this.user = user;
		this.password = pass;
		this.server = new AGServer(this.serverURL,this.user,this.password);
		this.catalog = server.getRootCatalog();
	}
	
	public AGRepositoryConnection createConnect(String repositoryID) {
		if(catalog.hasRepository(repositoryID)) {
			repository = catalog.openRepository(repositoryID);
			connection = repository.getConnection();
		}
		else {
			createRepo(repositoryID);
		}
		return connection;
	}
	
	public void createRepo(String repositoryID) {
		repository = catalog.createRepository(repositoryID);
		repository.initialize();
		connection = repository.getConnection();
		connection.setNamespace("class", Configue.CLASS_LINK);
		connection.setNamespace("ent", Configue.ENTITY_LINK);
		connection.setNamespace("prop", Configue.PROPERTIES_LINK);
		connection.setNamespace("rela", Configue.RELATIONSHIP_LINK);
	}
	
	public void closeConnect() {
		if(connection == null) return;
		connection.close();
		repository.shutDown();
	}
}
