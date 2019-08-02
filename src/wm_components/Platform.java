package wm_components;

/**
 * Properties of platform e.g. location in xy coordinates, size to be used for
 * computing the measures
 *
 * @author Meenakshi P. created on 02.08.2019
 */
public class Platform {

    public int platformXCoord = 0;//platform location in x coordinate
    public int platformYCoord = 0;//platform location in y coordinate
    private int platformSize = 0; //size of platform

    /**
     * Default constructor 
     * Creates nothing
     *
     */
    public Platform() {
    }

    /**
     * Get X coordinate of platform
     *
     * @return X coordinate of platform as an integer
     */
    public int getX() {
        return platformXCoord;
    }

    /**
     * Set X coordinate of platform
     *
     * @param X enter X coordinate of platform location
     */
    public void setX(int X) {
        platformXCoord = X;
    }

    /**
     * Get Y coordinate of platform
     *
     * @return Y coordinate of platform as an integer
     */
    public int getY() {
        return platformYCoord;
    }

    /**
     * Set Y coordinate of platform
     *
     * @param Y enter Y coordinate of platform location
     */
    public void setY(int Y) {
        platformYCoord = Y;
    }

    /**
     * Get diameter of platform
     *
     * @return diameter of platform as an integer
     */
    public int getSize() {
        return platformSize;
    }

    /**
     * Set diameter of platform
     *
     * @param size enter diameter of platform
     */
    public void setSize(int size) {
        platformSize = size;
    }

}
