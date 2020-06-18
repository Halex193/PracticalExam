CloudApp

General backend description
The following domain entities are given:
Node(String name - unique, Integer totalCapacity)
Pod (String name - unique, Integer cost) 
unidirectional one to many relation between them
both entities extend BaseEntity.
The NodeRepository and PodRepository interfaces extend 
CloudAppRepository<T extends BaseEntity<ID>, ID extends Serializable> extends JpaRepository<T, ID>.
At the repository level there will be no other interfaces and classes.

The NodeService interface contains ONLY the following methods:
	void add(String name, Integer totalCapacity);

List<Node> getNodes();

Node update(String name, Integer totalCapacity);

//the nodes for which totalCapacity is greater than the sum of costs (from the associated Pods) 
List<Node> getAvailableNodes();

// the method will verify if the pod cost is smaller than the current capacity and,if this is the case, assign the pod to the node. returns the missing cost value (how much more capacity would be needed to fulfill the entire cost)
Integer trySchedulePod(Long nodeId, Long podId); 

void deleteSchedule(Long nodeId, Long podId);

The PodService interface contains ONLY the following methods:
Pod add(String name, Integer cost);

List<Pod> getPods();

//returns all pods which are currently not scheduled on any node.
List<Pod> getAvailablePods();

Pod delete(Long podId);

You are not allowed to add other methods in the services on the backend.


Node operations
Link to the “Nodes” page. 
Page that lists all nodes in a table format (columns: name, totalCapacity and edit). [F1 25p]
Nodes should be sorted ascending by name. [F2 25p]
“Add Node” button below the table, that opens on the same page a form where you can specify a name and totalCapacity to add. 
When you press the “Submit” button the data will be sent to the backend, and the form will disappear. [F3 25p]
Name needs to be at least 5 characters long and totalCapacity bigger than 0, these need to be validated on the backend. If there is an error a message should appear. 
After a successful add if you reload the page the new node should appear. [F4 25p]
The “Edit Node” button in each row when clicked will let you modify the totalCapacity directly in the table and replaces the button “Edit Node” with a button “Save”. [F5 25p]
The button “Save” when pressed will send the new node to the backend, where it will be saved in the database. After a successful update the “Save” button is replaced with the “Edit Node” button discussed above [F6 25p]

Pod operations
Link to the “Pods” page
Page that displays the pods in an unordered list format (every row will have a label of form `${name} - ${cost}` and a button “X”). [F7 25p]
Above the list will be an “Add Pod” button, which opens in a new page a form where you can specify the name and the cost of a new pod to add.
When you press the “Submit” button the data will be sent to the backend.
Name needs to be exactly 3 characters long, and the cost needs to be positive (can be 0 too), these need to be validated ONLY on the frontend.
After a successful add, the user should be redirected to the pod listing page, and the new pod should appear without needing to reload. [F8 25p]
The “X” button in each line when clicked will delete the pod from the list, and send a delete request to the backend, which will delete the pod from the database. [F9 25p]
Above the list there should also be an input with type text and a button “Filter pods with cost less than”, which when pressed will only show the pods which have a cost less than the value from the input. If there is no value all pods should be shown. The filter needs to be applied on the frontend (filtering on the backend is not allowed). [F10 25p]



Pod scheduling
Link to the “Pod scheduling” page
Page should list all schedules (only node name and pod name) in any format.
Above the schedules there should be two inputs of type select and a “Schedule pod” button.
First select should contain all AVAILABLE node names.
Second select should contain all AVAILABLE pod names. [F11 25p]
When the “Schedule pod” button is pressed the schedule request is sent to the backend, which will verify if scheduling is possible.
If scheduling is possible, the relation between the node and the pod is saved, and it should appear in the list on the frontend without reloading the page.
If scheduling is NOT possible, the missing cost (the overflow value after reaching the total capacity) will be sent to the frontend, and together with a message will be displayed. (in the response from the backend to the frontend ONLY the integer cost value should be sent) [F12 25p]
Deleting of schedules should be possible. [F13 20p]
(On the “Nodes” page) Above the table put a button “Show only available nodes” which when pressed will only show the nodes that have a current capacity bigger than 0. (this filter should be applied on the backend side, filtering on the frontend is not allowed) [F14 20p]
(On the “Pods” page) If a pod is assigned to a node it should NOT be available for delete (the “X” button should not be shown). [F15 20p]
