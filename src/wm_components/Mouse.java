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
    private int GeneticBackground = 0;      // undefined = 0, wild type = 1, transgenic = 2.
    private int DrugType = 0; // undefined =0, Vehicle = 1, Test = 2.
    private ArrayList<Float> XPosData; //from the original data file, origin is left top corner of video
    private ArrayList<Float> YPosData; //from the original data file, origin is left top corner of video

    private final ArrayList<Float> XPos = new ArrayList<>();
    private final ArrayList<Float> YPos = new ArrayList<>();

    private ArrayList<Float> RDist = new ArrayList<>();
    private ArrayList<Float> RVel = new ArrayList<>();
    private final ArrayList<Float> RVelalongPt = new ArrayList<>();
    private final ArrayList<Float> RVelperpendPt = new ArrayList<>();
    private final ArrayList<Float> RVelErr = new ArrayList<>();

    ArrayList<Integer> resTime = new ArrayList();

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
    public int getID() {
        return ID;
    }

    /**
     * Get the probe trial no.
     *
     * @return the probe trial no. as an integer
     */
    public int getTrial() {
        return Trial;
    }

    /**
     * Get the genotype of the mouse
     *
     * @return the genotype of the mouse as an integer
     */
    public int getGenotype() {
        return GeneticBackground;
    }

    /**
     * Get the vehicle/drug administered to the mouse
     *
     * @return the DrugType administered to the mouse as an integer
     */
    public int getDrugType() {
        return DrugType;
    }

    /**
     * Get the X data of the mouse from original file
     *
     * @return the X position as an arraylist
     */
    public ArrayList<Float> getXData() {
        return XPosData;
    }

    /**
     * Get the Y data of the mouse from original file
     *
     * @return the Y position as an arraylist
     */
    public ArrayList<Float> getYData() {
        return YPosData;
    }

    public ArrayList<Float> setXPosition(Platform P) {
        P.getX();
        for (int i = 0; i < (XPosData.size()); i++) {
            XPos.add(i, (P.getX() - XPosData.get(i)));
        }
        return XPos;
    }

    public ArrayList<Float> setYPosition(Platform P) {
        P.getY();
        for (int i = 0; i < (YPosData.size()); i++) {
            YPos.add(i, (P.getY() - YPosData.get(i)));
        }
        return YPos;
    }

    public ArrayList<Float> getXPosition() {
        return XPos;
    }

    public ArrayList<Float> getYPosition() {
        return YPos;
    }

    private ArrayList<Float> getMeasureMagnitude(ArrayList<Float> X, ArrayList<Float> Y) {
        ArrayList<Float> result = new ArrayList<>();
        for (int i = 0; i < X.size() && i < Y.size(); i++) {
            result.add(i, (float) Math.sqrt(Math.pow(X.get(i), 2) + Math.pow(Y.get(i), 2)));
        }
        return result;
    }

    private ArrayList<Float> measureAngle(ArrayList<Float> X, ArrayList<Float> Y) {
        ArrayList<Float> result = new ArrayList<>();
        for (int i = 0; i < X.size() && i < Y.size(); i++) {
            if (Y.get(i) != 0) {
                result.add(i, (float) Math.atan2(Y.get(i), X.get(i))); //How to deal with x=0 error?
            }
        }
        return result;
    }

    private ArrayList<Float> getDelMeasure(ArrayList<Float> M) {
        ArrayList<Float> result = new ArrayList<>();
        for (int i = 0; i < (M.size() - 1); i++) {
            result.add(i, M.get(i) - M.get(i + 1));
        }
        return result;
    }

    /**
     * @return distance between platform and the mouse for each frame as an
     * arraylist of float
     */
    public ArrayList<Float> getDistance() {
        return RDist;
    }

    /**
     * @return velocity of the mouse for each frame as an arraylist of float
     */
    public ArrayList<Float> getVelocity() {
        return RVel;
    }

    /**
     * @return velocity component along the platform of the mouse for each frame
     * as an arraylist of float
     */
    public ArrayList<Float> getVelocityAlongPt() {
        return RVelalongPt;
    }

    /**
     * @return velocity component perpendicular to the platform of the mouse for
     * each frame as an arraylist of float
     */
    public ArrayList<Float> getVelocityPerpendicularPt() {
        return RVelperpendPt;
    }

    /**
     * @return velocity errors of the mouse for each frame as an arraylist of
     * float
     */
    public ArrayList<Float> getVelocityError() {
        return RVelErr;
    }

    /**
     * @return residence time the mouse for each position an arraylist of
     * integer
     */
    public ArrayList<Integer> getResidenceTime() {
        return resTime;
    }

    /**
     * @return distance between platform and the mouse for each frame as an
     * arraylist of float
     */
    public ArrayList<Float> setDistance() {
        RDist = this.getMeasureMagnitude(XPos, YPos);
        return RDist;
    }

    /**
     * @return velocity of the mouse for each frame as an arraylist of float
     */
    public ArrayList<Float> setVelocity() {
        ArrayList<Float> XVel = this.getDelMeasure(XPos);
        ArrayList<Float> YVel = this.getDelMeasure(YPos);
        RVel = this.getMeasureMagnitude(XVel, YVel);
        return RVel;
    }

    /**
     * @return velocity component along the platform of the mouse for each frame
     * as an arraylist of float
     */
    public ArrayList<Float> setVelocityAlongPt() {
        RVelalongPt.clear();
        ArrayList<Float> XVel = this.getDelMeasure(XPos);
        ArrayList<Float> YVel = this.getDelMeasure(YPos);
        ArrayList<Float> ThetaVel = new ArrayList<>();
        for (int i = 0; i < (XPos.size() - 1); i++) {
            ThetaVel.add(i, (float) Math.acos(((XVel.get(i) * XPos.get(i)) + (YVel.get(i) * YPos.get(i))) / (RDist.get(i) * RVel.get(i))));
            RVelalongPt.add(i, (float) (RVel.get(i) * Math.cos(ThetaVel.get(i))));
        }
        return RVelalongPt;
    }

    /**
     * @return velocity component perpendicular to the platform of the mouse for
     * each frame as an arraylist of float
     */
    public ArrayList<Float> setVelocityPerpendicularPt() {
        RVelperpendPt.clear();
        ArrayList<Float> XVel = this.getDelMeasure(XPos);
        ArrayList<Float> YVel = this.getDelMeasure(YPos);
        ArrayList<Float> ThetaVel = new ArrayList<>();
        for (int i = 0; i < (XPos.size() - 1); i++) {
            ThetaVel.add(i, (float) Math.acos(((XVel.get(i) * XPos.get(i)) + (YVel.get(i) * YPos.get(i))) / (RDist.get(i) * RVel.get(i))));
            RVelperpendPt.add(i, (float) (RVel.get(i) * Math.sin(ThetaVel.get(i))));
        }
        return RVelperpendPt;
    }

    /**
     * @return velocity errors of the mouse for each frame as an arraylist of
     * float
     */
    public ArrayList<Float> setVelocityError() {
        RVelErr.clear();
        ArrayList<Float> XVel = this.getDelMeasure(XPos);
        ArrayList<Float> YVel = this.getDelMeasure(YPos);
        ArrayList<Float> XVelErr = new ArrayList<>();
        ArrayList<Float> YVelErr = new ArrayList<>();
        ArrayList<Float> Xcap = new ArrayList<>();
        ArrayList<Float> Ycap = new ArrayList<>();
        for (int i = 0; i < (XPos.size() - 1) && i < (YPos.size() - 1); i++) {
            Xcap.add(i, (XPos.get(i) / RDist.get(i)));
            Ycap.add(i, (YPos.get(i) / RDist.get(i)));
            XVelErr.add(i, ((XVel.get(i) * Xcap.get(i)) - XVel.get(i)));
            YVelErr.add(i, ((YVel.get(i) * Ycap.get(i)) - YVel.get(i)));
            RVelErr.add(i, this.getMeasureMagnitude(XVelErr, YVelErr).get(i));
        }
        return RVelErr;
    }

    /**
     * @return residence time the mouse for each position an arraylist of
     * integer
     */
    public ArrayList<Integer> setResidenceTime() {
        resTime.clear();
        for (int count = 0; count <= (240 * 240); count++) {
            resTime.add(0);
        }
        for (Float YPo : XPosData) {
            for (Float XPo : YPosData) {
                int arrayIdx = ((Math.round(YPo) * 240) + Math.round(XPo));
                resTime.set(arrayIdx, (resTime.get(arrayIdx) + 1));
            }
        }
        return resTime;
    }

    
        /**
     * Get mean
     *
     * @param Measure a float arraylist of values
     * @return the mean as a float
     */
    public float mean(ArrayList<Float> Measure) {
        float sum = 0;
        if (!Measure.isEmpty()) {
            for (Float m : Measure) {
                sum += m;
            }
        }
        return (sum / Measure.size());
    }
}
