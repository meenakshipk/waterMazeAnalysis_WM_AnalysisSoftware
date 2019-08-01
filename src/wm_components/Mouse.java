package wm_components;

import java.util.ArrayList;

/**
 * An implementation of a mouse used in an experiment
 *
 * @author Meenakshi P. created on 29.07.2019, 18.10
 */
public class Mouse {

    private final int ID; //mouse ID;
    private int Trial = 0;      // undefined = 0, probe day 3 = 1, probe day 5 = 2, probe day 7 = 3.
    private int GeneticBackground = 0;      // wild type = 0, transgenic = 1.
    private int DrugType = 0; // Vehicle = 0 or Test = 1.
    private ArrayList<Float> XPosData;
    private ArrayList<Float> YPosData;

    /**
     * Default constructor Creates a new mouse
     *
     * @param number int rep of a mouse
     */
    public Mouse(int number) {
        ID = number;
    }

    /**
     * Creates a new mouse
     *
     * @param number int rep of a mouse
     * @param trial int rep of the trial the mouse underwent
     */
    public Mouse(int number, int trial) {
        ID = number;
        Trial = trial;
    }

    /**
     * Creates a new mouse
     *
     * @param number int rep of a mouse
     * @param trial int rep of the trial the mouse underwent
     * @param type int rep of the genetic background of the mouse
     */
    public Mouse(int number, int trial, int type) {
        ID = number;
        Trial = trial;
        GeneticBackground = type;
    }

    /**
     * Creates a new mouse
     *
     * @param number int rep of a mouse
     * @param trial int rep of the trial the mouse underwent
     * @param type int rep of the genetic background of the mouse
     * @param drug int rep of the drug cohort the mouse belongs to
     */
    public Mouse(int number, int trial, int type, int drug) {
        ID = number;
        Trial = trial;
        GeneticBackground = type;
        DrugType = drug;
    }

    /**
     * Creates a new mouse
     *
     * @param number int rep of a mouse
     * @param XPos x position data of the mouse
     * @param YPos y position data of the mouse
     *
     */
    public Mouse(int number, ArrayList<Float> XPos, ArrayList<Float> YPos) {
        ID = number;
        XPosData = XPos;
        YPosData = YPos;
    }

    /**
     * Creates a new mouse
     *
     * @param number int rep of a mouse
     * @param trial int rep of the trial the mouse underwent
     * @param type int rep of the genetic background of the mouse
     * @param drug int rep of the drug cohort the mouse belongs to
     * @param XPos x position data of the mouse
     * @param YPos y position data of the mouse
     */
    public Mouse(int number, int trial, int type, int drug, ArrayList<Float> XPos, ArrayList<Float> YPos) {
        ID = number;
        Trial = trial;
        GeneticBackground = type;
        DrugType = drug;
        XPosData = XPos;
        YPosData = YPos;
    }

    /**
     * Get the mouse ID
     *
     * @return ID of the mouse as an integer
     */
    public int ID() {
        return ID;
    }

    /**
     * Get the probe trial no.
     *
     * @return the probe trial no. as an integer
     */
    public int trial() {
        return Trial;
    }

    /**
     * Get the genotype of the mouse
     *
     * @return the genotype of the mouse as an integer
     */
    public int genotype() {
        return GeneticBackground;
    }

    /**
     * Get the vehicle/drug administered to the mouse
     *
     * @return the DrugType administered to the mouse as an integer
     */
    public int drugType() {
        return DrugType;
    }

    /**
     * Get the X position of the mouse
     *
     * @return the X position as an arraylist
     */
    public ArrayList<Float> XPosition() {
        return XPosData;
    }

    /**
     * Get the Y position of the mouse
     *
     * @return the Y position as an arraylist
     */
    public ArrayList<Float> YPosition() {
        return YPosData;
    }

    /**
     * Calculate instantaneous velocity of the mouse
     *
     * @param XPos An arraylist of the X position of the mouse
     * @param YPos An arraylist of the X position of the mouse
     * @return the instantaneous velocity as an arraylist
     */
    public ArrayList<Float> calculateVelocity(ArrayList<Float> XPos, ArrayList<Float> YPos) {
        ArrayList<Float> VelXcomp = new ArrayList<>();
        ArrayList<Float> VelYcomp = new ArrayList<>();
        ArrayList<Float> instVelocity = new ArrayList<>();
        for (int i = 0; i < (XPos.size() - 1) && i < (YPos.size() - 1); i++) {
            VelXcomp.add(i, (XPos.get(i + 1) - XPos.get(i)));
            VelYcomp.add(i, (YPos.get(i + 1) - YPos.get(i)));
            instVelocity.add(i, (float) Math.sqrt(Math.pow(VelXcomp.get(i), 2) + Math.pow(VelYcomp.get(i), 2)));
            //rm = (float) Math.sqrt(Math.pow(px - 175, 2) + Math.pow(py - 175, 2))
        }
        return instVelocity;
    }

    /**
     * Get the calculated instantaneous velocity of the mouse
     *
     * @return the instantaneous velocity as an arraylist
     */
    public ArrayList<Float> getVelocity() {
        ArrayList<Float> instVelocity = this.calculateVelocity(XPosData, YPosData);
        return instVelocity;
    }

}
