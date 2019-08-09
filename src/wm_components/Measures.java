package wm_components;

import java.util.ArrayList;

/**
 * List of measures to be computed.
 *
 * @author Meenakshi P. created on 02.08.2019. Updated on 06.08.2019
 */
public class Measures {

    private ArrayList<Float> XPos = new ArrayList<>();
    private ArrayList<Float> YPos = new ArrayList<>();

    private ArrayList<Float> RDist = new ArrayList<>();
    private ArrayList<Float> RVel = new ArrayList<>();
    private ArrayList<Float> RVelalongPt = new ArrayList<>();
    private ArrayList<Float> RVelperpendPt = new ArrayList<>();
    private ArrayList<Float> RVelErr = new ArrayList<>();

    ArrayList<Integer> resTime = new ArrayList();

    /*
    private ArrayList<Float> XVelalongPt;
    private ArrayList<Float> YVelalongPt;
    private ArrayList<Float> XVelperpendPt;
    private ArrayList<Float> YVelperpendPt;
    private ArrayList<Float> XVelErr;
    private ArrayList<Float> YVelErr;
     */
    /**
     * Default constructor. Creates an empty instance of measures object.
     */
    public Measures() {
    }

    /**
     * Creates an instance of measures, which calculates distance, velocity,
     * velocity along platform, velocity perpendicular to platform, velocity
     * errors.
     *
     * @param M mouse object containing mouse x and y position data from
     * original ascii files
     * @param P platform object containing platform information
     */
    public Measures(Mouse M, Platform P) {

        XPos = this.XPosition(M.XData(), P);
        YPos = this.YPosition(M.YData(), P);
        RDist = this.getMeasureMagnitude(XPos, YPos);

        //velocity measure
        ArrayList<Float> XVel = this.getDelMeasure(XPos);
        ArrayList<Float> YVel = this.getDelMeasure(YPos);
        RVel = this.getMeasureMagnitude(XVel, YVel);

        //velocity along platform and perpendicular to platform - CHECK THIS/REDO
        ArrayList<Float> ThetaVel = new ArrayList<>();
        for (int i = 0; i < (XPos.size() - 1); i++) {
            ThetaVel.add(i, (float) Math.acos(((XVel.get(i) * XPos.get(i)) + (YVel.get(i) * YPos.get(i))) / (RDist.get(i) * RVel.get(i))));
            RVelalongPt.add(i, (float) (RVel.get(i) * Math.cos(ThetaVel.get(i))));
            RVelperpendPt.add(i, (float) (RVel.get(i) * Math.sin(ThetaVel.get(i))));
        }

        //velocity error
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

        //residence time
        for (int count = 0; count <= (240 * 240); count++) {
            resTime.add(0);
        }
        for (Float YPo : M.YData()) {
            for (Float XPo : M.XData()) {
                int arrayIdx = ((Math.round(YPo) * 240) + Math.round(XPo));
                resTime.set(arrayIdx, (resTime.get(arrayIdx) + 1));
            }
        }
    }

    /**
     * INCORRECT CODE.
     * 
     * Creates an instance of measures, which calculates distance, velocity,
     * velocity along platform, velocity perpendicular to platform, velocity
     * errors. Stores all measures object of the arraylist of mice in an
     * arraylist of measures.
     *
     * @param Mice arraylist of mouse objects
     * @param P platform object containing platform information
     */
    
    //INCORRECT CODE
    public Measures(ArrayList<Mouse> Mice, Platform P) {
        ArrayList<Measures> measuresList = new ArrayList<>();

        for (int i = 0; i < Mice.size(); i++) {
            Measures measuresMouse = new Measures(Mice.get(i), P);
            measuresList.add(i, measuresMouse);
        }
    }

    private ArrayList<Float> XPosition(ArrayList<Float> XPosData, Platform P) {
        P.getX();
        for (int i = 0; i < (XPosData.size()); i++) {
            XPos.add(i, (P.getX() - XPosData.get(i)));
        }
        return XPos;
    }

    private ArrayList<Float> YPosition(ArrayList<Float> YPosData, Platform P) {
        P.getY();
        for (int i = 0; i < (YPosData.size()); i++) {
            YPos.add(i, (P.getY() - YPosData.get(i)));
        }
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

    //is this correct?
    private ArrayList<Float> normaliseMeasure(ArrayList<Float> X, ArrayList<Float> Y) {
        ArrayList<Float> result = new ArrayList<>();
        for (int i = 0; i < X.size() && i < Y.size(); i++) {
            float fx = X.get(i) / this.mean(this.getMeasureMagnitude(X, Y));
            float fy = Y.get(i) / this.mean(this.getMeasureMagnitude(X, Y));
            float f = (float) Math.sqrt(Math.pow(fx, 2) + Math.pow(fy, 2));
            result.add(i, f);
        }
        return result;
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

    /**
     * @return x position of mouse as an arraylist
     */
    public ArrayList<Float> getXPosition() {
        return XPos;
    }

    /**
     * @return y position of mouse as an arraylist of float
     */
    public ArrayList<Float> getYPosition() {
        return YPos;
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
}
