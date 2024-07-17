/**
 * Represents a collision event between two entities in the game.
 * This class encapsulates information about the entities involved in a collision.
 */
public class EntityCollisions {
	private Entity entityA;//1st Entity
	private Entity entityB;//2nd Entity

/**
 * 
 * @param entityA		1st Entity involved in collision
 * @param entityB		2nd Entity involved in collision
 */
	public EntityCollisions(Entity entityA, Entity entityB){
		this.entityA = entityA;
		this.entityB = entityA;
	}
	
/**
 * Two Getter Methods that return each entity respectively
 * @return 		1st Entity
 * @return  	2nd Entity
 */
	public Entity getEntityA() { return entityA; }
	public Entity getEntityB() { return entityB;}
	
	
}
