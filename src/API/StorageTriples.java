package API;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.impl.TreeModel;
import org.eclipse.rdf4j.model.vocabulary.RDF;

import com.franz.agraph.repository.AGRepositoryConnection;
import com.franz.agraph.repository.AGValueFactory;

import Object.ParentObject;
import Object.Relationship;

public class StorageTriples {
	private AGRepositoryConnection connection;
	private TreeModel treeModel = new TreeModel();
	private AGValueFactory valueFactory;
	 
	public StorageTriples(AGRepositoryConnection connection){
		 this.connection = connection;
		 this.valueFactory = connection.getValueFactory();
	 }
	 
	 public void setAttributeEntity(ParentObject object) {
		 IRI label = valueFactory.createIRI(Configue.ENTITY_LINK,object.getLable());
		 IRI type = valueFactory.createIRI(Configue.CLASS_LINK,object.getType());
		 treeModel.add(label,RDF.TYPE,type);
		 //chua hoan thien
		 if(treeModel.size() > 200000) storageModel();
	 }
	 
	 public void setRelationship(Relationship relationship) {
		 IRI s = valueFactory.createIRI(Configue.ENTITY_LINK,relationship.subject.toString());
		 IRI p = valueFactory.createIRI(Configue.RELATIONSHIP_LINK,relationship.type.toString());
		 IRI o = valueFactory.createIRI(Configue.ENTITY_LINK,relationship.object.toString());
		 treeModel.add(s,p,o);
		 if(treeModel.size() > 200000) storageModel();
	 }
	 
	 private void storageModel() {
		 System.out.println(treeModel.size());
		 connection.add(treeModel);
		 treeModel.clear();
		 System.out.println(connection.size());
		 System.out.println("================================================================");
	 }
}
