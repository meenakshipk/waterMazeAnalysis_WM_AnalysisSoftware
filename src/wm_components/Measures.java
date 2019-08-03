package wm_components;

import java.util.ArrayList;

/**
 * List of measures to be computed
 *
 * @author Meenakshi P. created on 02.08.2019
 */
public class Measures {

    private ArrayList<Float> XPos = new ArrayList<>();
    private ArrayList<Float> YPos = new ArrayList<>();

    private ArrayList<Float> RDist = new ArrayList<>();
    private ArrayList<Float> RVel = new ArrayList<>();
    private ArrayList<Float> RVelalongPt = new ArrayList<>();
    private ArrayList<Float> RVelperpendPt = new ArrayList<>();
    private ArrayList<Float> RVelErr = new ArrayList<>();

    /*
    private ArrayList<Float> XVelalongPt;
    private ArrayList<Float> YVelalongPt;
    private ArrayList<Float> XVelperpendPt;
    private ArrayList<Float> YVelperpendPt;
    private ArrayList<Float> XVelErr;
    private ArrayList<Float> YVelErr;
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

        //velocity error - CHECK THIS/REDO
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

    public ArrayList<Float> getXPosition() {
        return XPos;
    }

    public ArrayList<Float> getYPosition() {
        return YPos;
    }

    public ArrayList<Float> getDistance() {
        return RDist;
    }

    public ArrayList<Float> getVelocity() {
        return RVel;
    }

    public ArrayList<Float> getVelocityAlongPt() {
        return RVelalongPt;
    }

    public ArrayList<Float> getVelocityPerpendicularPt() {
        return RVelperpendPt;
    }

    public ArrayList<Float> getVelocityError() {
        return RVelErr;
    }

}
