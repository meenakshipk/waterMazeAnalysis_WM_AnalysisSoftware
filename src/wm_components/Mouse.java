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

}
