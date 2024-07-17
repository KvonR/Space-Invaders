/**
 * Interface defining methods for handling events. The only event implemented so far is collision events.
 * This interface has been designed to support the Chain of Resposibility pattern
 */
public interface Events {

/**
  * Handles a collision event.
  * Implementing specific methods in the Chain will define specific actions to be taken when an entity 
  * involved in the collision event is responsible for handling it.
  *
  * @param event 		Collision event to be handled.
  */
    void handleCollisionEvent(EntityCollisions event);

    
    
/**
  * Sets the next handler in the chain of responsibility.
  * This method links the current handler to the next one, allowing for propagation 
  * of an event handling process through the chain.   
  * 
  * @param next The next event handler in the chain.
  */
    void setNextHandler(Events next);
    
}
