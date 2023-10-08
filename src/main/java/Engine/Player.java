package Engine;

import Engine.Objects.GameObject;
import Engine.Objects.Shapes;
import Engine.Utils.Vector2D;
import javafx.scene.input.KeyCode;

import java.util.Arrays;

import static Engine.Renderer.GraphicsHandler.pressedKeys;

public class Player extends GameObject {

    public Player(Vector2D Position, double Height, double Width, Shapes Shape) {
        super(Position, Height, Width, Shape);
    }

    @Override
    public void UpdateObject() {
        // Assuming you have a Vector2D named Velocity for the object's velocity

        // For the 'W' key (Jump logic)
        if (pressedKeys.contains(KeyCode.W)) {
            // Only allow jumping if the object is on the ground (you'll need a method or condition to check this)
            if (onGround(this)) {
                System.out.println("On Floor");
                this.Velocity.y = -5;  // This gives an initial upward velocity for the jump
                this.changeY(this.getY() - 3);
            }
        }
        // No need for an else condition here since gravity will pull the object down after the jump

        // For the 'A' key (Move left)
        if (pressedKeys.contains(KeyCode.A)) {
            this.Velocity.x = -2;  // Negative for leftward movement
        } else if (pressedKeys.contains(KeyCode.D)) {  // For the 'D' key (Move right)
            this.Velocity.x = 2;   // Positive for rightward movement
        } else {
            this.Velocity.x = 0;   // Stop horizontal movement if neither 'A' nor 'D' is pressed
        }




        // Gravity logic (pulling the object down after a jump)
        this.Velocity.y += constGravity;


        for(GameObject object : GameObjects) {
            if (this == object) continue;

            if (checkCollisionBetween(this, object)) {
                // Handle the collision
                if (this.getY() + this.getHeight() <= object.getY() + object.getHeight()) {
                    // Player is on top of the object
                    this.Velocity.y = 0;
                    this.changeY(object.getY() - this.getHeight()); // Adjust player's position to sit on the object
                }
                // Add more conditions to handle collisions from other sides if needed
            }
        }

        String collisionSide = sideCollision(this);
        if (collisionSide.equals("left")) {
            this.Velocity.x = 2;
            this.changeX(this.getX() + 1);

        } else if (collisionSide.equals("right")) {
            this.Velocity.x = -2;
            this.changeX(this.getX() - 1);
        }



        this.changePositionVector(this.Velocity);

    }





}
