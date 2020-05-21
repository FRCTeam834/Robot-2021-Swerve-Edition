/**
 *  Class that organizes gains used when assigning values to slots
 */
package frc.robot.swerve;

public class PID_PARAMETERS {
    public final double P;
    public final double I;
    public final double D;
    public final double FF;
    public final int I_ZONE;
    public final double PEAK_OUTPUT;
    
    public PID_PARAMETERS(double P, double I, double D, double FF, int I_ZONE, double PEAK_OUPUT){
        this.P = P;
        this.I = I;
        this.D = D;
        this.FF = FF;
        this.I_ZONE = I_ZONE;
        this.PEAK_OUTPUT = PEAK_OUPUT;
    }
}